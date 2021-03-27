package chapter4.web;

import threads.pool.DefaultThreadPool;
import threads.pool.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Web处理器
 */
public class SimpleHttpServer {

    //处理HttpRequestHandler的线程池
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(11);
    static ServerSocket server;
    //SimpleHttpServer的根路径
    static String basePath;
    //服务器监听端口
    static int port = 8080;

    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String path) {
        basePath = path;
    }

    public static void start() throws IOException {
        server = new ServerSocket(port);
        while (true) {
            Socket socket = server.accept();
            if (socket != null) {
                //接受一个客户端Socket，生一个HttpRequestHandler 放入到线程池执行
                threadPool.execute(new HttpRequestHandler(socket));
            }
        }
    }


    static class HttpRequestHandler implements Runnable{

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;  //响应到客户端
            InputStream in = null;  //用于读图片格式
            try{
                //获取输入流 用于读取 请求信息
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                //由相对路径计算出绝对路径
                String filePath = basePath +header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                //如果请求资源的后缀为jpg或者ico，则读取资源并输出
                if (filePath.endsWith("jpg")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0 ;
                    while ((i = in.read()) != -1) {
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    System.out.println();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length:" + array.length);
                    System.out.println("image length : "+array.length);
                    out.println("");
                    socket.getOutputStream().write(array);
                    //输出响应到 客户端 为图片的格式
                }else{      //为html格式
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    System.out.println("完整路径 "+basePath+filePath);
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type:text/html;charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        out.println(line);
                    }
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            }finally{
                close(br,in,reader,out);
            }
        }

        private static void close(Closeable... closeables) {
            if (closeables != null) {
                for(Closeable closeable : closeables)
                    try{
                        if (closeable != null) {
                            closeable.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
