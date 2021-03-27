package mldn.aio.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:29
 * @Description todo
 */
public class ClientWriteHandler implements CompletionHandler<Integer, ByteBuffer> {
    private CountDownLatch latch;
    private AsynchronousSocketChannel clientChannel;

    public ClientWriteHandler(CountDownLatch latch, AsynchronousSocketChannel clientChannel) {
        this.latch = latch;
        this.clientChannel = clientChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if (buffer.hasRemaining()) {
            this.clientChannel.write(buffer, buffer, this);
        }else{
            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            this.clientChannel.read(readBuffer, readBuffer, new ClientReadHandler(this.latch, this.clientChannel));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("对不起，发送出了问题，该客户端被关闭...");
        try{
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.latch.countDown();
    }
}
