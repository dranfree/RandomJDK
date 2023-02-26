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
        Thread t1 = new Thread(TestSortedPrint::doPrint, "thread-1");
        Thread t2 = new Thread(() -> {
            doJoin(t1);
            doPrint();
        }, "thread-2");
        Thread t3 = new Thread(() -> {
            doJoin(t2);
            doPrint();
        }, "thread-3");
        t1.start();
        t2.start();
        t3.start();
        doJoin(t3);
    }

    private static void doJoin(Thread prev) {
        try {
            prev.join();
        } catch (InterruptedException e) {
            // ignore
        }
    }

    private static void doPrint() {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        log.info("Current Time: {}", LocalDateTime.now());
    }
}
