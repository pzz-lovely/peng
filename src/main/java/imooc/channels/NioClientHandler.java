package imooc.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端线程类，专门接收服务器端响应信息
 */
public class NioClientHandler implements Runnable {
    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //获取可用的channel数量
                int readChannels = selector.select();   //select()本身是一个阻塞方法，只有当监听的事件已经就绪了 他才会返回
                if (readChannels == 0) continue;
                //获取可用channel的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    //selectionKey实例
                    SelectionKey selectionKey = iterator.next();
                    //移除set当前的selectionKey
                    iterator.remove();

                    //根据就绪状态，调用对应方法处理业务逻辑‘
                    //可读事件
                    if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        //循环读取服务器端响应信息
        String response = "";
        while (socketChannel.read(byteBuffer) > 0) {
            //切换buffer为读模式
            byteBuffer.flip();
            //读取buffer中的内容
            response += Charset.forName("utf-8").decode(byteBuffer);
        }
        //将服务器响应信息打印到本地
        if (response.length() > 0) {
            System.out.println(response);
        }
    }
}

