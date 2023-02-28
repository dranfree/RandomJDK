package com.dranfree.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Random
 * @since 2023/2/28
 */
public class SleepTools {

    public static void sleep(long time, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
