package mldn.nio;

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

/**
 * @Auther lovely
 * @Create 2020-04-28 20:46
 * @Description todo
 */
public class EchoServer {
    public static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        //打开一个服务器的Socket的连接通道
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(PORT));
        server.configureBlocking(false);

        //打开一个选择器，随后所有的Channel都要注册到此选择器中
        Selector selector = Selector.open();
        //将当前的server统一注册到Selector中，接受统一的管理
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动，该程序在" + PORT + "端口上监听，等待客户端连接...");
        //所有的连接处理都需要被Selector所管理，也就是说，只要有新的用户连接，那么就通过Selector处理
        int keySelector = 0;
        while ((keySelector = selector.select()) > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionIter = selectionKeys.iterator();
            while (selectionIter.hasNext()) {
                SelectionKey selectionKey = selectionIter.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel client = server.accept();
                    if (client != null) {
                        service.submit(new SocketChannelThread(client));
                    }
                }
                selectionIter.remove();
            }
        }
        service.shutdown();
        server.close();
    }
}
