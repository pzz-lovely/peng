package one.chapter11.action;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-23 14:02
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ActionTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new ActionFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Action demo");
            frame.setVisible(true);
        });
    }
}
