package com.peng.group;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author lovely
 * @Create 2020-09-17 8:52
 * @Description 心跳
 */
public class GroupClientHandler extends SimpleChannelInboundHandler<ByteBuf>  {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        Channel channel = ctx.channel();


        System.out.println(msg.toString());

    }


}