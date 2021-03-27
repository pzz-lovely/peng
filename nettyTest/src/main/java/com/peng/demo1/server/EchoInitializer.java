package com.peng.demo1.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @Auther lovely
 * @Create 2020-07-24 12:17
 * @Description
 * 当一新的连接被接受，一个新的子Channel将被创建
 * 将我们自定义的ServerHandler的实例 添加到ChannelPipeine中。
 */
public class EchoInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("EchoHandler",new EchoServerHandler());
    }
}
