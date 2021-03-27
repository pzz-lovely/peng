package com.peng.reactor2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-09-02 11:32
 * @Description todo
 */
public class MthreadHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static int READING = 0,SENDING = 1,PROCESSING =3;
    int state = READING;

    ExecutorService pool = Executors.newFixedThreadPool(2);

    public MthreadHandler(SocketChannel channel, Selector selector) throws IOException {
        this.channel = channel;
        channel.configureBlocking(false);
        this.sk = channel.register(selector, 0);


        sk.attach(this);
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


    synchronized void read() throws IOException
    {
        // ...
        channel.read(input);
        if (inputIsComplete())
        {
            state = PROCESSING;
            //使用线程pool异步执行
            pool.execute(new Processer());
        }
    }

    void send() throws IOException
    {
        channel.write(output);

        //write完就结束了, 关闭select key
        if (outputIsComplete())
        {
            sk.cancel();
        }
    }

    synchronized void processAndHandOff()
    {
        process();
        state = SENDING;
        // or rebind attachment
        //process完,开始等待write就绪
        sk.interestOps(SelectionKey.OP_WRITE);
    }

    class Processer implements Runnable
    {
        public void run()
        {
            processAndHandOff();
        }
    }
}
