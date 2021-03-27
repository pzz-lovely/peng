package mldn.program2.handler;

import mldn.program2.thread.AIOServerThread;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;


public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServerThread> {

    @Override
    public void completed(AsynchronousSocketChannel result, AIOServerThread attachment) {
        attachment.getServerChannel().accept(attachment, this); //接收连接
        ByteBuffer buffer = ByteBuffer.allocate(100);                  //接收缓冲区
        result.read(buffer, buffer, new EchoHandler(result));
    }

    @Override
    public void failed(Throwable exc, AIOServerThread attachment) {
        System.out.println("服务器的连接失败s...");
        attachment.getLatch().countDown();//解除阻塞状态
    }
}
