package mldn.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @Auther lovely
 * @Create 2020-04-28 21:33
 * @Description todo
 */
public class EchoHandler implements CompletionHandler<Integer,ByteBuffer> {
    private AsynchronousSocketChannel client;
    private boolean exit = false;

    public EchoHandler(AsynchronousSocketChannel client) {
        this.client = client;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        String readMessage = new String(buffer.array(), 0, buffer.remaining());
        System.err.println("服务器读取到数据" + readMessage);
        String resultMessage = "【Echo】" + readMessage;
        if ("exit".equals(readMessage)) {
            resultMessage = "下次再见";
            this.exit = true;
        }
        //数据消息
        echoWrite(resultMessage);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        this.closeClient();
    }



    private void closeClient(){
        System.out.println("客户端连接有误，中断与此客户端的处理");
        try{
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void echoWrite(String result) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put(result.getBytes());
        buffer.flip();//切换为读模式
        this.client.write(buffer,buffer,new CompletionHandler<Integer,ByteBuffer>(){

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (attachment.hasRemaining()) {
                    EchoHandler.this.client.write(buffer, buffer, this);
                }else{
                    if (EchoHandler.this.exit == false) {
                        ByteBuffer readBuffer = ByteBuffer.allocate(100);
                        EchoHandler.this.client.read(readBuffer, readBuffer, new EchoHandler(EchoHandler.this.client));
                    }
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                EchoHandler.this.closeClient();

            }
        });
    }
}
