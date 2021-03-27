package com.peng.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @Auther lovely
 * @Create 2020-05-21 12:15
 * @Description
 * transferTo(long position,long count,WritableByteChannel target)
 * 将该通道文件的字节传输到给定的可写的字节通道
 *
 */
public class ChannelTransferToTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("D:\\task\\peng2\\network\\src\\main\\java\\com\\peng\\nio\\fromFile.txt","rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("D:\\task\\peng2\\network\\src\\main\\java\\com\\peng\\nio" +
                "\\toFile" +
                ".txt","rw");

        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }
}
