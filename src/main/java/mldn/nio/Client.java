package mldn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        SocketChannel clientSocket = SocketChannel.open();  //获取客户端通道
        clientSocket.connect(new InetSocketAddress("localhost", 9999));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        boolean flag = true;
        while (flag) {
            buffer.clear();
            String msg = InputUtil.getString("请输入你要输入的信息：");
            buffer.put(msg.getBytes());
            buffer.flip();
            clientSocket.write(buffer);
            buffer.clear();
            int readCount = clientSocket.read(buffer);
            buffer.flip();
            System.err.println(new String(buffer.array()));
            if ("exit".equalsIgnoreCase(msg)) {
                flag = false;
            }
        }
        clientSocket.close();
    }
}
