package imooc.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NioClient {
    /**
     * 启动客户端
     */
    public void start(String nickName) throws IOException {
        //连接服务器端
        SocketChannel clientSocketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8000));
        //接收服务器端响应

        //新开线程，专门负责来接收服务器的响应数据
        //selector,SocketChannel ,注册selector上
        Selector selector = Selector.open();
        clientSocketChannel.configureBlocking(false);
        clientSocketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();
        //向服务器发送数据
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String request = input.nextLine();
            if (request != null && request.length() > 0) {
                clientSocketChannel.write(Charset.forName("utf-8").encode(nickName+" : "+request));
            }
        }
    }

    public static void main(String[] args) throws IOException {

    }
}
