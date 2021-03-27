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
    private static ServerSocketChannel server; //f服务器
    private static Selector selector;          //选择器
    private static ExecutorService service = Executors.newFixedThreadPool(5);


    public static void main(String[] args) throws IOException {
        initServer();
        while (selector.select() > 0) { //阻塞在选择器上
            handler();
        }
    }

    private static void initServer() throws IOException {
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9999));
        selector = Selector.open();
        server.configureBlocking(false);        //设置为非阻塞模式
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Start Server");
    }

    private static void handler() throws IOException {
        SocketChannel client = null;
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
        while (selectionKeyIterator.hasNext()) {
            SelectionKey selectionKey = selectionKeyIterator.next();
            if (selectionKey.isAcceptable()) {    //是否是连接事件
                ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                client = server.accept();
                System.out.println("accept new conn : " + client.getRemoteAddress());
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                client = (SocketChannel) selectionKey.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int length = client.read(buffer); //将数据读取到 缓冲区中
                if (length > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    //将数据读到byte数组中
                    buffer.get(bytes);
                    //解析数据
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

