package mldn.aio.echo.client;

import java.io.IOException;

public class AioEchoClient {
    public static void main(String[] args) throws IOException {
        AioClientThread clientThread = new AioClientThread();
        new Thread(clientThread).start();
        while (clientThread.sendMessage("������Ҫ���͵���Ϣ��")) {

        }
    }
}
