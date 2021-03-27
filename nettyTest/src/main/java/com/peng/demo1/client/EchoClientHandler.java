package com.peng.demo1.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @Auther lovely
 * @Create 2020-07-24 14:06
 * @Description
 * 跟写服务器一样，提供ChannelInboundHandler来处理所有任务。
 *  channelActive() - 服务器的连接被建立后调用
 *  channelRead0() - 服务器发送数据后  调用
 *  exceptionCaught() - 捕获一个异常时调用
 *
 *  建立连接后该channelActive()方法被调用一次。
 *  channelRead0() 这个方法会在接收数据时被调用。注意，由服务器所发送的消息可以块的形式被接收。即，服务器发送5个字节是不是保证所有的5个戒子会立刻收到 - 即使是只有5个字节channelRead0()
 *  方法可以调用两次，第一次用一个ByteBuf(Netty的字节容器）装载3个字节和第二次一个ByteBuf装载2个字节。唯一要保证的是，该字节的按照它们发送的顺序分别被接收。（注意，只是真实的，只有面向流的协议如TCP）
 *
 *
 *  SimpleChannelInboundHandler vs ChannelInboundHandler
 *  何时用这两个要看具体业务的需要，在客户端，当channelRead0()完成，我们已经拿到的入站的信息。当方法返回时，SimpleChannelInboundHandler会小心的释放对
 *  ByteBUf（保存信息）的引用，而在EchoServerHandler我们需要将入站的信息返回给发送着，由于write()是异步的，在channelRead()
 *  返回时，可能还没有完成。所以我们使用ChannelInboundHandleAdapter无需释放信息，最后在channelReadCompleted()我们地要哪个ctx.writeAndFlush()来释放信息。
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    // 服务器的连接被建立后
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty HelloWorld", CharsetUtil.UTF_8));
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
