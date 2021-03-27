package com.peng.sgg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author lovely
 * @Create 2020-09-10 16:12
 * @Description
 * Scattering：将数据写入到buffer时，可以采用Buffer数组，依次写入
 * Gathering：从Buffer读取数据时，也可以采用Buffer数，依次读
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(8088));

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        // 等待客户端连接
        SocketChannel socketChannel = server.accept();

        int messageLength = 8;  // 接收8个字节

        // 循环读取
        while (true) {
            int byteRead = 0 ;
            while(byteRead < messageLength){
                long readBytes = socketChannel.read(byteBuffers);
                // 累计读取的字节数
                byteRead += readBytes;
                System.out.println("byteRead=" + byteRead);
                Arrays.asList(byteBuffers).stream().map(buffer -> {
                    return "position="+buffer.position()+",limit="+buffer.limit();
                }).forEach(System.out::println);
            }

            // 将所有的Buffer进行flip，并进行打印
            Arrays.asList(byteBuffers).forEach(Buffer::flip);


            int byteWrite = 0;
            while(byteWrite < messageLength){
                long l = socketChannel.write(byteBuffers);
                byteWrite += 1;
            }

            Arrays.asList(byteBuffers).forEach(Buffer::clear);

            System.out.println("byteRead=" + byteRead + ",byteWrite" + byteWrite + ",messageLength" + messageLength);

        }
    }
}