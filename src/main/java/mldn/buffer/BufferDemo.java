package mldn.buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        File file = new File("D:"+File.separator+"info.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();                 //创建管道流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int count = 0;
        while ((count = channel.read(buffer))!=-1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                bos.write(buffer.get());
            }
            buffer.clear();
        }
        System.out.println(new String(bos.toByteArray(),"gbk"));
        channel.close();
        fis.close();
    }
}
