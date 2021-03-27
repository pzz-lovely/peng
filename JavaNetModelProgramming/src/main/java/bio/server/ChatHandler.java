package bio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Auther lovely
 * @Create 2020-04-01 13:28
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 服务器处理
 */
public class ChatHandler implements Runnable {
    private ChatServer chatServer;
    private Socket socket;

    public ChatHandler(ChatServer chatServer, Socket socket) {
        this.chatServer = chatServer;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //存储新上线的任务
            chatServer.addClient(socket);
            //读取用户发送的消息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = null;
            while ((msg = reader.readLine()) != null) {
                String fwdMsg = "客户端发送的消息[" + socket.getPort() + "]:" + msg+"\n";
                System.out.println(fwdMsg);
                //将消息转发给聊天室里在线的其他用户
                chatServer.forwardMessage(socket, fwdMsg);
                if(chatServer.readyToQuit(msg))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                chatServer.removeClient(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
