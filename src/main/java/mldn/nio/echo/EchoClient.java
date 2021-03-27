package mldn.nio.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.connect(new InetSocketAddress("localhost", 8080));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        boolean flag = true;
        while (flag) {
            buffer.clear();
            String msg = InputUtil.getString("请输入要发送的信息s");
            buffer.put(msg.getBytes());
            buffer.flip();
            clientChannel.write(buffer);
            buffer.clear();
            int readCount = clientChannel.read(buffer);
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, readCount));
            if ("exit".equals(msg)) {
                flag = false;
            }
        }
        clientChannel.close();
    }

}
