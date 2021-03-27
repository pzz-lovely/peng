package mldn.aio.echo.server;

import java.io.IOException;

public class AioEchoServer {
    public static void main(String[] args) throws IOException {
        new Thread(new AioServerThread()).start();
    }
}
