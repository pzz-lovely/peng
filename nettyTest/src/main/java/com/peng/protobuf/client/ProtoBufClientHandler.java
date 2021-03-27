package com.peng.protobuf.client;

import com.peng.protobuf.MyStudent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.channels.SocketChannel;

/**
 * @Author lovely
 * @Create 2020-09-18 14:56
 * @Description todo
 */
public class ProtoBufClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyStudent.Student student = MyStudent.Student.newBuilder().setName("0.0").setId(1).build();
        ctx.writeAndFlush(student);
    }
}