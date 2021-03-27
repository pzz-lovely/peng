package com.peng.sgg.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author lovely
 * @Create 2020-09-15 16:24
 * @Description todo
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        // 客户端需要一个事件循环组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        // 创建客户端启动对象
        Bootstrap bootstrap = new Bootstrap();


        try{
            // 设置相关参数
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(null);
                }
            });

            System.out.println("客户端 ok ...");

            // 启动客户端去连接服务器端
            ChannelFuture cf = bootstrap.connect(new InetSocketAddress("localhost", 8088)).sync();

            cf.channel().closeFuture().sync();
        }finally{
            eventLoopGroup.shutdownGracefully();
        }
    }
}