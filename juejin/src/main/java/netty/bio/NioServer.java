package netty.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer {
    private static ServerSocketChannel server; //f������
    private static Selector selector;          //ѡ����
    private static ExecutorService service = Executors.newFixedThreadPool(5);


    public static void main(String[] args) throws IOException {
        initServer();
        while (selector.select() > 0) { //������ѡ������
            handler();
        }
    }

    private static void initServer() throws IOException {
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9999));
        selector = Selector.open();
        server.configureBlocking(false);        //����Ϊ������ģʽ
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Start Server");
    }

    private static void handler() throws IOException {
        SocketChannel client = null;
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
        while (selectionKeyIterator.hasNext()) {
            SelectionKey selectionKey = selectionKeyIterator.next();
            if (selectionKey.isAcceptable()) {    //�Ƿ��������¼�
                ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                client = server.accept();
                System.out.println("accept new conn : " + client.getRemoteAddress());
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                client = (SocketChannel) selectionKey.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int length = client.read(buffer); //�����ݶ�ȡ�� ��������
                if (length > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    //�����ݶ���byte������
                    buffer.get(bytes);
                    //��������
                    String data = new String(bytes, "utf-8").replace("\r\n", "");
                    if ("exit".equalsIgnoreCase(data)) {
                        selectionKey.cancel();
                        client.close();
                    }else {
                        System.out.println("server received data : "+data);
                    }
                }
            }
            selectionKeyIterator.remove();
        }
    }
}

