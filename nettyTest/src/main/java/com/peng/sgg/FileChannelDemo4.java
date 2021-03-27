package com.peng.sgg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author lovely
 * @Create 2020-09-10 16:00
 * @Description Nio还提供了 MappedByteBuffer，可以让文件直接在内存(堆外的内存)修改，操作系统不需要拷贝一次
 */
public class FileChannelDemo4 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\jvm.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        /*
         * 参数1：FileChannel.MapMode.READ_WRITE 使用读写模式
         * 参数2：可以直接修改的起始位置
         * 参数3：映射到内存的大小
         * 实际类型DirectByteBuffer
         */
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        buffer.put(0, (byte) 'J');
        buffer.put(1, (byte) 'A');
        buffer.put(2, (byte) 'V');
        buffer.put(3, (byte) 'A');


        System.out.println((char) buffer.get());
        randomAccessFile.close();
    }
}