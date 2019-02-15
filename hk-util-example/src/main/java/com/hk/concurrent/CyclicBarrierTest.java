package com.hk.concurrent;

import java.util.concurrent.CyclicBarrier;

/**
 * <p>
 * {@link CyclicBarrier} 测试
 * </p>
 * <pre>
 *
 * 和 CountDownLatch 相反，原理是使多个线程都准备好之后一起执行。使用加计算方式，计数达到指定的值时释放所有等待的线程。
 * 应用场景：
 *      如多个运动员(每个运动员表示一个线程) 在起跑线上，都先准备好，等待枪声响时(计算器达到了指定的值)，同时起跑。
 * </pre>
 *
 * @author huangkai
 * @date 2019-02-15 16:36
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        int num = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        for (int i = 0; i < num; i++) {
            new SimpleCyclicBarrier(cyclicBarrier).start();
        }
    }

    private static class SimpleCyclicBarrier extends Thread {

        private CyclicBarrier cyclicBarrier;

        private SimpleCyclicBarrier(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "start...");
            try {
                /*
                    阻塞，每个线程执行一次 await()方法，会使 cyclicBarrier 变量中的计算加1
                 */
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*
            必须等到所有线程(如这里开启5个线程)执行完 cyclicBarrier.await() 方法后，才会执行后面的代码
             */
            System.out.println(threadName + "working...");
        }
    }
}
