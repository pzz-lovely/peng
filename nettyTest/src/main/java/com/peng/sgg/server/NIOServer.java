package com.peng.sgg.server;

import com.peng.server.Endpoint;

import java.io.IOException;

/**
 * @Author lovely
 * @Create 2020-09-10 18:50
 * @Description todo
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Endpoint endpoint = new Endpoint();
        endpoint.start(8088);
    }
}