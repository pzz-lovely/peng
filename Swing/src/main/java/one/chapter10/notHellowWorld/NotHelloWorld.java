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
     * paintComponent方法有一个Graphics类型的参数，这个参数保存着用于绘制图像和文本的设置。
     * 在 java中，所有绘制都必须使用Graphics对象
     * 无论何种原因，只要窗口需要重新绘图，事件处理器就会通告组件，从而引发执行所有组件的paintComponent(方法
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
