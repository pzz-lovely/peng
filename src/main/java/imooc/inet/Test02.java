package imooc.inet;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL常用方法
 */
public class Test02 {
    public static void main(String[] args) {
        //创建一个URL实例
        try {
            URL imooc = new URL("http://www.imooc.com");
            URL url = new URL(imooc,"/index.html?username=tom#test");//?表示的是参数 #后面表示的是锚点
            System.out.println("协议 : "+url.getProtocol());
            System.out.println("主机：" + url.getHost());
            System.out.println("端口号:"+url.getPort());
            System.out.println("文件路径:"+url.getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
