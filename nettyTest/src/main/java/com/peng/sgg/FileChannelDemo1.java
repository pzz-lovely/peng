package com.peng.sgg;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Author lovely
 * @Create 2020-09-10 14:54
 * @Description 将一个文本读取到 控制台
 */
public class FileChannelDemo1 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("F:\\csdn地址 .txt"), "r");

        // 这个FileChannel 真实类型是 FileChannelImpl
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读取到buffer中
        channel.read(buffer);

        // 切换到读模式
        buffer.flip();

        if (buffer.hasArray()) {
            String message = new String(buffer.array(), StandardCharsets.UTF_8);
            System.out.println(message);
        }

    }
}