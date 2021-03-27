package one.chapter10.notHellowWorld;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-22 14:26
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class NotHelloWorld {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new JFrame();
            frame.add(new NotHelloWorldComponent());
            frame.setTitle("paintComponent(Graphics) use");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setVisible(true);
        });
    }
}

class NotHelloWorldComponent extends JComponent {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    public static final int Message_x = 75;
    public static final int Message_y = 75;


    /**
     * paintComponent������һ��Graphics���͵Ĳ���������������������ڻ���ͼ����ı������á�
     * �� java�У����л��ƶ�����ʹ��Graphics����
     * ���ۺ���ԭ��ֻҪ������Ҫ���»�ͼ���¼��������ͻ�ͨ��������Ӷ�����ִ�����������paintComponent(����
     * @param graphics
     */
    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawString("Not a Hello,world program", Message_x, Message_y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
