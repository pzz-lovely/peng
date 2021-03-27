package imooc.inet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信 ，实现用户登录
 * 服务器端
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //创建一个服务器端的Socket
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("服务器即将启动,等待客户端连接");
        //接收到的客户端口 调用accept()方法
        Socket clientSocket = serverSocket.accept();
        //获取输入流
        InputStream is = clientSocket.getInputStream(); //字节输入流
        InputStreamReader isr = new InputStreamReader(is);//将字节流转换为 字符流
        BufferedReader br = new BufferedReader(isr);                //为输入流添加缓冲
        String info = null;
        while (((info = br.readLine()))!=null) {
            System.out.println("客户端提交的信息 : "+info);
        }
        clientSocket.shutdownInput();   //关闭输入流
        OutputStream os = clientSocket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("服务器发出消息0.0");
        pw.flush();
        //关闭资源
        pw.close();
        isr.close();;
        is.close();
        clientSocket.close();
        serverSocket.close();
    }
}
