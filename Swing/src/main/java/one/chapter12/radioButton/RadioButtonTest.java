package one.chapter12.radioButton;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-24 16:52
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class RadioButtonTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new RadioButtonFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("radio demo");
            frame.setVisible(true);
        });
    }
}
