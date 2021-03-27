package com.peng.demo1.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Auther lovely
 * @Create 2020-07-24 10:12
 * @Description
 * Echo Server 将会将接受到的数据的拷贝发送给客户端。因此，我们需要实现ChannelInboundHandler接口，用来定义处理入站事件的放。
 * ChannelInboundAdapter类提供默认ChannelInboundAdapter的实现，所以只需要覆盖下面的方法：
 *  channelRead() - 每个信息入站都会调用
 *  channelReadComplete() - 通知处理器最后的channelRead() 是当前批处理中的最后一条消息时调用
 *  exceptionCaught() - 读操作时捕获到异常时调用
 *
 *
 * @Sharable 标识这类的实例可以在channel 里面共享
 *
 * 覆盖 exceptionCaught 使我们能够应对任何 Throwable 的子类型。在这种情况下我们记录，并关闭所有可能处于未知状态的连接。它通常是难以
 * 从连接错误中恢复，所以干脆关闭远程连接。当然，也有可能的情况是可以从错误中恢复的，所以可以用一个更复杂的措施来尝试识别和处理
 * 这样的情况。
 *
 * 如果异常没有被捕获，会发生什么？
 * <em>
 *     每个Channel都有一个关联的ChannelPipe，它代表了ChannelHandler实例的链。适配器处理的实现只是将一个处理方法调用转发到链中的下一个处理器。因此，如果一个Netty
 *     应用程序不应该覆盖exceptionCaught，那么这些错误将最终到达ChannelPipeline，并且结束警告被记录。处于这个原因，你应该至少实现exceptionCaught的ChannelHandler。
 * </em>
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Server received: " + byteBuf.toString(CharsetUtil.UTF_8));
        // 将所接收的消息返回发送者。这是没有冲刷数据
        ctx.write(byteBuf);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 冲刷所有待审消息到远程节点。关闭通道，操作完成
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 关闭通道
        ctx.close();
    }
}
