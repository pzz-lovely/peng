package com.peng.demo1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Auther lovely
 * @Create 2020-07-24 10:11
 * @Description
 * 一个服务器handler：这个组件实现了服务器的业务逻辑，决定了连接创建后和接收到消息后该如何处理
 * Bootstrapping：这个是配置服务器的启动代码。最少需要设置服务器绑定的端口，用来监听连接请求
 *
 * 引导服务器
 *  监听和接收进行的请求
 *  配置Channel来通知一个关于入站消息的EchoServerHandler实例
 *
 *  Transport(传输)
 *  在网络的多层视图协议里面，传输层提供了用于端与端口或主机到主机之间的 通信服务。互联网通信的基础是 TCP传输。我们指的是一个传输的实现，它是大多等同于 TCP ，除了一些由 Java NIO 的实现提供了服务器端的性能增强。
 *
 *  创建步骤：
 *      1. 创建ServerBootStrap 实例来引导服务器并随后绑定
 *      2. 创建并分配一个NioEventLoopGroup实例来处理事件的处理，如接受新的连接和读/写数据
 *      3. 指定本地InetSocketAddress给服务器绑定
 *      4. 通过EchoSeverHandler实例给每一个新的Channel实例化
 *      5. 最后调用ServerBootStrap.bind() 绑定服务器
 */
public class EchoServer {
    private static final  int port = 8282;

    public static void main(String[] args) throws InterruptedException {
        new EchoServer().start();
    }

    public void start() throws InterruptedException {
        // 接受和处理新连接
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(group, new NioEventLoopGroup())
                    // 指定使用NIO传输Channel
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new EchoInitializer());


              ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listener on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
