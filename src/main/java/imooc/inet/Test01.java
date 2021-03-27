package imooc.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress类
 */
public class Test01 {
    public static void main(String[] args) throws UnknownHostException {
        //获取本机的InetAddress实例
        InetAddress address = InetAddress.getLocalHost();
        System.out.println("计算机名:" + address.getHostName());
        System.out.println("ip地址:" + address.getHostAddress());
    }
}
