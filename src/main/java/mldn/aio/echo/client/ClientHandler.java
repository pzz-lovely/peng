package mldn.aio.echo.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class ClientHandler implements CompletionHandler<Integer, ByteBuffer> {
    private CountDownLatch countDownLatch ;
    private AsynchronousSocketChannel clientChannel;

    public ClientHandler(AsynchronousSocketChannel clientChannel,CountDownLatch countDownLatch) {
        this.clientChannel = clientChannel;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        String receiveMessage = new String(attachment.array(), 0, attachment.remaining());
        System.err.println(receiveMessage);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("发送除了问题，服务器的问题");
        try{
            clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
            this.countDownLatch.countDown();
        }
    }
}
