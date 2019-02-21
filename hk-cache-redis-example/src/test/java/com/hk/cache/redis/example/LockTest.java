package com.hk.cache.redis.example;

import com.hk.core.redis.locks.RedisLock;
import com.hk.core.test.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.Lock;

/**
 * Redis 分布式锁
 *
 * @author huangkai
 * @date 2019-02-21 15:59
 */
@Log4j2
@SpringBootTest(classes = {RedisExampleApplication.class})
public class LockTest extends BaseTest {

    @Test
    public void lockTest() {
        Lock lock = new RedisLock("key3");
        try {
            lock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void ticketTest() throws InterruptedException {
        //V1
//        Runnable ticket = new TicketThread();
//        Thread t1 = new Thread(ticket, "窗口1");
//        Thread t2 = new Thread(ticket, "窗口2");
//        Thread t3 = new Thread(ticket, "窗口3");
//        Thread t4 = new Thread(ticket, "窗口4");
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        Thread.currentThread().join();

        // V2: 使用 Lcok ，保证线程安全
        Lock lock = new RedisLock("ticketLock");
        Runnable ticket = new LockTicketThread(lock);
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");
        Thread t4 = new Thread(ticket, "窗口4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        Thread.currentThread().join();


    }

    /**
     * V2: 线程安全
     */
    private class LockTicketThread implements Runnable {

        private int ticket = 100;

        private Lock lock;

        LockTicketThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (ticket > 0) {
                try {
                    lock.lock();
                    if (ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "正在售出第" + (ticket--) + "张票");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();

                }
            }
        }
    }


//    /**
//     * V1: 线程不安全
//     */
//    private class TicketThread implements Runnable {
//
//        private int ticket = 100;
//
//        @Override
//        public void run() {
//            while (ticket > 0) {
//                System.out.println(Thread.currentThread().getName() + "正在售出第" + (ticket--) + "张票");
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
