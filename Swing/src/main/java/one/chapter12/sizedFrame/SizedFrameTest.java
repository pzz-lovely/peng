package one.chapter12.sizedFrame;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class SizedFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new SizedFrame();
            frame.setTitle("0.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

