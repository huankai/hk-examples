package com.hk.concurrent;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * {@link CountDownLatch} 测试
 * </p>
 *
 * <pre>
 *     CountDownLath 应用场景:
 *          使用减计数方式，多个线程同时工作，其中的几个线程可以任意并行执行，但有一个线程需要等其它线程工作结束后，才能开始。
 *          如多线程分块下载一个大文件，每个线程只下载其中的一部分，最后由一个主线程拼接所有的分段。
 *
 * </pre>
 *
 * @author huangkai
 * @date 2019-02-15 16:18
 */
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {
        int threadNum = 3;
        CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new ThreadTest(COUNT_DOWN_LATCH).start();
        }
        /*
                调用 await() 方法，会阻塞，需要 CountdownLatch 计数值为 0 时才会释放阻塞
         */
        COUNT_DOWN_LATCH.await();
        System.out.println("end...");
    }

    private static class ThreadTest extends Thread {

        private CountDownLatch countDownLatch;

        ThreadTest(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " start...");
                long sleep = RandomUtils.nextLong(1000, 10000);
                Thread.sleep(sleep);
                System.out.println(threadName + "sleep :" + sleep + ",complate...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                /*
                    将CountDownLatch 计数器减 1
                 */
                countDownLatch.countDown();
            }
        }
    }
}
