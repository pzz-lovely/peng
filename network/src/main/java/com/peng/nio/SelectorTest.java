package com.peng.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther lovely
 * @Create 2020-05-21 15:11
 * @Description todo
 */
public class SelectorTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        Selector selector = Selector.open();
        server.bind(new InetSocketAddress(8888));
        server.register(selector, SelectionKey.OP_ACCEPT);

        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            if (key.isAcceptable()) {
                // acceptable
            } else if (key.isConnectable()) {
                // connect
            } else if (key.isReadable()) {
                //read
            } else if (key.isWritable()) {
                // write
            }
            keyIterator.remove();
        }
    }
}
