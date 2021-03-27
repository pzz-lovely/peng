/*
package nio;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

*/
/**
 * @Auther lovely
 * @Create 2020-04-02 11:27
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 *//*

public class ChatServer {
    private final int DEFAULT_PORT = 8888;
    private final String QUIT = "quit";
    private ServerSocketChannel server;
    private Selector selector;
    private ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer wBuffer = ByteBuffer.allocate(1024);
    private Charset charset = Charset.forName("UTF-8");

    private int port;

    public ChatServer() {
        this(8888);
    }

    public ChatServer(int port) {
        this.port = port;
    }

    private void start() {
        //创建ServletSocketChannels
        try{
            server = ServerSocketChannel.open();
            //设置为非阻塞
            server.configureBlocking(false);
            //设置端口号
            server.socket().bind(new InetSocketAddress(port));
            */
/*server.bind(new ServerSocket(new InetAddress(port)));*//*

            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("启动服务器，监听端口:" + port);
            //select调用是阻塞的  监听事件
            while (selector.select() != -1) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.stream().forEach(key ->{
                    //处理被触发的事件
                    handles(key);
                });
                selectionKeys.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            close(selector);
        }
    }

    public boolean readyToQuit(String msg) {
        //检查是否要退出
        return QUIT.equalsIgnoreCase(msg);
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void handles(SelectionKey key) throws IOException {
        //Accept事件 和客户端建立连接
        if (key.isAcceptable()) {
            //获得 已经注册的 serverSocketChannel
            SocketChannel client = (SocketChannel) key.channel();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            System.out.println( getClientName(client)+"]连接上服务器");
        }
        //Read事件 客户端发送了消息
        else if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            String fwdMsg = receive(client);
            if (fwdMsg.isEmpty()) {
                //客户端异常
                key.cancel(); //取消掉，不在监听这个通道上socketChannel
                selector.wakeup();//重新监听这个通道上的所有事件
            }else{
                forwardMessage(client, fwdMsg);
                //检查用户是否退出
                if (readyToQuit(fwdMsg)) {
                    key.cancel();
                    selector.wakeup();
                }
            }
        }
    }

    private String receive(SocketChannel client) throws IOException {
        rBuffer.clear();
        client.read(rBuffer);
    }

    private String getClientName(SocketChannel client) {
        return "客户端[" + client.socket().getPort() ;
    }

}
*/
