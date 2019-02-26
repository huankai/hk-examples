package com.hk.mysql.example.cache.test;

import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import com.hk.mysql.examples.domain.Account;
import com.hk.mysql.examples.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CyclicBarrier;

/**
 * @author huangkai
 * @date 2019-02-26 17:32
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class CacheTest extends BaseTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void test() throws InterruptedException {
        int num = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        for (int i = 0; i < num; i++) {
            new TestCyclicBarrier(cyclicBarrier).start();
        }
        Thread.currentThread().join();

    }

    private class TestCyclicBarrier extends Thread {

        private CyclicBarrier cyclicBarrier;

        private TestCyclicBarrier(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                Account account = accountService.getById("1");
                System.out.println(JsonUtils.serialize(account));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
