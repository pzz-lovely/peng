package one.chapter12.factory;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-24 20:58
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FrameFactory {
    public static void runFrame(JFrame frame, String title) {
        EventQueue.invokeLater(()->{
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle(title);
            frame.setVisible(true);
        });
    }
}
