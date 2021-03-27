package one.chapter12.calculator;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-24 14:05
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CalculatorFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new JFrame();
            frame.add(new CalculatorPanel());
            frame.setSize(700, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
