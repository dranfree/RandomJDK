package com.dranfree.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Random
 * @since 2023/2/28
 */
@Slf4j
public class TestInterrupt {

    public static void main(String[] args) throws InterruptedException {

        final Lock lock = new ReentrantLock();

        final CountDownLatch signal = new CountDownLatch(1);

        Thread holdLockThread = new Thread(() -> {
            lock.lock();
            try {
                signal.countDown();
                log.info("I'm locked: {}", Thread.currentThread().getName());
                SleepTools.sleep(60, TimeUnit.SECONDS);
            } finally {
                lock.unlock();
            }
        }, "LockHolderThread");
        holdLockThread.start();

        signal.await();

        final CountDownLatch exit = new CountDownLatch(1);

        Thread waitLockThread = new Thread(() -> {
            // 无法被中断
            lock.lock();
            try {
                lock.lockInterruptibly();
                try {
                    log.info("I'm locked: {}", Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException ie) {
                exit.countDown();
            }
        }, "LockWaiterThread");
        waitLockThread.start();

        waitLockThread.interrupt();

        exit.await();

        log.info("exit...");

//        SleepTools.sleep(10, TimeUnit.SECONDS);
    }
}
