package mldn.channel;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @Auther lovely
 * @Create 2020-03-30 10:54
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ChannelDemo {
    public static void main(String[] args) throws IOException {
        SendThread send = new SendThread();
        ReceiveThread receive = new ReceiveThread();
        send.getOutput().connect(receive.getInput());
        /*receive.getInput().connect(send.getOutput());*/
        new Thread(send).start();
        new Thread(receive).start();
    }
}


class SendThread implements Runnable{
    private PipedOutputStream output = new PipedOutputStream();
    @Override
    public void run() {
        try {
            this.output.write("Hello?".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipedOutputStream getOutput() {
        return output;
    }
}

class ReceiveThread implements Runnable {
    private PipedInputStream input = new PipedInputStream();

    @Override
    public void run() {
        byte[] bytes = new byte[502];
        try {
            this.input.read(bytes);
            System.out.println("Messages are received : "+new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipedInputStream getInput() {
        return input;
    }
}