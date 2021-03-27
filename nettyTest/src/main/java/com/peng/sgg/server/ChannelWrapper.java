package com.peng.sgg.server;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lovely
 * @Create 2020-09-10 20:17
 * @Description todo
 */
public class ChannelWrapper {
    private Map<String, SocketChannel> channels;
    private AtomicInteger channelCounter;

    public ChannelWrapper() {
        channelCounter = new AtomicInteger();
        channels = new ConcurrentHashMap<>();
    }

    public String addChannel(SocketChannel socketChannel) {
        String id = "client:" + channelCounter.incrementAndGet();
        channels.put(id, socketChannel);
        return id;
    }

    public SocketChannel getChannel(String id) {
        return channels.get(id);
    }

    public Map<String,SocketChannel> excludeChannels(String id) {
        Map<String,SocketChannel> channelMap = new HashMap<>();
        synchronized (this) {
            channels.forEach((s, socketChannel) -> {
                if (!s.equalsIgnoreCase(id)) {
                    channelMap.put(id,socketChannel);
                }
            });
        }
        return channelMap;
    }

}