package tomcat.server.peng.serverThread;

import tomcat.server.peng.request.Request;
import tomcat.server.peng.response.Response;

import java.net.Socket;

public class ServerThread extends Thread {
    private Socket client;
    public ServerThread(Socket client){this.client = client;}

    @Override
    public void run() {
        Request request = new Request(client);
        Response response = new Response(client);
        response.forward(request.getUrl());
    }
}
