package one.chapter11.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Auther lovely
 * @Create 2020-02-22 19:44
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ButtonFrame extends JFrame {
    private JPanel panel;   //面板
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    public ButtonFrame() {
        setSize(WIDTH, HEIGHT);

        //create buttons
        JButton yellowButton = new JButton("yellow");
        JButton blueButton = new JButton("blue");
        JButton redButton = new JButton("red");

        panel = new JPanel();

        //add buttons to panel
        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        //add panel to frame
        add(panel);

        //create button actions
        ColorAction yellowAction = new ColorAction(Color.YELLOW);
        ColorAction blueAction = new ColorAction(Color.BLUE);
        ColorAction redAction = new ColorAction(Color.RED);

        //associate actions with button 添加 事件
        yellowButton.addActionListener(yellowAction);
        blueButton.addActionListener(blueAction);
        redButton.addActionListener(redAction);

    }

    private class ColorAction implements ActionListener{
        private Color backgroundColor;

        public ColorAction(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setBackground(backgroundColor);
        }
    }
}

