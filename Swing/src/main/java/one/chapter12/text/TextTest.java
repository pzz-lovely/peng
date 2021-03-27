package one.chapter12.text;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-24 15:48
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class TextTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new TextComponentFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle(" text demo");
            frame.setVisible(true);

        });
    }
}
