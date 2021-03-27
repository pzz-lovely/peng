package com.peng.recator;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @Auther lovely
 * @Create 2020-09-02 10:48
 * @Description todo
 */
public class ReactorHandler {
    final SelectionKey sk;
    final SocketChannel client;

    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0,SENDING = 1;
    int state = READING;

    public ReactorHandler(Selector selector, SocketChannel client) throws IOException {
        this.client = client;
        client.configureBlocking(false);

        sk = client.register(selector, 0);


        sk.attach(this);

        // 注册read事件
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }


    boolean inputIsComplete()
    {
        /* ... */
        return false;
    }

    boolean outputIsComplete()
    {

        /* ... */
        return false;
    }

    void process()
    {
        /* ... */
        return;
    }

    public void run()
    {
        try
        {
            if (state == READING)
            {
                read();
            }
            else if (state == SENDING)
            {
                send();
            }
        } catch (IOException ex)
        { /* ... */ }
    }

    void read() throws IOException
    {
        client.read(input);
        if (inputIsComplete())
        {

            process();

            state = SENDING;
            // Normally also do first write now

            //第三步,接收write就绪事件
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException
    {
        client.write(output);

        //write完就结束了, 关闭select key
        if (outputIsComplete())
        {
            sk.cancel();
        }
    }


}
