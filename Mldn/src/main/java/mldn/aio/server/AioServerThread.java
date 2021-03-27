package mldn.aio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:01
 * @Description todo
 */
public class AioServerThread implements Runnable {
    private static final int PORT = 9999;
    private CountDownLatch latch = null;;
    private AsynchronousServerSocketChannel server;

    public AioServerThread() throws IOException {
        this.latch = new CountDownLatch(1);
        this.server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress(PORT));
        System.out.println("服务器启动成功，在" + PORT + "端口上监听，等待客户端连接");
    }

    public AsynchronousServerSocketChannel getServer() {
        return server;
    }

    public void setServer(AsynchronousServerSocketChannel server) {
        this.server = server;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        this.server.accept(this, new AcceptHandler());
        try{
            latch.await();
            System.out.println("服务器连接失败，服务器停止运行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
