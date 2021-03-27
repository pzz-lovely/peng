package com.peng.group;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Arrays;

/**
 * @Author lovely
 * @Create 2020-09-17 8:49
 * @Description todo
 */
public class GroupClient {
    private int port;

    public GroupClient(int port) {
        this.port = port;
    }


    public void run() throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workerGroup).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());


                pipeline.addLast(new GroupClientHandler());
            }
        });


        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", port)).sync();

        channelFuture.channel().closeFuture().sync();

        workerGroup.shutdownGracefully();
    }


    public static void main(String[] args) throws InterruptedException {
        new GroupClient(8088).run();
    }
}