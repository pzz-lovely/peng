package nio.acceptor;

import nio.NioEndpoint;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @Auther lovely
 * @Create 2020-04-26 11:28
 * @Description 处理连接
 */
public class NioAcceptor implements Runnable {
    private NioEndpoint endpoint;


    public NioAcceptor(NioEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public void run() {
        while (endpoint.isRunning()) {
            try {
                //获取客户端端连接
                SocketChannel client = endpoint.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
