package imooc.inet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
    //和本线程相关的Socket
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket=socket;
    }

    @Override
    //线程执行的操作，响应客户端的请求
    public void run() {
        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        try{
            //获取输入流
            is = socket.getInputStream(); //字节输入流
            isr = new InputStreamReader(is);//将字节流转换为 字符流
            br = new BufferedReader(isr);                //为输入流添加缓冲
            String info = null;
            while (((info = br.readLine()))!=null) {
                System.out.println("客户端提交的信息 : "+info);
            }
            socket.shutdownInput();   //关闭输入流
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("服务器发出消息0.0");
            pw.flush();
            //关闭资源
            pw.close();
            isr.close();;
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
