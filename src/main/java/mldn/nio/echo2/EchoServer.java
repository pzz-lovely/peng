package mldn.nio.echo2;

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
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(9999));
        //新建一个选择器
        Selector selector = Selector.open();
        //注册选择器
        server.register(selector, SelectionKey.OP_ACCEPT);
        while ((selector.select()) > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = server.accept();
                    if (socketChannel != null) {
                        service.submit(new ClientHandler(socketChannel));
                    }
                }
                selectionKeyIterator.remove();
            }
        }
        service.shutdown();
        server.close();
    }
}
