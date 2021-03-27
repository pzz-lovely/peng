package imooc.channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio服务器端
 */
public class NioServer {
    /**
     * 启动服务器方法
     */
    public void start() throws IOException {
        //1.创建selector选择器
        Selector selector = Selector.open();
        //2.通过serverSocketChannel创建channel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3.为channel通道绑定监听端口
        serverSocketChannel.bind(new InetSocketAddress(8000));
        //4.设置channel为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //5.将channel注册到selector上，监听连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);     //接收就绪
        System.out.println("服务器启动成功s");
        //6.循环等待新连接的连接
        while (true) {
            //获取可用的channel数量
            int readChannels = selector.select();   //select()本身是一个阻塞方法，只有当监听的事件已经就绪了 他才会返回
            if(readChannels == 0)continue;
            //获取可用channel的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //selectionKey实例
                SelectionKey selectionKey = iterator.next();
                //移除set当前的selectionKey
                iterator.remove();

                //根据就绪状态，调用对应方法处理业务逻辑‘
                //连接事件
                if (selectionKey.isAcceptable()) {
                    acceptHandler(serverSocketChannel, selector);
                }
                //可读事件
                if (selectionKey.isReadable()) {
                    readHandler(selectionKey,selector);
                }

            }
        }
    }

    /**
     * 接入事件处理器 连接
     * @param serverSocketChannel
     * @param selector
     * @throws IOException
     */
    private void acceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        //创建socketChannel
        SocketChannel socketChannel = serverSocketChannel.accept(); //接收
        //将socketChannel设置为非阻塞工作模式
        socketChannel.configureBlocking(false);
        //将channel注册到selector上，监听 可读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
        //回复客户端提示信息
        socketChannel.write(Charset.forName("utf-8").encode("你与聊天室里其他人都不是朋友关系，请注意隐私安全"));
    }

    /**
     * 刻度事件处理器
     * @param selectionKey
     * @param selector
     */
    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        //要从selectionKey中获取到已经就绪的channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //循环读取客户端请求信息
        String result = "";
        while (socketChannel.read(byteBuffer) > 0) {
            //切换buffer为读模式
            byteBuffer.flip();
            //读取buffer中的内容
            result += Charset.forName("utf-8").decode(byteBuffer);
        }
        //将客户端发送的请求信 广播给其他客户端
        if (result.length() > 0) {
            System.out.println("服务器收到客户端信息 :: "+result);
            broadCast(selector,socketChannel,result);
        }
    }

    /**
     * 广播其他客户端
     */
    private void broadCast(Selector selector,SocketChannel sourceChannel,String request){
        //获取到所有已接入的客户端channel
        Set<SelectionKey> selectionKeys = selector.keys();
        //循环向所有channel
        selectionKeys.forEach(selectionKey -> {
            Channel targetChannel = selectionKey.channel();
            //剔除发消息的客户端
            if (targetChannel instanceof SocketChannel && targetChannel != sourceChannel) {
                try {
                    //将消息发送到targetChannel客户端
                    ((SocketChannel) targetChannel).write(Charset.forName("utf-8").encode(request));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.start();
    }
}
