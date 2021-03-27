package com.peng.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Auther lovely
 * @Create 2020-09-03 15:26
 * @Description todo
 */

public class Client {
    public static void main(String[] args) throws IOException {

        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("192.168.1.66", 8888));
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (true) {
            buf.put(buffer.readLine().getBytes(StandardCharsets.UTF_8));
            buf.flip();

            client.write(buf);

            buf.clear();

            client.read(buf);
            buf.flip();

            System.out.println(new String(buf.array(), StandardCharsets.UTF_8));
        }
    }
}
