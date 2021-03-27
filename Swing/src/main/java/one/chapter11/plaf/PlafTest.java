package one.chapter11.plaf;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-22 21:45
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class PlafTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new PlafFrame();
            frame.setTitle("0.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
