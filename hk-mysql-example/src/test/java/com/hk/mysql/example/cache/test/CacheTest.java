package com.hk.mysql.example.cache.test;

import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import com.hk.mysql.examples.domain.Account;
import com.hk.mysql.examples.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.concurrent.CyclicBarrier;

/**
 * @author huangkai
 * @date 2019-02-26 17:32
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class CacheTest extends BaseTest {

    @Autowired
    private AccountService accountService;

    /**
     * 缓存穿透测试：
     * <pre>
     *  id 为 1 的记录在数据中存在， id 为 5  的记录在数据库中不存在，
     *  将数据库查询为 null 的记录(id 为 5)也保存在缓存中，但设置的缓存过期时间较短，防止在高并发下用户访问不存在的记录造成数据库压力过大，
     *  为null 的缓存过期时间配置参数，可查看 {@link NullCacheProperties#getNullValueTtl()}
     *
     * </pre>
     */
    @Test
    public void test2() throws InterruptedException {
        int num = 100;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        for (int i = 0; i < num; i++) {
            new TestCyclicBarrier(cyclicBarrier, "1").start();
        }

//        num = 100;
//        cyclicBarrier = new CyclicBarrier(num);
//        for (int i = 0; i < num; i++) {
//            new TestCyclicBarrier(cyclicBarrier, "5").start();
//        }
        Thread.currentThread().join();
    }

    /**
     * 缓存雪崩测试:
     * <pre>
     *     在高并发条件下，多个线程同时查询同一记录，如果缓存中不存在 ，只需要有一个线程去数据库查询记录，放在缓存中，其它线程不需要从数据库中查询，直接在缓存中取数据，
     *     实现方法，使用 {@link com.hk.core.cache.interceptor.LockCacheInterceptor} 加上 synchronized
     * </pre>
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        int num = 1000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        for (int i = 0; i < num; i++) {
            new TestCyclicBarrier(cyclicBarrier, "1").start();
        }
        Thread.currentThread().join();

    }

    private class TestCyclicBarrier extends Thread {

        private String id;

        private CyclicBarrier cyclicBarrier;

        private TestCyclicBarrier(CyclicBarrier cyclicBarrier, String id) {
            this.cyclicBarrier = cyclicBarrier;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                Optional<Account> account = accountService.findById(id);
                System.out.println(account.isPresent());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
