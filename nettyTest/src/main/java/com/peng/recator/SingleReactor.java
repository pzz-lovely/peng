package com.peng.recator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther lovely
 * @Create 2020-09-02 10:29
 * @Description todo
 */
public class SingleReactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel server;

    SingleReactor(int port) throws IOException {
        selector = Selector.open();
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(80));
        SelectionKey selectionKey = server.register(selector, SelectionKey.OP_ACCEPT);
        server.configureBlocking(true);

        // 添加到附加对象中
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()) {
                // 阻塞
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                while (keyIterator.hasNext()) {
                    keyIterator.remove();
                    SelectionKey key = keyIterator.next();
                    Acceptor acceptor = (Acceptor) key.attachment();
                    acceptor.accept(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class Acceptor  {
        public void accept(SelectionKey key) {
            try {
                if (key.isAcceptable()) {
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    new ReactorHandler(selector, client);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
