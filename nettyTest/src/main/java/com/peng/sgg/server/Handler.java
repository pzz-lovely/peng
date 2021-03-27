package com.peng.sgg.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author lovely
 * @Create 2020-09-10 19:19
 * @Description todo
 */

public class Handler implements Runnable {
    private String name;
    private volatile Selector selector;
    private ChannelWrapper channelWrapper;
    private Dispatcher dispatcher;
    private MessageContainer messageContainer;

    public Handler(String name,ChannelWrapper channelWrapper) throws IOException {
        this.name = name;
        selector = Selector.open();
        this.channelWrapper = channelWrapper;
        dispatcher = new Dispatcher();
        messageContainer = new MessageContainer();
    }

    public void register(String id) throws IOException {
        SocketChannel socketChannel = channelWrapper.getChannel(id);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, id);
        messageContainer.setRecentlyMessage(id,id+"已跟服务器建立连接");
    }


    @Override
    public void run() {
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = keys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isReadable()) {
                        System.out.println(messageContainer.getRecentlyMessage());
                        dispatcher.channelRead(messageContainer.getRecentlyMessage(), (SocketChannel) key.channel());
                    } else if (key.isWritable()) {
                        String id = (String) key.attachment();
                        Map<String, SocketChannel> channelMap = channelWrapper.excludeChannels(id);
                        messageContainer.setRecentlyMessage(id, dispatcher.channelWrite(channelWrapper.getChannel(id)));
                        channelMap.forEach((s, socketChannel) -> {
                            try {
                                dispatcher.channelRead(messageContainer.getRecentlyMessage(),socketChannel);
                            }  catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public Selector getSelector() {
        return selector;
    }
}