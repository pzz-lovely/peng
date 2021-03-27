package mldn.nio;


import mldn.nio.util.InputUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Auther lovely
 * @Create 2020-04-28 21:00
 * @Description todo
 */
public class EchoClient {
    public static final String HOST = "localhost";
    public static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress(HOST, PORT));
        ByteBuffer buffer = ByteBuffer.allocate(50);
        boolean flag = true;
        while (flag) {
            buffer.clear();
            String msg = InputUtil.getString("请输要发送的消息");
            buffer.put(msg.getBytes());
            buffer.flip();
            client.write(buffer);
            buffer.clear();
            int readCount = client.read(buffer);
            buffer.flip();
            System.err.println(new String(buffer.array(), 0, readCount));
            if("exit".equals(msg))
                flag = false;
        }
        client.close();
    }
}
