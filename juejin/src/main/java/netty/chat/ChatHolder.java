package netty.chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 业务实现
 */
public class ChatHolder {
    //
    static final Map<SocketChannel, String> USER_MAP = new ConcurrentHashMap<>();

    /**
     * 加入群聊
     * @param socketChannel 客户端
     */
    static void join(SocketChannel socketChannel) {
        //有人加入就分配一个 id
        String user_id = "用户" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        send(socketChannel, "你的id为" + user_id + "\r\n");
        for (SocketChannel client : USER_MAP.keySet()) {
            send(client, user_id + "加入了群聊");
        }
        USER_MAP.put(socketChannel, user_id);
    }

    /**
     * 退出群聊
     * @param socketChannel
     */
    static void quit(SocketChannel socketChannel) {
        String userId = USER_MAP.get(socketChannel);
        send(socketChannel, "你退出了群聊\r\n");
        USER_MAP.remove(socketChannel);
        for (SocketChannel client : USER_MAP.keySet()) {
            if (client != socketChannel) {
                send(client,userId+" 退出了群聊");
            }
        }
    }


    /**
     * 扩散说话内容
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
     * 发送信息
     * @param socketChannel 要发送到 的客户端
     * @param msg 消息
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
