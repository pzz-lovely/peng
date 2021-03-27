package mldn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static int PORT = 9999;

    private static void run() throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        //打开一个服务器socket通道
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9999));
        //打开一个选择器，随所有的Channel都要注册到此选择器上
        Selector selector = Selector.open();
        //将当前的ServerSocketChannel统一注册到Selector，接受统一管理
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");
        //所有的连接处理都需要被Selector所管理，也就是说，只要有新的用户连接，那么就通过Selector处理
        int keySelect = 0;          //接受连接状态
        while ((keySelect = selector.select()) > 0) {   //select()选择一组其相应通道准备好进行I / O操作的键。
            Set<SelectionKey> selectionKeys = selector.selectedKeys();  //获取全部连接通道
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel clientChannel = server.accept();  //等待接受
                    if (clientChannel != null) {
                        service.submit(new SocketThread(clientChannel));
                    }
                }
                selectionKeyIterator.remove();
            }
        }
        service.shutdown();
        server.close();
    }

    public static void main(String[] args) {

    }
}
