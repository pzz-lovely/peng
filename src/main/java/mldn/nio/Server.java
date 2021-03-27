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
        //��һ��������socketͨ��
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9999));
        //��һ��ѡ�����������е�Channel��Ҫע�ᵽ��ѡ������
        Selector selector = Selector.open();
        //����ǰ��ServerSocketChannelͳһע�ᵽSelector������ͳһ����
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("�����������ɹ�");
        //���е����Ӵ�����Ҫ��Selector������Ҳ����˵��ֻҪ���µ��û����ӣ���ô��ͨ��Selector����
        int keySelect = 0;          //��������״̬
        while ((keySelect = selector.select()) > 0) {   //select()ѡ��һ������Ӧͨ��׼���ý���I / O�����ļ���
            Set<SelectionKey> selectionKeys = selector.selectedKeys();  //��ȡȫ������ͨ��
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel clientChannel = server.accept();  //�ȴ�����
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
