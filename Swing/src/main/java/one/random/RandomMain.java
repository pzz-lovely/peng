package one.random;

import javax.swing.*;
import java.awt.*;

/**
 * @Author lovely
 * @Create 2020-11-24 15:27
 * @Description todo
 */
public class RandomMain {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new RandomFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}