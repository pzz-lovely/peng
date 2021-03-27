package com.peng.protobuf.client;

import com.peng.protobuf.MyStudent;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.net.InetSocketAddress;

/**
 * @Author lovely
 * @Create 2020-09-18 13:59
 * @Description todo
 */
public class ProtoBufClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try{
            bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();

                    pipeline.addLast(new ProtobufVarint32FrameDecoder());


                    pipeline.addLast(new ProtobufDecoder(MyStudent.Student.getDefaultInstance()));


                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());


                    pipeline.addLast(new ProtoBufClientHandler());
                }
            });



            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8088));

            channelFuture.channel().closeFuture().sync();


        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}