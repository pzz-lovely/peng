package mldn.program1.client;


import mldn.program1.util.InputUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.connect(new InetSocketAddress("localhost", 9999));
        ByteBuffer buffer = ByteBuffer.allocate(50);
        boolean flag = true;
        while (flag) {
            buffer.clear();
            String msg = InputUtil.getString("请输入你要输入的信息:");
            buffer.put(msg.getBytes());
            buffer.flip();
            clientChannel.write(buffer);
            buffer.clear();
            int readCount = clientChannel.read(buffer);
            buffer.flip();
            System.err.println(new java.lang.String(buffer.array(), 0, readCount));
            if ("exit".equals(msg)) {
                flag = false;
            }
        }
        clientChannel.close();
    }
}
