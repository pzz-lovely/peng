package mldn.program2.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AIOClientThread implements Runnable {

    private CountDownLatch latch;               //线程锁定
    private AsynchronousSocketChannel clientChannel = null;//客户端连接

    public AIOClientThread() throws IOException {
        this.clientChannel = AsynchronousSocketChannel.open();
        this.clientChannel.connect(new InetSocketAddress("localhost", 9999));
        this.latch = new CountDownLatch(1);
    }
    @Override
    public void run() {
        try{
            this.latch.await();      //等待处理
            this.clientChannel.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMessage(String msg) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put(msg.getBytes());
        buffer.flip();
        this.clientChannel.write(buffer, buffer, new ClientWriteHandler(this.latch, this.clientChannel));
        if("exit".equalsIgnoreCase(msg))
            return false;
        return true;
    }
}
