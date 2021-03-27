package mldn.program2.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 实现回调处理
 */
public class EchoHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private boolean exit = false;

    public EchoHandler(AsynchronousSocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        String readMessage = new String(buffer.array(),0,buffer.remaining()).trim();
        System.err.println("服务器读取到数据 " + readMessage);
        String resultMessage = " [0.0] "+readMessage;
        if ("exit".equalsIgnoreCase(readMessage)) {
            resultMessage = "再见";
            this.exit = true;
        }
        this.echoWrite(resultMessage);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        this.closeClient();
    }

    private void closeClient(){
        System.out.println("客户端连接错误，中断与此客户端的处理!");
        try{
            clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void echoWrite(String result) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put(result.getBytes());
        buffer.flip();
        this.clientChannel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (buffer.hasRemaining()) {                //有数据
                    EchoHandler.this.clientChannel.write(buffer, buffer, this);//输出
                }else {
                    if (EchoHandler.this.exit == false) {   //继续下一次操作
                        ByteBuffer readBuffer = ByteBuffer.allocate(100);
                        EchoHandler.this.clientChannel.read(readBuffer, readBuffer, new EchoHandler(EchoHandler.this.clientChannel));
                    }
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                EchoHandler.this.closeClient();     //关闭通道
            }
        });
    }
}
