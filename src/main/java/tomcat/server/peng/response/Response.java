package tomcat.server.peng.response;

import java.io.*;
import java.net.Socket;

public class Response {
    private Socket client;  //��Ӧ�Ŀͻ���
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
        //��Ӧ(�ѷ��������е���Դ�������ͻ���)
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
            url����������� 1.Ϊ��:����Ĭ����Դ 2.���մ��ڣ����ظ���Դ 3.���ղ����ڣ����ش�����Ϣ
         */
        if (url.equals("/")) {

        }
    }
}
