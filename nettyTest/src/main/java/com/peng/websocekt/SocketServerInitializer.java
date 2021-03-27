package com.peng.websocekt;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Auther lovely
 * @Create 2020-08-04 15:51
 * @Description todo
 */
public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // websocket 基于http协议，所需要的http，编解码器
        pipeline.addLast(new HttpServerCodec());
        // 在http上有一些数据流产生，有大有小，我们对其进行处理。
        pipeline.addLast(new ChunkedWriteHandler());
        // 对httpMessage进行聚合处理，聚合成request成response
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        // 本handler会处理一些繁重复杂的事情
        //
        pipeline.addLast(new WebSocketServerProtocolHandler("/peng"));
        pipeline.addLast(new ChatHandler());

    }
}
