package com.dranfree.thread;

import java.util.concurrent.TimeUnit;

/**
 * 测试volatile可见性
 *
 * @author Random
 * @since 2023/3/1
 */
public class TestVolatileVisibility {

    // 加上volatile关键字，程序可以正常结束；否则，程序会一直运行下去。
    private static volatile boolean active = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (active) {

            }
            System.out.println("Thread1 is stop");
        }, "Thread1").start();
        // 确保线程1已经启动
        SleepTools.sleep(1, TimeUnit.SECONDS);
        new Thread(() -> {
            SleepTools.sleep(2, TimeUnit.SECONDS);
            System.out.println("Thread2 run");
            active = false;
            System.out.println("now is inactive!");
        }, "Thread2").start();
    }
}
