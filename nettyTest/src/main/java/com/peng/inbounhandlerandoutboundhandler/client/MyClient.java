package com.peng.inbounhandlerandoutboundhandler.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author lovely
 * @Create 2020-09-20 8:37
 * @Description todo
 */
public class MyClient {
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try{
            bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new MyClientInitializer());


            ChannelFuture future = bootstrap.connect(new InetSocketAddress(8088)).sync();


            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }

    }
}