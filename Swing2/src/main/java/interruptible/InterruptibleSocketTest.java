package interruptible;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Auther lovely
 * @Create 2020-03-15 15:01
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description This program shows how to interrupt a socket channel
 * 这个程序 展示 套接字管道 中断
 */
public class InterruptibleSocketTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new InterruptibleSocketFrame();
            frame.setTitle("InterruptibleSocketTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }



}
class InterruptibleSocketFrame extends JFrame{
    private Scanner in;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private JTextArea messages;
    private TestServer server;
    private Thread connectThread;

    public InterruptibleSocketFrame(){
        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);

        final int TEXT_ROWS = 20;
        final int TEXT_COLUMNS = 60;
        messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");

        panel.add(interruptibleButton);
        panel.add(blockingButton);

        interruptibleButton.addActionListener(event ->{
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(()->{
                try {
                    connectInterruptibly();
                } catch (IOException e) {
                    messages.append("\nInterruptibleSocketTest.connectInterruptibly:" + e);
                }
            });
            connectThread.start();
        });

        blockingButton.addActionListener(event ->{
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(()->{
                try {
                    connectBlocking();
                } catch (IOException e) {
                    messages.append("\nInterruptibleSocketTest.connectBlocking:" + e);
                }
            });
            connectThread.start();
        });

        //取消按钮
        cancelButton = new JButton("cancel");
        cancelButton.setEnabled(false);
        panel.add(cancelButton);
        cancelButton.addActionListener(event ->{
            connectThread.interrupt();
            cancelButton.setEnabled(false);
        });
        server = new TestServer();
        new Thread(server).start();
        pack();
    }


    /**
     * Connects to the test server,using interruptibly I/O
     */
    public void connectInterruptibly() throws IOException {
        messages.append("Interruptibly:\n");
        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8181))) {
            in = new Scanner(channel, "utf-8");
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }finally{
            EventQueue.invokeLater(()->{
                messages.append("Channel closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }


    /**
     * Connects to the test server,using blocking I/O
     */
    public void connectBlocking() throws IOException {
        messages.append("Blocking: \n");
        try (Socket client = new Socket("localhost", 8181)) {
            in = new Scanner(client.getInputStream(), "utf-8");
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }finally{
            EventQueue.invokeLater(()->{
                messages.append("Scoket closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }


    /**
     * 服务器
     */
    class TestServer implements Runnable {
        @Override
        public void run() {
            try (ServerSocket s = new ServerSocket(8181)) {
                while (true) {
                    Socket client = s.accept();     //阻塞获得的客户端连接
                    Runnable r = new TestServerHandler(client);
                    Thread thread = new Thread(r);  //当前这个线程为主线程 thread为子线程
                    thread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 向服务器打印数据
     */
    class TestServerHandler implements Runnable {
        private Socket client;
        private int counter;

        public TestServerHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try{
                OutputStream out = client.getOutputStream();
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8),true/*autoFlush*/);
                while (counter < 100) {
                    counter++;
                    if(counter <= 10) pw.println(counter);
                    Thread.sleep(100);
                }

            } catch (IOException | InterruptedException e) {
                messages.append("\nTestServerHandler.run("+e+")");
            }finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                messages.append("\nClosing server\n");
            }
        }
    }
}
