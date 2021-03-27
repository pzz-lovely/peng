package one.chapter10.simpleFrame;

import javax.swing.*;

/**
 * @Auther lovely
 * @Create 2020-02-22 12:58
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class SimpleFrameTest {
    public static void main(String[] args) {
        JFrame frame = new SimpleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

class SimpleFrame extends JFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    public SimpleFrame() {
        setSize(WIDTH, HEIGHT);
    }
}
