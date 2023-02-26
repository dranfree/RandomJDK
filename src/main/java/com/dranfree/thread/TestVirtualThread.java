package com.dranfree.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试虚拟线程
 *
 * @author Random
 * @since 2023/2/26
 */
@Slf4j
public class TestVirtualThread {

    public static void main(String[] args) throws InterruptedException {
        Thread.ofVirtual().name("TEST-VT").start(() -> {
           log.info("Hello, Virtual Thread! Current Thread Name: {}", Thread.currentThread().getName());
        }).join();
    }
}
