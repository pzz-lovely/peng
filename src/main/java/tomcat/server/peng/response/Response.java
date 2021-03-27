package tomcat.server.peng.response;

import java.io.*;
import java.net.Socket;

public class Response {
    private Socket client;  //响应的客户端
    private PrintStream ps;
    private String path = null;

    public Response(){}

    public Response(Socket client){
        this.client = client;
        try{
            ps = new PrintStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forward() {
        //响应(把服务器上有的资源流传到客户端)
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(path));
            ps.println("HTTP/1.1 200 OK");
            ps.println();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                ps.write(buf, 0, len);
                ps.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void forward(String url) {
        /*
            url处理三种情况 1.为空:返回默认资源 2.不空存在，返回该资源 3.不空不存在，返回错误信息
         */
        if (url.equals("/")) {

        }
    }
}
