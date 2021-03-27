package com.peng.webscoket2.initializer;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * @Auther lovely
 * @Create 2020-09-01 12:46
 * @Description todo
 */
public class SecureChatServerInitializer extends ChatServerInitializer {

    private final SslContext sslCtx;

    public SecureChatServerInitializer(ChannelGroup group, SslContext sslCtx) {
        super(group);
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        super.initChannel(ch);
        SSLEngine engine = sslCtx.newEngine(ch.alloc());
        engine.setUseClientMode(false);
        ch.pipeline().addLast(new SslHandler(engine));
    }
}
