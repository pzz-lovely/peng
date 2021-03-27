package mldn.aio.thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AIOServerThread implements Runnable {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private AsynchronousServerSocketChannel serverSocket;

    public AIOServerThread(int port) throws IOException {
        serverSocket = AsynchronousServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("������ �����ɹ� �� " + port + " �˿��ϼ���");
    }

    @Override
    public void run() {
        try {
            countDownLatch.await(); //��������
            System.err.println("����������ʧ�ܣ�������ֹͣ����...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel getServerSocket() {
        return serverSocket;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
