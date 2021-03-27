package mldn.channel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeChannelDemo {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        new Thread(()->{
            Pipe.SourceChannel source = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(50);
            try{
                int count = source.read(buffer);
                buffer.flip();
                System.out.println("{接收端}" + new String(buffer.array(), 0, count));
            } catch (IOException e) {
                e.printStackTrace();
            }
        },"接收端").start();
        new Thread(()->{
            Pipe.SinkChannel sink = pipe.sink();
            ByteBuffer buffer = ByteBuffer.allocate(50);
            buffer.put(" 发送给数据的是啥?0.0 ".getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                try{
                    sink.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },"发送端").start();
    }
}
