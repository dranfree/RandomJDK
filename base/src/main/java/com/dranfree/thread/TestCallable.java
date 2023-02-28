package com.dranfree.thread;

import java.util.concurrent.*;

/**
 * @author Random
 * @since 2023/2/28
 */
public class TestCallable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CountDownLatch latch = new CountDownLatch(1);

        Callable<Integer> callable = () -> {
            int sum = 0;
            for (int i = 0; i < 1000; i++) {
                sum += i;
            }
            SleepTools.sleep(5, TimeUnit.SECONDS);
            latch.countDown();
            return sum;
        };

        FutureTask<Integer> task = new FutureTask<>(callable);

        new Thread(task).start();

        System.out.println("已提交计算任务...");

        latch.await();

        System.out.println("计算任务已完成：" + task.get());
    }
}
