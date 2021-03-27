package netty.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioServer {
    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        //监听accept()
        server.bind(new InetSocketAddress(9999));
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                try{
                    System.out.println("accept new conn :"+result.getRemoteAddress());
                    //再次监听accept事件
                    server.accept(null, this);
                    //消息的处理
                    while (true) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //将数据读入到buffer中
                        Future<Integer> future = result.read(buffer);
                        if (future.get() > 0) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            //将数据写入到 byte数组中
                            buffer.get(bytes);
                            String data = new String(bytes,"UTF-8");
                            //换行符会当成另一条消息传过来
                            if (data.equals("\r\n")) {
                                continue;
                            }
                            if ("exit".equals(data)) {
                                result.close();
                                break;
                            }else {
                                System.out.println("received msg : "+data);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failed");
            }
        });
        System.in.read();
    }
}
