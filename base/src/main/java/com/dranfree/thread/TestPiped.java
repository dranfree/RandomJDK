package com.dranfree.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道输入输出：数据交换
 *
 * @author Random
 * @since 2023/2/26
 */
public class TestPiped {

    public static void main(String[] args) throws IOException {

        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter();
        writer.connect(reader);

        // 输出线程
        Thread.ofPlatform().daemon(true).name("PRINT-THREAD").start(() -> {
            int receive;
            try {
                while ((receive = reader.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                // ignore
            }
        });

        // 输入线程：从用户输入读取文本
        int receive;
        try {
            while ((receive = System.in.read()) != -1) {
                // 将结果交给输出线程处理
                writer.write(receive);
            }
        } finally {
            writer.close();
        }
    }
}
