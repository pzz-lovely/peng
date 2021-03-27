package com.peng.demo1.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Auther lovely
 * @Create 2020-07-24 14:28
 * @Description
 *
 * 一个Bootstrap被创建来初始化客户端
 * 一个NioEventLoopGroup实例被分配给处理该事件的处理，这包括创建新的连接和处理入站和出站数据
 * 一个InetSocketAddress为连接服务器而建
 * 一个EchoClientHandler将被安装在pipeline当连接完成时
 * 之后BootStrap.connect() 被调用 连接到远程的 服务器
 */
public class EchoClient {
    private final String host = "localhost";
    private final int port = 8282;

    public static void main(String[] args) throws InterruptedException {
        new EchoClient().start();
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new EchoClientInitializer());
            ChannelFuture sync = b.connect().sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
