package mldn.aio.echo.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class ClientWriterHandler implements CompletionHandler<Integer, ByteBuffer> {
    private CountDownLatch countDownLatch;
    private AsynchronousSocketChannel clientChannel;

    public ClientWriterHandler(CountDownLatch countDownLatch, AsynchronousSocketChannel clientChannel) {
        this.clientChannel = clientChannel;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        if (attachment.hasRemaining()) {
            this.clientChannel.write(attachment, attachment, this);
        }else{
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            this.clientChannel.read(buffer, buffer, new ClientHandler(this.clientChannel, countDownLatch));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("服务器出现问题");
        try{
            clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.countDownLatch.countDown();
    }
}
