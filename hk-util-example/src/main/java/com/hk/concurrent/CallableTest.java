package com.hk.concurrent;

import java.util.concurrent.*;

/**
 * <p>
 * {@link Callable} 测试
 * </p>
 * <pre>
 *     创建子线程来做一些耗时任务，然后把任务执行结果回传给主线程使用。
 *     {@link Runnable} 执行完成后没有返回值，可以使用 {@link Callable} .
 * </pre>
 *
 * @author huangkai
 * @date 2019-02-15 16:50
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            System.out.println("start...");
//            Thread.sleep(5000);
            int result = 0;
            for (int i = 0; i <= 100; i++) {
                result += i;
            }
            return result;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        /* 方式一：使用 Thread */
        new Thread(futureTask).start();
        /*
        获取子线程的返回结果，会阻塞
         */
        System.out.println("Result:" + futureTask.get());

        /* 方式二：使用 ExecutorService */
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);
        System.out.println("Result: " + futureTask.get());

    }
}
