package com.peng.demo1.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @Auther lovely
 * @Create 2020-07-24 14:33
 * @Description todo
 */
public class EchoClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new EchoClientHandler());
    }
}
