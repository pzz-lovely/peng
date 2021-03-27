package com.peng.sgg.client;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author lovely
 * @Create 2020-09-11 12:42
 * @Description todo
 */
public class ClientHandlerReader implements Runnable {

    private final SocketChannel socketChannel;
    private ByteBuffer buffer;
    private final Selector selector;
    public ClientHandlerReader(SocketChannel socketChannel, Selector selector) {
        this.selector = selector;
        buffer = ByteBuffer.allocate(512);
        this.socketChannel = socketChannel;
    }


    @Override
    public void run() {
        while (socketChannel.isOpen()) {
            try {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = keys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isReadable()) {
                        buffer.clear();
                        socketChannel.read(buffer);
                        buffer.flip();

                        System.out.println(new String(buffer.array(), StandardCharsets.UTF_8));
                    }
                    keyIterator.remove();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}