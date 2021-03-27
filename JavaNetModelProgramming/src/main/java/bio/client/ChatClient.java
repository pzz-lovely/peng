package bio.client;


import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Auther lovely
 * @Create 2020-04-01 10:40
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 客户端
 */
public class ChatClient {
    private final String DEFAULT_SERVER_HOST = "127.0.0.1";
    private final int DEFAULT_SERVER_PORT = 8888;
    private final String QUIT = "quit";


    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;

    //发送消息给服务器
    public void send(String msg) throws IOException {
        if (!client.isOutputShutdown()) {
            System.out.println(client.isOutputShutdown());
            writer.write(msg);
        }
    }

    //从服务器接收消息
    public String receive() throws IOException {
        String msg  = null;
        if (!client.isInputShutdown()) {
            msg = reader.readLine();
        }
        return msg;
    }

    public boolean readyToQuit(String msg) {
        return QUIT.equalsIgnoreCase(msg);
    }

    public void start(){
        try {
            client = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
            //创建IO流
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            // 处理用户的输入
            new Thread(new UserInputHandler(this)).start();
            //监听 服务器转发的消息
            String msg = null;
            while ((msg = reader.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void close(){
        try{
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("关闭客户端");
    }


}
