package com.peng.nonetty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther lovely
 * @Create 2020-07-29 8:36
 * @Description Java Api方式的 nio
 */
public class PlainNioServer {
    public void server(int port) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress(port);
        ServerSocket ss = server.socket();
        ss.bind(address);

        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer buf = ByteBuffer.wrap("Hi \n".getBytes(StandardCharsets.UTF_8));

        for (; ; ) {
            try {
                // 阻塞
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel client = serverSocketChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, buf.duplicate());
                        System.out.println("Accepted connection from " + client);
                    }
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0)
                                break;
                        }
                        client.close();
                    }
                } catch (IOException ex) {
                    key.channel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                        // do something
                    }
                }
            }
        }
    }
}
