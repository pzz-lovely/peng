package runtimeAnnotations;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-03-15 21:56
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class AnnotationsTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ButtonFrame frame = new ButtonFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Annotation demo");
            frame.setVisible(true);
        });
    }
}
