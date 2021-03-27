package com.peng.dubborpc.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @Author lovely
 * @Create 2020-09-21 8:44
 * @Description todo
 */
public class RPCHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext context;

    private String result;  // 返回的结果

    private String para;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive被调用");
        context = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ChannelRead 被调用");
        result = msg.toString();

        notify();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call1 被调用");
        context.writeAndFlush(para);

        // 进行wait
        wait();

        System.out.println("call2 被调用");
        return result;
    }


    public void setPara(String para) {
        System.out.println("setPara");
        this.para = para;
    }
}