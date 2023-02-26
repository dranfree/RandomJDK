package com.dranfree.thread;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 多线程有序打印
 *
 * @author Random
 * @since 2023/2/26
 */
@Slf4j
public class TestSortedPrint {

    public static void main(String[] args) {
        Thread t1 = new Thread(TestSortedPrint::doPrintCurrentTime, "thread-1");
        Thread t2 = new Thread(() -> {
            doJoinOn(t1);
            doPrintCurrentTime();
        }, "thread-2");
        Thread t3 = new Thread(() -> {
            doJoinOn(t2);
            doPrintCurrentTime();
        }, "thread-3");
        t1.start();
        t2.start();
        t3.start();
        doJoinOn(t3);
    }

    private static void doJoinOn(Thread prev) {
        try {
            prev.join();
        } catch (InterruptedException e) {
            // ignore
        }
    }

    private static void doPrintCurrentTime() {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        log.info("Current Time: {}", LocalDateTime.now());
    }
}
