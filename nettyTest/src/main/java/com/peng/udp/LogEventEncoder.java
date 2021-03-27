package com.peng.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Auther lovely
 * @Create 2020-09-01 14:15
 * @Description todo
 */
public class LogEventEncoder extends MessageToMessageDecoder<LogEvent> {

    private final InetSocketAddress source;

    public LogEventEncoder(InetSocketAddress source) {
        this.source = source;
    }


    @Override
    protected void decode(ChannelHandlerContext ctx, LogEvent logEvent, List<Object> out) throws Exception {
        byte[] file = logEvent.getLogfile().getBytes(StandardCharsets.UTF_8);
        byte[] msg = logEvent.getMsg().getBytes(StandardCharsets.UTF_8);

        ByteBuf buf = ctx.alloc().buffer(file.length + msg.length + 1);

        buf.writeBytes(file);
        buf.writeByte(LogEvent.SEPARATOR);
        buf.writeBytes(msg);
        out.add(new DatagramPacket(buf, source));
    }
}
