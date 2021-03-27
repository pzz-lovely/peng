package mldn.nio.echo;

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
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(PORT));
        socketChannel.configureBlocking(false);
        //打开一个选择器，随后所有的SocketChannel都要注册到此选择器中
        Selector selector = Selector.open();
        //将当前serverSocketChannel统一注册到此选择器上
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        int keySelector = 0;
        while ((keySelector = selector.select()) > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                if (selectionKey.isAcceptable()) { //是否是连接状态
                    SocketChannel clientChannel = socketChannel.accept();//获取连接
                    if (clientChannel != null) {
                        executorService.submit(new SocketClientChannelThread(clientChannel));   //提交任务
                    }
                }
                selectionKeyIterator.remove();
            }
        }
        executorService.shutdown();
        socketChannel.close();
    }
}
