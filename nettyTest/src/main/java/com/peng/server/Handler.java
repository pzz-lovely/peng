package com.peng.server;



import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Auther lovely
 * @Create 2020-09-03 15:05
 * @Description 处理读写
 */
public class Handler {
    private SocketChannel client;

    public Handler(SocketChannel client) {
        this.client = client;
    }

    public void process(SelectionKey key) throws IOException {
        if (key.isReadable()) {
            read();
        } else if (key.isWritable()) {
            write("0.0");
        }
    }


    // 读取事件
    private void read() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 读取到byteBuffer中
        client.read(byteBuffer);
        // 转换为读模式
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8));
        write("java天下第一");
    }

    // 写入事件
    private void write(String msg) throws IOException {
        client.write(ByteBuffer.wrap(msg.getBytes( StandardCharsets.UTF_8)));
    }
}
