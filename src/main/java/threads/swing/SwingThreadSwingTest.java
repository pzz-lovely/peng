package threads.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SwingThreadSwingTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new SwingThreadFrame();
            frame.setTitle("SwingThreadTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
    /**
     * This frame has two buttons to fill a combo box from a separate thread. The
     * "Good" button uses the event queue,the "Bad" Button modifies the combo box directly
     */
    static class SwingThreadFrame extends JFrame {
        public SwingThreadFrame(){
            setSize(500, 500);
            final JComboBox<Integer> comboBox = new JComboBox<>();  //组合框
            comboBox.insertItemAt(Integer.MAX_VALUE, 0);
            comboBox.setPrototypeDisplayValue(comboBox.getItemAt(0));   //设置第一个值
            comboBox.setSelectedIndex(0);

            JPanel jpanel = new JPanel();   //组合面板
            JButton goodButton = new JButton("Good");   //按钮
            goodButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Thread(new GoodWorkerRunnable(comboBox)).start();
                }
            });
            jpanel.add(goodButton);
            JButton badButton = new JButton("Bad");
            badButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Thread(new BadWorkerRunnable(comboBox)).start();
                }
            });
            jpanel.add(badButton);

            jpanel.add(comboBox);
            add(jpanel);
            pack();
        }
    }

    /**
     * This runnable modifies a combobox by randomly adding and removing numbers
     * This can result in errors because the combobox methods are bot synchronized
     * and both the worker thread and thr event dispatcher thread access the combobox
     */
    static class BadWorkerRunnable implements Runnable {
        private JComboBox<Integer> comboBox;
        private Random generator;

        public BadWorkerRunnable(JComboBox<Integer> aComboBox) {
            comboBox = aComboBox;
            generator = new Random();
        }

        @Override
        public void run() {
            try{
                while (true) {
                    int i = Math.abs(generator.nextInt());
                    if(i % 2 == 0 )
                        comboBox.insertItemAt(i, 0);    //指定索引 添加元素
                    else if(comboBox.getItemCount() >  0)
                        comboBox.removeItemAt(i % comboBox.getItemCount());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This Runnable modifies a combo box by randomly adding and removing numbers
     * In order to ensure that the combo box is not corrupted,the editing
     * operation are forwarded to the event dispatcher thread
     */
    static class GoodWorkerRunnable implements Runnable {
        private JComboBox<Integer> comboBox;
        private Random generator;

        public GoodWorkerRunnable(JComboBox<Integer> aComboBox) {
            this.comboBox = aComboBox;
            generator = new Random();
        }

        @Override
        public void run() {
            try{
                while (true) {
                    EventQueue.invokeLater(()->{
                        int i = Math.abs(generator.nextInt());
                        if( i % 2 == 0)
                            comboBox.insertItemAt(i, 0);
                        else if(comboBox.getItemCount() > 0)
                            comboBox.removeItemAt(i % comboBox.getItemCount());
                    });
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
