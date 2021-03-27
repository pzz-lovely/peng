package chapter4.web;

import threads.pool.DefaultThreadPool;
import threads.pool.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Web������
 */
public class SimpleHttpServer {

    //����HttpRequestHandler���̳߳�
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(11);
    static ServerSocket server;
    //SimpleHttpServer�ĸ�·��
    static String basePath;
    //�����������˿�
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
                //����һ���ͻ���Socket����һ��HttpRequestHandler ���뵽�̳߳�ִ��
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
            PrintWriter out = null;  //��Ӧ���ͻ���
            InputStream in = null;  //���ڶ�ͼƬ��ʽ
            try{
                //��ȡ������ ���ڶ�ȡ ������Ϣ
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                //�����·�����������·��
                String filePath = basePath +header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                //���������Դ�ĺ�׺Ϊjpg����ico�����ȡ��Դ�����
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
                    //�����Ӧ�� �ͻ��� ΪͼƬ�ĸ�ʽ
                }else{      //Ϊhtml��ʽ
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    System.out.println("����·�� "+basePath+filePath);
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
