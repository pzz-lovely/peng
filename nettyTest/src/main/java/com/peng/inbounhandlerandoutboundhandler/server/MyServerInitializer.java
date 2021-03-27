package com.peng.inbounhandlerandoutboundhandler.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author lovely
 * @Create 2020-09-20 8:29
 * @Description todo
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 入站的Handler进行解码 MyByteToLongDecoder
        pipeline.addLast(new MyByteToLongDecoder());

        pipeline.addLast(new MyServerHandler());
    }
}