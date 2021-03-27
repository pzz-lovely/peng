package com.peng.websocekt;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther lovely
 * @Create 2020-08-04 15:48
 * @Description todo
 */
public class WebSocketServer {
    public static void main(String[] args) {
        // 创建主从线程池
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();

        try{
            // 创建服务器类
            ServerBootstrap server = new ServerBootstrap();

            server.group(mainGroup, subGroup).channel(NioServerSocketChannel.class).childHandler(new SocketServerInitializer());

            ChannelFuture future = server.bind(8888).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }
}
