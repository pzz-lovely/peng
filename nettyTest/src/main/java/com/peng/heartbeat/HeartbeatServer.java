package com.peng.heartbeat;

import com.peng.group.GroupServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author lovely
 * @Create 2020-09-17 9:19
 * @Description todo
 */
public class HeartbeatServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();


        ServerBootstrap serverBootstrap = new ServerBootstrap();


        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 获取pipeline
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("IdleStateEvent", new IdleStateHandler(5, 7, 8, TimeUnit.SECONDS));


                        pipeline.addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                if (evt instanceof IdleStateEvent) {
                                    IdleStateEvent stateEvent = (IdleStateEvent) evt;


                                    switch (stateEvent.state()) {
                                        case READER_IDLE:
                                            System.out.println("读空闲");
                                            break;
                                        case WRITER_IDLE:
                                            System.out.println("写空闲");
                                            break;
                                        case ALL_IDLE:
                                            System.out.println("读写空闲");
                                            break;
                                    }
                                }
                            }
                        });
                    }
                });


        ChannelFuture cf = serverBootstrap.bind(8088).sync();


        cf.channel().closeFuture().sync();

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}