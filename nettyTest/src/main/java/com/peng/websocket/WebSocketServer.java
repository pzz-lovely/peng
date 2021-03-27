package com.peng.websocket;

import com.peng.group.GroupServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author lovely
 * @Create 2020-09-17 9:40
 * @Description todo
 */
public class WebSocketServer {
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
                        ChannelPipeline pipeline = ch.pipeline();


                        // 因为基于HTTP协议，使用HTTP的编码和解码器
                        pipeline.addLast(new HttpServerCodec());

                        // 以块的方式写，
                        pipeline.addLast(new ChunkedWriteHandler());

                        // http数据在传输对的过程中是分段，HttpObjectAggregator，可以将多个段聚合
                        // 这就是为什么，当浏览器发大量数据时，会发出多次http请求
                        pipeline.addLast(new HttpObjectAggregator(8192));

                        /*
                         * 对应webSocket，它的数据是以帧(frame)传输的
                         * webSocketFrame 有六个子类
                         * WebSocketServerProtocolHandler 核心功能是将http协议升级为ws协议，保持长连接
                         */
                        pipeline.addLast(new WebSocketServerProtocolHandler("/peng"));

                        pipeline.addLast(new MyTextWebSocketFrameHandler());

                    }
                });


        ChannelFuture cf = serverBootstrap.bind(8088).sync();


        cf.channel().closeFuture().sync();

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}