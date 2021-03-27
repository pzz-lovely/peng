package mldn.aio.echo.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class EchoHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel clientChannel;
    private boolean exit = false;

    public EchoHandler(AsynchronousSocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        String readMessage = new String(attachment.array(), 0, attachment.remaining());
        System.out.println("服务器读取到数据 " + readMessage);
        String resultMessage = "Echo " + readMessage;
        if ("exit".equals(readMessage)) {
            resultMessage = "服务器退出";
            this.exit = true;
        }
    }


    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            closeClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void closeClient() throws IOException {
        System.out.println("客戶端连接有错误，中断与服务的处理");
        this.clientChannel.close();
    }

    private void echoWrite(String result) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(result.getBytes());
        buffer.flip();
        this.clientChannel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (buffer.hasRemaining()) {
                    EchoHandler.this.clientChannel.write(buffer, buffer, this); //输出
                }else {
                    if (EchoHandler.this.exit == false) {
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        EchoHandler.this.clientChannel.read(readBuffer, readBuffer, new EchoHandler(EchoHandler.this.clientChannel));
                    }
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    EchoHandler.this.closeClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


