package tomcat.server.peng.request;

import tomcat.server.peng.util.GetParm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private Socket client;  //客户端请求的对象
    private BufferedReader br;
    private String url;     //请求资源
    private String method;  //请求方法
    private String protocal;//协议
    private Map<String,String> map; //参数列表
    //工具类解析参数
    private GetParm getParm;


    public Request(Socket client) {
        this.client = client;
        map = new HashMap<>();
        getParm = new GetParm();
        try{
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //读取第一行
            String firstLine = br.readLine();
            String[] split = firstLine.split(" ");
            //把提交方式，请求资源，协议取出
            method = split[0];
            url = split[1];
            System.out.println(url);
            protocal = split[2];
            //解析url，分析参数
            if (method.equalsIgnoreCase("get")) {
                if (url.contains("?")) {
                    String[] split2 = url.split("[?]");
                    url = split2[0];
                    //参数行
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

    // 获得get post 方法
    public String getMethod() {
        return method;
    }

    // 获得url
    public String getUrl() {
        return url;
    }
}
