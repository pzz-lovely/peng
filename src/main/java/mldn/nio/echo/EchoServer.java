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
        //��һ��ѡ������������е�SocketChannel��Ҫע�ᵽ��ѡ������
        Selector selector = Selector.open();
        //����ǰserverSocketChannelͳһע�ᵽ��ѡ������
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        int keySelector = 0;
        while ((keySelector = selector.select()) > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                if (selectionKey.isAcceptable()) { //�Ƿ�������״̬
                    SocketChannel clientChannel = socketChannel.accept();//��ȡ����
                    if (clientChannel != null) {
                        executorService.submit(new SocketClientChannelThread(clientChannel));   //�ύ����
                    }
                }
                selectionKeyIterator.remove();
            }
        }
        executorService.shutdown();
        socketChannel.close();
    }
}
