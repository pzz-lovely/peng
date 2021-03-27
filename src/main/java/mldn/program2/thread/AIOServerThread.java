package mldn.program2.thread;


import mldn.program2.handler.AcceptHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AIOServerThread implements Runnable {
    private static final int PORT = 9999;
    private CountDownLatch latch = null;            //服务器端关闭阻塞
    private AsynchronousServerSocketChannel serverChannel = null;//服务器通道
    public AIOServerThread() throws IOException {
        this.latch = new CountDownLatch(1);         //服务器端阻塞线程数
        this.serverChannel = AsynchronousServerSocketChannel.open();
        this.serverChannel.bind(new InetSocketAddress(PORT));
        System.out.println("服务器启动成功，在" + PORT + "断口上进行监听，等待客户端连接...");
    }

    public AsynchronousServerSocketChannel getServerChannel() {
        return serverChannel;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    @Override
    public void run() {
        this.serverChannel.accept(this, new AcceptHandler());    //等待连接
        try{
            this.latch.await();         //保持连接
            System.err.println("服务器连接失败，服务器停止运行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
