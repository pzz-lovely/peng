package mldn.program2.client;

import mldn.program1.util.InputUtil;

import java.io.IOException;

public class AIOEchoClient {
    public static void main(String[] args) throws IOException {
        AIOClientThread client = new AIOClientThread();
        new Thread(client).start();
        while(client.sendMessage(InputUtil.getString("请输入要发送的消息:")));
    }
}
