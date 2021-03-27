package com.peng.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Auther lovely
 * @Create 2020-05-21 12:05
 * @Description 分散读取
 */
public class BufferGatheingTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("E:\\work\\Data\\network\\Nio.md", "rw");
        FileChannel channel = aFile.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);
        header.put("header".getBytes(StandardCharsets.UTF_8));
        body.put("body".getBytes(StandardCharsets.UTF_8));
        ByteBuffer[] bufferArray = {header, body};

        channel.write(bufferArray);
    }
}
