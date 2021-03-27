package com.peng.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther lovely
 * @Create 2020-05-21 10:50
 * @Description 文件管道读取
 *
 * read(ByteBuffer[] dsts, int offset, int length)
 * 从该通道读取字节序列到给定缓冲区的子序列中。
 * write(ByteBuffer src, long position)
 * 从给定的缓冲区向给定的文件位置开始，向该通道写入一个字节序列。
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("E:\\work\\Data\\network\\Nio.md","rw");
        FileChannel nioChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        nioChannel.read(buf);   //读取缓冲区与
        buf.flip();
        System.out.println(new String(buf.array()));
        while (buf.hasRemaining()) {
            System.out.println((char) buf.get());
        }
        aFile.close();

    }
}
