package com.peng.sgg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 * @Author lovely
 * @Create 2020-09-10 15:36
 * @Description 文件复制，使用transferFrom拷贝文件
 */
public class FileChannelDemo3 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("F:\\csdn地址.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("F:\\jvm.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        fromChannel.transferFrom(toChannel, 0, fromChannel.size());


        // 关闭Channel
        fromChannel.close();
        toChannel.close();
    }
}