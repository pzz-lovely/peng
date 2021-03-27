package com.peng.server;

import java.io.IOException;

/**
 * @Auther lovely
 * @Create 2020-09-03 14:42
 * @Description todo
 */
public class Server {
    public static void main(String[] args) {
        try {
            new Endpoint().start(8888);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
