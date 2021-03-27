package com.peng.sgg.server;


import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @Author lovely
 * @Create 2020-09-10 18:55
 * @Description todo
 */
public class Acceptor implements Runnable {
    private final Endpoint endpoint;

    public Acceptor(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public void run() {
        while (endpoint.isRunning()) {
            try {
                SocketChannel client = endpoint.accept();
                if(client == null)
                    continue;
                endpoint.register(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}