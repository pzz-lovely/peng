package mldn.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * �ͻ��˴����߳�
 */
public class SocketThread implements Runnable {
    SocketChannel clientChannel ;       //�ͻ���
    private boolean flag = true;        //ѭ�����

    /**
     * ÿ��һ���ͻ��� ���ӷ����� ���½�һ���߳� ��ʼ�� �ͻ���ͨ��
     * @param clientChannel �ͻ���ͨ��
     */
    public SocketThread(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }
    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);        //����������
        try{
            while (this.flag) {
                //���ڿ����ظ�ʹ��buffer������ʹ��ǰ��Ҫ���������������
                buffer.clear();
                int readCount = this.clientChannel.read(buffer);    //���ͻ��˷��͵����ݶ�����������
                String readMessage = new String(buffer.array());
                String writeMessage = " ���͵����� 0.0 : "+readMessage;
                if ("exit".equalsIgnoreCase(readMessage)) {
                    writeMessage = "�´��ټ�";
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
