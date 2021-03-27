package mldn.aio.handler;

import mldn.aio.thread.AIOServerThread;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServerThread> {

    /**
     * 操作完成后 调用
     * @param result io操作的结果类型
     * @param attachment 附加到io操作的对象的类型
     */
    @Override
    public void completed(AsynchronousSocketChannel result, AIOServerThread attachment) {
        attachment.getServerSocket().accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(54);
        result.read(buffer, buffer, new EchoHandler(result));
    }

    @Override
    public void failed(Throwable exc, AIOServerThread attachment) {
        System.out.println("服务器连接失败");
        attachment.getCountDownLatch().countDown();
    }
}
