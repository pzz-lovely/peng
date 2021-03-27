package com.peng.hello;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @Auther lovely
 * @Create 2020-08-04 14:35
 * @Description 初始化器，channel注册之后，会执行里面的响应的初始化方法
 */
public class HelloNettyServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 通过SocketChannel，获取对应的管理
        ChannelPipeline pipeline = ch.pipeline();
        /*
         * 通过管道添加handler
         * HttpServerCodec：是由netty自己提供的助手类，可以理解为拦截器
         * 当请求到服务器，我们需要解码，响应客户端做编码
         */
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        pipeline.addLast("CustomHandler", new CustomHandler());
    }
}
