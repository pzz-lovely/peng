package com.peng.hello;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Auther lovely
 * @Create 2020-08-04 14:42
 * @Description 自定义助手类
 */
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 获取Channel
        Channel channel = ctx.channel();
        if (msg instanceof HttpRequest) {
            System.out.println("ClientChanel remoteAddress:" + channel.remoteAddress());
            // 定义向客户端发送数据内容
            ByteBuf byteBuf = Unpooled.copiedBuffer("<h1>hello netty</h1>", StandardCharsets.UTF_8);
            // 构建http response
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            // 为响应增加数据类型和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, byteBuf.readableBytes());

            // 把响应渲染到客户端页面上
            ctx.writeAndFlush(response);
        }
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel 注册");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel 移除");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("助手类添加");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("助手类移除");
    }
}
