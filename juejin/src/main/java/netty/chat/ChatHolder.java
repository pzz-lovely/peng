package netty.chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ҵ��ʵ��
 */
public class ChatHolder {
    //
    static final Map<SocketChannel, String> USER_MAP = new ConcurrentHashMap<>();

    /**
     * ����Ⱥ��
     * @param socketChannel �ͻ���
     */
    static void join(SocketChannel socketChannel) {
        //���˼���ͷ���һ�� id
        String user_id = "�û�" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        send(socketChannel, "���idΪ" + user_id + "\r\n");
        for (SocketChannel client : USER_MAP.keySet()) {
            send(client, user_id + "������Ⱥ��");
        }
        USER_MAP.put(socketChannel, user_id);
    }

    /**
     * �˳�Ⱥ��
     * @param socketChannel
     */
    static void quit(SocketChannel socketChannel) {
        String userId = USER_MAP.get(socketChannel);
        send(socketChannel, "���˳���Ⱥ��\r\n");
        USER_MAP.remove(socketChannel);
        for (SocketChannel client : USER_MAP.keySet()) {
            if (client != socketChannel) {
                send(client,userId+" �˳���Ⱥ��");
            }
        }
    }


    /**
     * ��ɢ˵������
     * @param socketChannel
     * @param content
     */
    static void propagate(SocketChannel socketChannel, String content) {
        String userId = USER_MAP.get(socketChannel);
        for (SocketChannel client : USER_MAP.keySet()) {
            if (socketChannel != client) {
                send(client, userId + " : " + content + "\r\n");
            }
        }
    }


    /**
     * ������Ϣ
     * @param socketChannel Ҫ���͵� �Ŀͻ���
     * @param msg ��Ϣ
     */
    static void send(SocketChannel socketChannel, String msg) {
        try{
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());
            buffer.flip();
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
