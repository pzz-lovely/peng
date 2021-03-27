package mldn.aio.echo.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AioClientThread implements Runnable {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private AsynchronousSocketChannel clientChannel = null;
    public AioClientThread() throws IOException {
        clientChannel = AsynchronousSocketChannel.open();
        clientChannel.connect(new InetSocketAddress("localhost", 9999));

    }

    @Override
    public void run() {
        try {
            this.countDownLatch.await();
            this.clientChannel.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMessage(String msg){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(msg.getBytes());
        buffer.flip();
        this.clientChannel.write(buffer, buffer
                , new ClientWriterHandler(this.countDownLatch, this.clientChannel));
        if ("exit".equals(msg)) {
            return false;
        }
        return true;
    }
}
