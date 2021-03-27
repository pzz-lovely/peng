package netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    private static ExecutorService service = Executors.newFixedThreadPool(5);


    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9999));
        System.out.println("Start server");
        while (true) {
            getSocket(server);
        }
        /*service.shutdown();
        server.close();
        System.out.println("server is closed ");*/
    }


    private static void getSocket(ServerSocket server) throws IOException {
        Socket client = server.accept();
        if (client != null) {
            service.submit(new ClientThread(client));
        }
    }

    static class ClientThread implements Runnable {

        private Socket client;

        public ClientThread(Socket client) {
            this.client = client;
        }
        @Override
        public void run() {
            boolean flag = true;
            BufferedReader br = null;
            while (flag) {
                try{
                    br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    System.out.println("Connection to server address " + client.getRemoteSocketAddress());
                    String msg = null;
                    while((msg = br.readLine()) != null){
                        System.out.println(Thread.currentThread()+"data received : " + msg);
                    }
                    if (msg != null && "exit".equalsIgnoreCase(msg)) {
                        flag = false;
                        client.close();;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
