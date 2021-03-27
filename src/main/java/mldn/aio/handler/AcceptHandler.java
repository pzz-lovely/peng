package mldn.aio.handler;

import mldn.aio.thread.AIOServerThread;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServerThread> {

    /**
     * ������ɺ� ����
     * @param result io�����Ľ������
     * @param attachment ���ӵ�io�����Ķ��������
     */
    @Override
    public void completed(AsynchronousSocketChannel result, AIOServerThread attachment) {
        attachment.getServerSocket().accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(54);
        result.read(buffer, buffer, new EchoHandler(result));
    }

    @Override
    public void failed(Throwable exc, AIOServerThread attachment) {
        System.out.println("����������ʧ��");
        attachment.getCountDownLatch().countDown();
    }
}
