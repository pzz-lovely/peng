package mldn.nio.echo2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientHandler implements Runnable {
    private SocketChannel clientChannel;
    private boolean flag = true;
    public ClientHandler(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (flag) {
            try {
                buffer.clear();
                clientChannel.read(buffer);
                String readMessage = new String(buffer.array(), 0, buffer.remaining());
                if ("exit".equalsIgnoreCase(readMessage)) {
                    System.out.println("�������˳�");
                    flag = false;
                }
                System.out.println("���յ���ֵ : "+readMessage);
                String result = "Echo : " + readMessage;
                buffer.clear();
                buffer.put(result.getBytes());
                buffer.flip();  //ת��Ϊ��ģʽ
                clientChannel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
