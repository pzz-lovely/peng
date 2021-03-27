package runtimeAnnotations;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-03-15 20:45
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ButtonFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private JPanel panel;
    private JButton yellowButton;
    private JButton redButton;
    private JButton blueButton;

    public ButtonFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        panel = new JPanel();
        add(panel);

        yellowButton = new JButton("Yellow");
        blueButton = new JButton("Blue");
        redButton = new JButton("Red");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        ActionListenerInstaller.processAnnotations(this);
    }

    @ActionListenerFor(source = "yellowButton")
    public void yellowButton() {
        panel.setBackground(Color.YELLOW);
    }

    @ActionListenerFor(source = "blueButton")
    public void blueButton(){
        panel.setBackground(Color.BLUE);
    }

    @ActionListenerFor(source="redButton")
    public void redButton(){
        panel.setBackground(Color.RED);
    }


}
