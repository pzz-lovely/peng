package com.peng.reactor2;

import com.peng.recator.ReactorHandler;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Handler;

/**
 * @Auther lovely
 * @Create 2020-09-02 11:31
 * @Description todo
 */
public class MthreadReactor implements  Runnable {
    Selector[] selectors = new Selector[2];
    int next =  0;
    ServerSocketChannel serverSocket;

    MthreadReactor(){
        try {
            serverSocket = ServerSocketChannel.open();
            selectors[0] = Selector.open();
            selectors[1] = Selector.open();
            serverSocket.bind(new InetSocketAddress(80));

            serverSocket.configureBlocking(false);

            // 分步处理，接收accept事件
            SelectionKey sk = serverSocket.register(selectors[0], SelectionKey.OP_ACCEPT);
            sk.attach(new Acceptor());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()) {
                for (int i = 0; i < 2; i++) {
                    selectors[i].select();
                    Set<SelectionKey> selectionKeys = selectors[i].selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                    while (keyIterator.hasNext()) {
                        dispatch((SelectionKey) (keyIterator.next()));
                    }
                    selectionKeys.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void dispatch(SelectionKey key) {
        Runnable r = (Runnable) key.attachment();
        if(r != null)
            r.run();

    }

    class Acceptor implements Runnable{
        @SneakyThrows
        public synchronized void run(){
            try {
                SocketChannel connection = serverSocket.accept();
                if (connection != null)
                    new ReactorHandler(selectors[next], connection);
                if (++next == selectors.length) next = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
