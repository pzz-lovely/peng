package com.peng.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Scanner;

/**
 * @Auther lovely
 * @Create 2020-09-03 14:46
 * @Description 服务器入口类
 */
public class Endpoint {
    // 当作服务器
    private ServerSocketChannel server;
    private Acceptor acceptor;
    private BufferedReader reader;


    public void start(int port) throws IOException {
        // 开启服务
        server = ServerSocketChannel.open();
        // 绑定端口
        server.bind(new InetSocketAddress(port));
        server.configureBlocking(false);

        acceptor = new Acceptor(this);

        reader = new BufferedReader(new InputStreamReader(System.in));
        Thread thread = new Thread(acceptor);
        thread.setDaemon(true);
        thread.start();
        String msg = null;
        // 阻塞
        while ((msg =reader.readLine()) != null) {

        }
    }

    public void register(Selector selector) throws ClosedChannelException {
        server.register(selector, SelectionKey.OP_ACCEPT);
    }

    public SocketChannel accept() throws IOException {
        return server.accept();
    }

    // 转发事件
    public void dispatch(SocketChannel client,SelectionKey key) {
        try {
            new Handler(client).process(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
