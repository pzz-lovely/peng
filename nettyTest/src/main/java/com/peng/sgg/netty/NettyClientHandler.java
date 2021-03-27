package com.peng.sgg.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Author lovely
 * @Create 2020-09-15 16:36
 * @Description todo
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client " + ctx);

        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,server:()", CharsetUtil.UTF_8));

    }
}