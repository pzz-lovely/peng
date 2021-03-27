package imooc.inet.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 服务器端，基于UPD的用户登录
 */
public class UPDServer {
    public static void main(String[] args) throws IOException {
        //创建服务器端DatagramSocket，指定端口
        DatagramSocket socket = new DatagramSocket(8000);
        //创建数据报，用于接收客户端发送的数据
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        //接受客户端发送的数据
        socket.receive(packet);//此方法在接收到数据报之前会一直阻塞
        //读取数据
        String info = new String(data, 0, packet.getLength());
        System.out.println("我是服务器，客户端说:" + info);
        //向客户端响应数据
        //定义客户端的地址，端口号，数据
        InetAddress inetAddress = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "欢迎!".getBytes();
        //创建数据报，包含响应的数据信息
        DatagramPacket packet1 = new DatagramPacket(data2, data2.length, inetAddress, port);
        //响应客户单
        socket.send(packet1);
        //关闭资源
        socket.close();
    }
}
