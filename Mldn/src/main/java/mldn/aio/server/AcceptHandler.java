package mldn.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:00
 * @Description todo
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServerThread> {


    @Override
    public void completed(AsynchronousSocketChannel result, AioServerThread aioThread) {
        aioThread.getServer().accept(aioThread, this);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        result.read(buffer, buffer, new EchoHandler(result));
    }

    @Override
    public void failed(Throwable exc, AioServerThread attachment) {
        System.out.println("服务器的连接处理失败...");
        attachment.getLatch().countDown();
    }
}
