package com.peng.websocekt;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @Auther lovely
 * @Create 2020-08-04 16:07
 * @Description 用于处理消息的handler
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开，Channel对应的长Id为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，Channel对应的短Id为：" + ctx.channel().id().asShortText());
        // clients.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 获取客户端所传输的消息
        String content = msg.text();
        System.out.println("接收到的数据:" + content);

        // 将数据刷新到客户端上
        clients.writeAndFlush(new TextWebSocketFrame("【服务器在:】" + LocalDateTime.now() + " 接收到消息，消息内容为:" + content));
    }
}
