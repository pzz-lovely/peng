package mldn.aio.echo.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServerThread> {
    @Override
    public void completed(AsynchronousSocketChannel result, AioServerThread attachment) {
        attachment.getServerChannel().accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new EchoHandler(result));
    }

    @Override
    public void failed(Throwable exc, AioServerThread attachment) {
        attachment.getLatch().countDown();
    }
}
