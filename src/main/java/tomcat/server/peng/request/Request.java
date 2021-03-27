package tomcat.server.peng.request;

import tomcat.server.peng.util.GetParm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private Socket client;  //�ͻ�������Ķ���
    private BufferedReader br;
    private String url;     //������Դ
    private String method;  //���󷽷�
    private String protocal;//Э��
    private Map<String,String> map; //�����б�
    //�������������
    private GetParm getParm;


    public Request(Socket client) {
        this.client = client;
        map = new HashMap<>();
        getParm = new GetParm();
        try{
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //��ȡ��һ��
            String firstLine = br.readLine();
            String[] split = firstLine.split(" ");
            //���ύ��ʽ��������Դ��Э��ȡ��
            method = split[0];
            url = split[1];
            System.out.println(url);
            protocal = split[2];
            //����url����������
            if (method.equalsIgnoreCase("get")) {
                if (url.contains("?")) {
                    String[] split2 = url.split("[?]");
                    url = split2[0];
                    //������
                    String property = split2[1];
                    map = getParm.getParm(property);
                }
            } else if (method.equalsIgnoreCase("post")) {
                int length = 0 ;
                while (br.ready()) {
                    String line = br.readLine();
                    if (line.contains("Content-Length")) {
                        String[] split2 = line.split(" ");
                        length = Integer.parseInt(split2[1]);
                    }
                    if (line.equals("")) {
                        break;
                    }
                }
                String info = null;
                char[] ch = new char[length];
                br.read(ch, 0, length);
                info = new String(ch, 0, length);
                map = getParm.getParm(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProtocal() {
        return protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Socket getClient() {
        return client;
    }

    // ���get post ����
    public String getMethod() {
        return method;
    }

    // ���url
    public String getUrl() {
        return url;
    }
}
