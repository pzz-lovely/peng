package com.peng.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther lovely
 * @Create 2020-09-03 14:44
 * @Description 连接客户端的请求
 */
public class Acceptor implements Runnable {

    // 选择器 ，监听从客户端传过来的
    private Selector selector;

    private final Endpoint endpoint;

    public Acceptor(Endpoint endpoint) throws IOException {
        this.endpoint = endpoint;
        selector = Selector.open();
        // 注册连接事件
        endpoint.register(selector);
    }


    @Override
    public void run() {
        // 不断监听 从服务器发送的连接
        try{
            while (true) {
                // 选择 键 阻塞
                selector.select();
                // OP_ACCEPT OP_READ OP_WRITE OP_CONNECT 获取连接状态
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    // 获取 选择 的状态
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        SocketChannel client = endpoint.accept();
                        // 设置为非阻塞
                        client.configureBlocking(false);
                        // 将它 注册为读状态
                        client.register(selector, SelectionKey.OP_READ);
                        // 进行读写
                        endpoint.dispatch(client, key);
                    }

                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
