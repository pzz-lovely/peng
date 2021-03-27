package com.peng.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

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
    private final ExecutorService service = Executors.newFixedThreadPool(2);
    private volatile boolean isAccept = false;
    private volatile Handler handler ;

    public void start(int port) throws IOException, InterruptedException {
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
        while (true) {
            if(isAccept){
                dispatch();
                TimeUnit.SECONDS.sleep(5);
            }
        }
    }

    private void dispatch()  {
        try {
            if(handler != null ){
                handler.process();
                isAccept = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void register(Selector selector) throws ClosedChannelException {
        server.register(selector, SelectionKey.OP_ACCEPT);
    }

    public SocketChannel accept() throws IOException {
        return server.accept();
    }


    public void execute(SocketChannel client,SelectionKey key) {
        handler = new Handler(client,key);
        isAccept = true;
    }
}
