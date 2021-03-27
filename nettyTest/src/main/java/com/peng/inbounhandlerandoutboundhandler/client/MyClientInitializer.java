package com.peng.inbounhandlerandoutboundhandler.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author lovely
 * @Create 2020-09-20 8:40
 * @Description todo
 */
public class MyClientInitializer  extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();


        pipeline.addLast(new MyLongToByteEncoder());

        pipeline.addLast(new MyClientHandler());

    }
}