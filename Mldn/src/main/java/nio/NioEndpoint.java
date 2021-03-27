package nio;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Auther lovely
 * @Create 2020-04-26 11:24
 * @Description todo
 */
public class NioEndpoint {
    private ServerSocketChannel server;
    private volatile boolean running;

    public SocketChannel accept() throws IOException {
        return server.accept();
    }


    public boolean isRunning() {
        return running;
    }

}
