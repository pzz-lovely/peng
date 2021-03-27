package com.peng.file.transfer.server;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author lovely
 * @Create 2020-11-12 13:45
 * @Description todo
 */
public class FileOperation {
    private long size;

    private String fileName;

    private FileChannel fileChannel;

    public long out(String fileName, ByteBuffer buffer) {
        long startTime = System.currentTimeMillis();
//        fileChannel.write(buffer)
        long endTime = System.currentTimeMillis();
        return startTime - endTime;
    }
}