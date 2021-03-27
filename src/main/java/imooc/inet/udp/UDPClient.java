package imooc.inet.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 客户端
 */
public class UDPClient {
    public static void main(String[] args) throws IOException {
        //定义服务器的地址，端口号，数据
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8000;
        byte[] data = "用户名:admin;密码:123".getBytes();
        //创建数据报，包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        //创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();
        //向服务器发送数据报
        socket.send(packet);
    }
}
