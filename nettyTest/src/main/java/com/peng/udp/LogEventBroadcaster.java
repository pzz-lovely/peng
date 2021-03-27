package com.peng.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

/**
 * @Auther lovely
 * @Create 2020-09-01 14:22
 * @Description todo
 */
public class LogEventBroadcaster {
    private final EventLoopGroup group;
    private final Bootstrap bootstrap;
    private final File file;

    public LogEventBroadcaster(InetSocketAddress address, File file) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST, true).handler(new LogEventEncoder(address));
        this.file = file;
    }

    public void run()throws Exception{
        Channel ch = bootstrap.bind(0).sync().channel();
        long pointer = 0 ;
        for (; ; ) {
            long len = file.length();
            if(len < pointer)
                // file was rest
                pointer = len;
            else if (len > pointer) {
                // Content was added
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                raf.seek(pointer);
                String line ;
                while ((line = raf.readLine()) != null) {
                    ch.writeAndFlush(new LogEvent(null,line , file.getAbsolutePath(), -1));
                }

                pointer = raf.getFilePointer();
                raf.close();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
        }
    }

    public void stop() {
        group.shutdownGracefully();
    }


    public static void main(String[] args) {
        LogEventBroadcaster broadcaster = new LogEventBroadcaster(new InetSocketAddress("255.255.255.255", 80),
                new File("D:\\task\\peng2\\nettyTest\\src\\main\\java\\com\\peng\\udp\\a.txt"));
        try{
            broadcaster.run();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            broadcaster.stop();
        }
    }
}
