package com.peng.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author lovely
 * @Create 2020-09-17 9:48
 * @Description TextWebSocketFrame类型，表示一个文本帧
 */
public class MyTextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器收到消息:" + msg.text());


        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间："+format.format(new Date())+"，收到的消息："+msg.text()));
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // id 表示唯一的值，LongText是唯一的,ShortText不是唯一的
        System.out.println("HandlerAdded被调用了" + ctx.channel().id().asLongText());
        System.out.println("HandlerAdded被调用了" + ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HandlerRemove被调用");
        System.out.println("HandlerAdded被调用了" + ctx.channel().id().asLongText());
        System.out.println("HandlerAdded被调用了" + ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}