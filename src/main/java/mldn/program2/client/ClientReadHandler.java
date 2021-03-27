package mldn.program2.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class ClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private CountDownLatch latch;                   //线程同步
    private AsynchronousSocketChannel clientChannel = null;//客户端连接

    public ClientReadHandler(AsynchronousSocketChannel clientChannel,CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        String receiveMessage = new String(attachment.array(), 0, attachment.remaining());                              //读取返回内容
        System.err.println(receiveMessage);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("对不起，发送出现了问题，该客户端被关闭...");
        try{
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.latch.countDown();
    }
}
