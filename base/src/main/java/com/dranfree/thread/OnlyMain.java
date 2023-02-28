package com.dranfree.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author Random
 * @since 2023/2/28
 */
public class OnlyMain {

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        for (ThreadInfo threadInfo : threadMXBean.dumpAllThreads(false, false)) {
            System.out.println("[" + threadInfo.getThreadId() + "] : " + threadInfo.getThreadName());
        }
    }
}
