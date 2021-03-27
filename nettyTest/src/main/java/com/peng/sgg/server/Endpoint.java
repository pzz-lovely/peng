package com.peng.sgg.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lovely
 * @Create 2020-09-10 18:52
 * @Description todo
 */

public class Endpoint {
    private ServerSocketChannel server;
    private volatile boolean isRunning;
    private List<Handler> handlers ;
    private int handlerIndex;

    private final ChannelWrapper channelWrapper;
    private Acceptor acceptor;

    public Endpoint() throws IOException {
        handlers =  new ArrayList<>();
        isRunning = true;
        handlerIndex = 0;
        channelWrapper = new ChannelWrapper();
        acceptor = new Acceptor(this);
        server = ServerSocketChannel.open();

    }



    public void start(int port) throws IOException {
        server.bind(new InetSocketAddress(port));

        server.configureBlocking(true);

        handlers.add(new Handler("Handler1", channelWrapper));
        handlers.add(new Handler("Handler2", channelWrapper));


        Thread acceptor = new Thread(this.acceptor, "Acceptor");
        acceptor.setDaemon(false);
        acceptor.start();

        for (int i = 0; i < handlers.size(); i++) {
            Thread thread = new Thread(handlers.get(i), handlers.get(i).getName());
            thread.start();
        }
    }


    public SocketChannel accept() throws IOException {
        return server.accept();
    }

    public boolean isRunning() {
        return isRunning;
    }

    private Handler getHandler(){
        return handlers.get(Math.abs((handlerIndex++) % handlers.size()));
    }

    public void register(SocketChannel socketChannel) throws IOException {
        server.configureBlocking(false);
        String id = channelWrapper.addChannel(socketChannel);
        Handler handler = getHandler();
        handler.register(id);
        // 唤醒在其它线程 阻塞的selector
        handler.getSelector().wakeup();
        server.configureBlocking(true);
    }


}

