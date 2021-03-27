package com.peng.protobuf.server;

import com.peng.protobuf.MyStudent;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @Author lovely
 * @Create 2020-09-18 13:55
 * @Description todo
 */
public class ProtoBufHandler extends SimpleChannelInboundHandler<MyStudent.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyStudent.Student msg) throws Exception {


        System.out.println(msg);
    }
}