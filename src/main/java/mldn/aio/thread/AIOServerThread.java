package mldn.aio.thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AIOServerThread implements Runnable {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private AsynchronousServerSocketChannel serverSocket;

    public AIOServerThread(int port) throws IOException {
        serverSocket = AsynchronousServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("服务器 启动成功 在 " + port + " 端口上监听");
    }

    @Override
    public void run() {
        try {
            countDownLatch.await(); //保持连接
            System.err.println("服务器连接失败，服务器停止运行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel getServerSocket() {
        return serverSocket;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
