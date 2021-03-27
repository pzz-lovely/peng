package imooc.inet;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //创建客户端Socket
        Socket clientSocket = new Socket("localhost", 8000);
        OutputStream os = clientSocket.getOutputStream();
        //将输出流 变成打印流
        PrintWriter pw = new PrintWriter(os);
        pw.write("用户名:admin密码:123");
        pw.flush();
        clientSocket.shutdownOutput();
        //获取输入流，用来读取服务器的响应
        InputStream is = clientSocket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String result = br.readLine();
        while (result != null) {
            System.out.println(result);
            result = br.readLine();
        }
        clientSocket.shutdownInput();
        //关闭资源
        br.close();
        isr.close();
        is.close();
        pw.close();
        os.close();
        clientSocket.close();
    }
}
