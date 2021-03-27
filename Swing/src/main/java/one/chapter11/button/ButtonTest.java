package one.chapter11.button;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-22 19:54
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ButtonTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new ButtonFrame();
            frame.setTitle("0.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
