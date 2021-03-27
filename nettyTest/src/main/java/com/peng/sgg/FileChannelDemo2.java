package com.peng.sgg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author lovely
 * @Create 2020-09-10 15:28
 * @Description 文件复制，通过Buffer来定义一个装载数据的容器
 */
public class FileChannelDemo2 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile inFile = new RandomAccessFile("F:\\csdn地址.txt", "r");
        FileChannel inChannel = inFile.getChannel();

        RandomAccessFile outFile = new RandomAccessFile("F:\\jvm.txt", "rw");
        FileChannel outChannel = outFile.getChannel();

        // 定义装载数据的容器
        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            // 所有位置清0，不清0 否则切换为读 limit永远在相同位置
            buffer.clear();


            // 读取到 ByteBuffer中
            int read = inChannel.read(buffer);

            if(read == -1)
                break;

            // 切换读模式
            buffer.flip();
            outChannel.write(buffer);
        }
        inFile.close();
        outFile.close();
    }
}