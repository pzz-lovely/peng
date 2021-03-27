package com.peng.sgg.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import sun.java2d.pipe.ParallelogramPipe;

/**
 * @Author lovely
 * @Create 2020-09-14 13:51
 * @Description todo
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        // 创建BossGroup 和 workerGroup
        // bossGroup只是处理连接请求，真正的和客户端业务处理，会交给workerGroup完成
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        ServerBootstrap bootstrap = new ServerBootstrap();

        // 使用链式编程来进行设置
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,
                128)    // 设置线程队列得到连接个数
                .childOption(ChannelOption.SO_KEEPALIVE, true)// 设置保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new NettyServerHandler());
                    }
                });    // 给workerGroup 的 EventLoop 设置处理

        System.out.println("...服务器 is ready");


        // 绑定一个端口并且同步，生成了一个ChannelFuture对象
        ChannelFuture cf = bootstrap.bind(8088).sync();

        // 对关闭通道进行监听
        cf.channel().closeFuture().sync();
    }
}