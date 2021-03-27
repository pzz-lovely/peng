package com.peng.sgg.http;

import com.peng.sgg.netty.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author lovely
 * @Create 2020-09-15 19:18
 * @Description todo
 */
public class NettyHttpServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new HttpInitializer());


        ChannelFuture cf = bootstrap.bind(8080).sync();

        // boosGroup.shutdownGracefully();
        // workerGroup.shutdownGracefully();
    }
}