package com.peng.protobuf.server;

import com.peng.protobuf.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author lovely
 * @Create 2020-09-18 16:26
 * @Description todo
 */
public class ProtoBufServerHandler extends SimpleChannelInboundHandler<MyMessage.Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage.Message msg) throws Exception {
        MyMessage.Message.DataType dataType = msg.getDataType();

        System.out.println(dataType.getNumber());
    }
}