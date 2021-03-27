package com.peng.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther lovely
 * @Create 2020-08-04 14:24
 * @Description 实现客户端发送请求，服务器给予响应
 */
public class HelloNettyServer {
    public static void main(String[] args)  {
        // 创建一组线程池组
        // 主线程池：用于接收客户端的请求链接，不做任何处理
        EventLoopGroup group1 = new NioEventLoopGroup();
        // 从线程池：主线程会把任务交给它，让其做任务
        EventLoopGroup group2 = new NioEventLoopGroup();
        try{
            // 创建服务器启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group1, group2).channel(NioServerSocketChannel.class) //设置nio双向通道
                    .childHandler(new HelloNettyServerInitializer());
            // 启动服务，并且设置端口号，同时启动方法为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            // 监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();       //
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group1.shutdownGracefully();
            group2.shutdownGracefully();
        }
    }
}
