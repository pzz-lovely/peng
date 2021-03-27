package mldn.aio.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:25
 * @Description todo
 */
public class ClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private CountDownLatch latch;
    private AsynchronousSocketChannel clientChannel;

    public ClientReadHandler(CountDownLatch latch, AsynchronousSocketChannel clientChannel) {
        this.latch = latch;
        this.clientChannel = clientChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        String receiveMessage = new String(buffer.array(), 0, buffer.remaining());
        System.err.println(receiveMessage);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("对不起，发送出现问题，该客户端被关闭");
        try{
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
