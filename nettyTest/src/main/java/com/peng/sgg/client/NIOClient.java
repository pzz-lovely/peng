package com.peng.sgg.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Author lovely
 * @Create 2020-09-11 12:36
 * @Description todo
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost", 8088));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ByteBuffer buffer = ByteBuffer.allocate(24);

        String line  = "";

        Thread thread = new Thread(new ClientHandlerReader(socketChannel, selector));
        thread.start();

        while ((line = bufferedReader.readLine()) != null) {
            buffer.clear();

            buffer.put(line.getBytes(StandardCharsets.UTF_8));

            buffer.flip();
            socketChannel.write(buffer);
        }
    }
}