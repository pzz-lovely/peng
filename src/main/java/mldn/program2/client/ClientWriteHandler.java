package mldn.program2.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class ClientWriteHandler implements CompletionHandler<Integer, ByteBuffer> {
    private CountDownLatch latch;
    private AsynchronousSocketChannel clientChannel = null;//客户端连接

    public ClientWriteHandler(CountDownLatch latch, AsynchronousSocketChannel clientChannel) {
        this.latch = latch;
        this.clientChannel = clientChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if (buffer.hasRemaining()) {
            this.clientChannel.write(buffer, buffer, this); //数据发送s
        }else {
            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            this.clientChannel.read(readBuffer, readBuffer, new ClientReadHandler(this.clientChannel, this.latch));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println("对不起，发送出了问题，该客户端被关闭");
        try{
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
