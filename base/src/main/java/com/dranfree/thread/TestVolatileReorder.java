package com.dranfree.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试volatile禁止指令重排序
 *
 * @author Random
 * @since 2023/3/1
 */
@Slf4j
public class TestVolatileReorder {

    static int a , b, c, d;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            a = 0; b = 0;
            c = 0; d = 0;
            // 正常情况下 c 和 d 不可能同时为 0
            Thread t1 = new Thread(() -> {
                a = 1;
                d = b;
            });
            Thread t2 = new Thread(() -> {
                b = 1;
                c = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            if (c == 0 && d == 0) {
                // 如果没有volatile关键字，那么这里是有可能发生的。
                System.err.println("c == 0 && d == 0");
                break;
            } else {
                System.out.println("c == " + c + " && d == " + d);
            }
        }
        System.out.println("i = " + i);
    }
}
