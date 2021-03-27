package bio.server;



import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther lovely
 * @Create 2020-04-01 10:40
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 服务器
 */
public class ChatServer {
    private final int DEFAULT_PORT = 8888;
    private final String QUIT = "quit";
    private ServerSocket server ;
    private Map<Integer,Writer> connectedClients;


    public ChatServer(){
        connectedClients = new ConcurrentHashMap<>();
    }

    public void addClient(Socket socket) throws IOException {
        if (socket != null) {
            //获取客户单的 端口
            int port = socket.getPort();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            connectedClients.put(port, writer);
            System.out.println("客户端["+port+"]以连接服务器");
        }
    }

    public void removeClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            if (connectedClients.containsKey(port)) {
                connectedClients.get(port).close();
            }
            connectedClients.remove(port);
            System.out.println("客户端["+port+"]已断开连接");
        }
    }

    public void forwardMessage(Socket socket,String fwMsg) throws IOException {
        for (Integer id : connectedClients.keySet()) {
            if (!id.equals(socket.getPort())) {
                Writer writer = connectedClients.get(id);
                writer.write(fwMsg);
                writer.flush();
            }
        }
    }

    public void start(){
        try {
            //绑定监听端口
            server = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口");
            while (true) {
                //以阻塞的方式获取客户端连接
                Socket socket = server.accept();
                System.out.println("客户端已连接:"+socket.getPort());
                //创建 ChatHandler线程
                new Thread(new ChatHandler(this, socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean readyToQuit(String msg) {
        //检查是否要退出
        return QUIT.equalsIgnoreCase(msg);
    }

    public void close(){
        if (server != null) {
            try {
                server.close();
                System.out.println("关闭服务器");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start();
        server.close();
    }
}
