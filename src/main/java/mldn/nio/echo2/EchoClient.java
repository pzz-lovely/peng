package mldn.nio.echo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 9999));
        boolean flag = true;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (flag) {
            client.write(buffer);
            String readMessage = new String(buffer.array(), 0, buffer.remaining());
            System.out.println(readMessage);
            String writeMessage = InputUtil.getString("«Î ‰»Î ˝æ›");
            buffer.clear();
            buffer.put(writeMessage.getBytes());
            buffer.flip();
        }
        client.close();
    }
}
