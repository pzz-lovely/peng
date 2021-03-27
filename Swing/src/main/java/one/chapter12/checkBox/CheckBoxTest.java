package one.chapter12.checkBox;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-24 16:17
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CheckBoxTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new CheckBoxFrame();
            frame.setTitle("Check Box Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
