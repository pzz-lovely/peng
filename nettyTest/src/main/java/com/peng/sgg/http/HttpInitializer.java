package com.peng.sgg.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author lovely
 * @Create 2020-09-15 19:26
 * @Description todo
 */
public class HttpInitializer extends ChannelInitializer<SocketChannel> {
    //
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 加入一个Netty，提供httpServerCodec -> codec

        // Netty提供的http 编码 解码器
        pipeline.addLast("MyHTTPServer", new HttpServerCodec());

        //
        pipeline.addLast("MyTestHttpServerHandler", new NettyHttpServerHandler());
    }

}