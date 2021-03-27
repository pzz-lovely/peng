package mldn.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 客户端处理线程
 */
public class SocketThread implements Runnable {
    SocketChannel clientChannel ;       //客户端
    private boolean flag = true;        //循环标记

    /**
     * 每有一个客户端 连接服务器 就新建一个线程 初始化 客户端通道
     * @param clientChannel 客户端通道
     */
    public SocketThread(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }
    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);        //创建缓冲区
        try{
            while (this.flag) {
                //由于可能重复使用buffer，所有使用前需要将其进行做出清理
                buffer.clear();
                int readCount = this.clientChannel.read(buffer);    //将客户端发送的内容读到缓冲区中
                String readMessage = new String(buffer.array());
                String writeMessage = " 发送的数据 0.0 : "+readMessage;
                if ("exit".equalsIgnoreCase(readMessage)) {
                    writeMessage = "下次再见";
                    this.flag = false;
                }
                buffer.clear();
                buffer.put(writeMessage.getBytes());
                buffer.flip();
            }
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
