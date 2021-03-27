package com.peng.inbounhandlerandoutboundhandler.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.List;

/**
 * @Author lovely
 * @Create 2020-09-20 8:32
 * @Description todo
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     *
     * @param ctx 上下文对象
     * @param in 入站的ByteBuf
     * @param out list集合，将解码后的数据传给下一个Handler处理
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println(in);
        // 因为 long 8个字节，需要判断有8个字节，才能读取一个 long
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}