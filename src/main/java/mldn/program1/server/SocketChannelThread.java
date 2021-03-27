package mldn.program1.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelThread implements Runnable {
    private SocketChannel clientSocketChannel ;             //客户端处理线程
    private boolean flag = true;

    public SocketChannelThread(SocketChannel clientSocketChannel) {
        this.clientSocketChannel = clientSocketChannel;
    }
    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        try{
            while (this.flag) {
                //提前做出清空
                buffer.clear();
                int readCount = this.clientSocketChannel.read(buffer);      //接收客户端的消息
                String readMessage = new String(buffer.array(), 0, readCount).trim();
                System.out.println("服务器接收到信息" + readMessage);
                String writeMessage = "0.0" + readMessage + "\n";
                if ("exit".equals(readMessage)) {
                    writeMessage = "再见0.0";
                    this.flag = false;
                }
                buffer.clear();
                buffer.put(Byte.parseByte(writeMessage));
                buffer.flip();
                this.clientSocketChannel.write(buffer);
            }
            this.clientSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
