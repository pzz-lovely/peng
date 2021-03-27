package com.peng.server;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-09-03 15:05
 * @Description 处理读写
 */
public class Handler {
    private SocketChannel client;
    private SelectionKey key;

    public Handler(SocketChannel client, SelectionKey key) {
        this.key = key;
        this.client = client;
    }

    public void process() throws IOException {
        System.out.println("进入读写"+key.readyOps());
        System.out.println(this);
        if (client != null && key != null) {
            if (key.isReadable()) {
                read();
            } else if (key.isWritable()) {
                write("0.0");
            }
        }
    }


    // 读取事件
    private void read() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(client);
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

    @Override
    public String toString() {
        return "Handler{" +
                "client=" + client +
                ", key=" + key +
                '}';
    }
}
