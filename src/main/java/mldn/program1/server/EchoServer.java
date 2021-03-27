package mldn.program1.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    public static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        //执行人服务
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //打开一个服务器端的Socket人的连接通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        //打开一个选择器，随后所有的Channel都要注册到此选择器中
        Selector selector = Selector.open();
        //将当前的ServerSocketChannel统一注册到Selector中接收统一管理
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动程序，该程序在" + PORT + "端口上监听，等待客户端连接....");
        //所有连接处理都需要被Selector所管理，也就是说，只要有新用户连接，那么久通过Selector处理
        int keySelect = 0;
        while ((keySelect = selector.select()) > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {                          //模式为接收连接模式
                    SocketChannel clientChannel = serverSocketChannel.accept();//等待接收
                    if (clientChannel != null) {        //已经有连接了
                        executorService.submit(new SocketChannelThread(clientChannel));
                    }
                }
                keyIterator.remove();
            }
        }
        executorService.shutdown();
        serverSocketChannel.close();
    }
}
