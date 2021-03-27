package tomcat.server.peng.server;

import tomcat.server.peng.serverThread.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {
    private ServerSocket server;
    private int port;

    public Server(){
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void receive(){
        try{
            Socket client = server.accept();
            ServerThread thread = new ServerThread(client);
            thread.start();
            thread.join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
