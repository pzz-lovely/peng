package mldn.classLoader.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther lovely
 * @Create 2020-04-09 13:38
 * @Description todo
 */
public class MessageServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("开启服务器");
        Socket client = server.accept();
        File classFile = new File("F:" + File.separator + "Message.class");
        InputStream in = new FileInputStream(classFile);
        byte[] data = new byte[200];
        int len = 0;
        OutputStream out = client.getOutputStream();
        while ((len = in.read(data)) != -1) {
            out.write(data, 0, len);
        }
        client.isOutputShutdown();
        server.close();
    }
}
