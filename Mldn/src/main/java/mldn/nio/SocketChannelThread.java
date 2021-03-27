package mldn.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Auther lovely
 * @Create 2020-04-28 20:37
 * @Description 客户端处理线程
 */
public class SocketChannelThread implements Runnable {

    private SocketChannel clientChannel;        //客户端处理线程
    private boolean flag = true;

    public SocketChannelThread(SocketChannel clientChannel) throws IOException {
        this.clientChannel = clientChannel;
        System.out.println("客户端连接成功，该客户端地址为" + clientChannel.getRemoteAddress());
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(50);
        try{
            while (this.flag) {
                //由于可能重复使用一个buffer，所有使用前需要将其做出清空处理
                buffer.clear();
                int readCount = this.clientChannel.read(buffer);
                String readMessage = new String(buffer.array(), 0, readCount).trim();
                System.out.println("服务器收到信息:" + readMessage);
                String writeMessage = "【Echo】" + readMessage + "\n";
                if (readMessage.equals(readMessage)) {
                    writeMessage = "下次再见";
                    this.flag = false;
                }
                buffer.clear();
                buffer.put(writeMessage.getBytes());
                buffer.flip();
                this.clientChannel.write(buffer);
            }
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
