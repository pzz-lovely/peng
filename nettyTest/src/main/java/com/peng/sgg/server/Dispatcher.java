package com.peng.sgg.server;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author lovely
 * @Create 2020-09-10 18:58
 * @Description todo
 */
public class Dispatcher {

    private ByteBuffer outBuffer = ByteBuffer.allocate(512);
    private ByteBuffer inBuffer = ByteBuffer.allocate(512);
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void channelRead(Message recentlyMessage, SocketChannel channel) throws IOException {
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        outBuffer.clear();
        try {
            readLock.lock();
            outBuffer.put(recentlyMessage.toString().getBytes(StandardCharsets.UTF_8));

            outBuffer.flip();
            channel.write(outBuffer);
        }finally {
            readLock.unlock();
        }
    }


    public String channelWrite(SocketChannel socketChannel
                                         ) throws IOException {
        synchronized (this) {
            inBuffer.clear();
            socketChannel.read(inBuffer);
            inBuffer.flip();


            String msg = new String(inBuffer.array(),StandardCharsets.UTF_8);
            System.out.println("服务器接收到消息:"+msg+"，正在准备转发");
            return msg;
        }
    }


}