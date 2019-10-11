package hk.springboot.webflux.example;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * reactor {@link Schedulers} 使用
 *
 * @author kevin
 * @date 2019-10-8 11:15
 * @see https://blog.csdn.net/get_set/article/details/79480172
 */
public class SchedulersTest {

    public static void main(String[] args) {
        immediate();
        System.out.println("-------immediate--------");

        single();
        System.out.println("-------single--------");

        elastic();
        System.out.println("--------elastic-------");
        parallel();
        System.out.println(Runtime.getRuntime().availableProcessors());//获取当前运行环境的处理器个数
    }

    /**
     * 使用当前线程
     */
    private static void immediate() {
        Schedulers.immediate().schedule(() -> System.out.println(Thread.currentThread().getName()));
    }

    /**
     * 固定大小线程池:相当于 : Executors.newFixedThreadPool() ,默认为处理器个数
     */
    private static void parallel() {
        /*
            第一个参数：线程名称前缀
            第二个参数：线程个数，默认为 最大处理器个数
         */
        Scheduler elastic = Schedulers.newParallel("custom-parallel", 10);
        for (int i = 0; i < 100; i++) {
            elastic.schedule(() -> System.out.println(Thread.currentThread().getName()));
        }
        elastic.dispose();
    }

    /**
     * 弹性线程池：相当于 : Executors.newCachedThreadPool()
     */
    private static void elastic() {
        Scheduler elastic = Schedulers.elastic();
        for (int i = 0; i < 100; i++) {
            elastic.schedule(() -> System.out.println(Thread.currentThread().getName()));
        }
    }

    /**
     * 创建单个线程池: 相当于 Executors.newSingleThreadExecutor()
     */
    private static void single() {
        Scheduler single = Schedulers.single();
        for (int i = 0; i < 10; i++) {
            single.schedule(() -> System.out.println(Thread.currentThread().getName()));
        }
    }
}

