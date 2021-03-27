package one.random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author lovely
 * @Create 2020-11-24 15:29
 * @Description todo
 */
public class RandomFrame extends JFrame {

    private MyPanel myPanel;

    private JButton stopButton;
    private JButton startButton;

    private volatile boolean running;

    private long timeout = 1;

    Team[] teams = {new Team("一组",1,0),new Team("三组",3,0),new Team("四组",4,0),new Team("五组",5,0)};
    public RandomFrame() throws HeadlessException {
        setSize(600, 600);
        setLocation(200, 200);
        setTitle("随机选取");

        myPanel = new MyPanel(teams,running);
        stopButton = new JButton("停止");
        startButton = new JButton("开始");


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("被点击了");
                running = true;
                myPanel.setRunning(running);
                LockSupport.unpark(myPanel.thread);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 3; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(timeout);
                        timeout += 0.5;
                        System.out.println("停止");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                timeout = 1;
                running = false;
                myPanel.setRunning(running);
            }
        });


        myPanel.add(stopButton);
        myPanel.add(startButton);

        add(myPanel);


    }

}