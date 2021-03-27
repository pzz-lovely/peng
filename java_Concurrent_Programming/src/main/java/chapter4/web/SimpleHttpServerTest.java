package chapter4.web;

import sun.java2d.pipe.SpanShapeRenderer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleHttpServerTest {
    public static void main(String[] args) throws IOException {
        SimpleHttpServer server = new SimpleHttpServer();
        SimpleHttpServer.setBasePath("D:\\imooc");
        SimpleHttpServer.start();
    }
}
