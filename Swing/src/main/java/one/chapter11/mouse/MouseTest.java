package one.chapter11.mouse;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-23 15:27
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MouseTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            MouseFrame frame = new MouseFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Mouse demo");
            frame.setVisible(true);
        });
    }
}
