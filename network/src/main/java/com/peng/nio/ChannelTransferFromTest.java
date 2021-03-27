package com.peng.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @Auther lovely
 * @Create 2020-05-21 12:15
 * @Description
 * transferFrom()方法
 * 从给定的可读字节通道传输到给定的可以写字节通道
 * transferFrom(ReadableByteChannel src,long position,long count)
 */
public class ChannelTransferFromTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("D:\\task\\peng2\\network\\src\\main\\java\\com\\peng\\nio\\fromFile.txt","rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("D:\\task\\peng2\\network\\src\\main\\java\\com\\peng\\nio\\toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        System.out.println("Count = "+count);
        toChannel.transferFrom(fromChannel, position, count);
    }
}
