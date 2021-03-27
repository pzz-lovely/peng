package mldn.aio.echo.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AioServerThread implements Runnable {
    private CountDownLatch countDownLatch = null;
    private AsynchronousServerSocketChannel serverSocketChannel = null;

    public AioServerThread() throws IOException {
        this.countDownLatch = new CountDownLatch(1);
        this.serverSocketChannel = AsynchronousServerSocketChannel.open();
        this.serverSocketChannel.bind(new InetSocketAddress(9999));
        System.out.println("服务器启动在 " + serverSocketChannel.getLocalAddress() + " 运行");
    }

    public AsynchronousServerSocketChannel getServerChannel(){
        return serverSocketChannel;
    }

    public CountDownLatch getLatch(){
        return countDownLatch;
    }


    @Override
    public void run() {
        this.serverSocketChannel.accept(this, new AcceptHandler());
        try{
            this.countDownLatch.await();        //等待
            System.out.println("服务器的连接失败，服务器停止运行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
