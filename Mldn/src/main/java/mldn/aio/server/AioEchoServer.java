package mldn.aio.server;

import java.io.IOException;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:05
 * @Description todo
 */
public class AioEchoServer {
    public static void main(String[] args) throws IOException {
        new Thread(new AioServerThread()).start();
    }
}
