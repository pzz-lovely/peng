package mldn.nio.echo;

import mldn.program1.server.SocketChannelThread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClientChannelThread implements Runnable {
    private SocketChannel client;
    private boolean flag = true;

    public SocketClientChannelThread(SocketChannel socketChannel) throws IOException {
        this.client = socketChannel;
        System.out.println("�ͻ������ӳɹ�  ; �ÿͻ��˵ĵ�ַΪ " + socketChannel.getRemoteAddress());
    }


    @Override
    public void run() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (this.flag) {
            byteBuffer.clear();
            try {
                int readCount = this.client.read(byteBuffer);
                String readMessage = new String(byteBuffer.array(), 0, readCount).trim();
                System.out.println("���������յ�����ϢΪ : " + readMessage);
                String writeMessage = "[ECHO] " + readMessage + "\n";
                if ("exit".equalsIgnoreCase(writeMessage)) {
                    writeMessage = "�������˳�";
                    this.flag = false;
                }
                byteBuffer.clear();
                byteBuffer.put(writeMessage.getBytes());
                byteBuffer.flip();
                client.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
