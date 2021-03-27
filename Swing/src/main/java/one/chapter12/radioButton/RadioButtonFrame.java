package one.chapter12.radioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @Auther lovely
 * @Create 2020-02-24 16:39
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class RadioButtonFrame extends JFrame {
    private JPanel buttonPanel;
    private ButtonGroup group;
    private JLabel label;
    private static final int SIZE = 36;


    public RadioButtonFrame(){
        //add the sample text label
        label = new JLabel("The quick brown fox jumps over the lazy dog");
        label.setFont(new Font("Serif", Font.PLAIN, SIZE));
        add(label, BorderLayout.CENTER);

        //add the radio buttons
        buttonPanel = new JPanel();
        group = new ButtonGroup();

        addRadioButton("Small", 8);
        addRadioButton("Medium", 12);
        addRadioButton("Large", 18);
        addRadioButton("Extra large", 36);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();

    }

    private void addRadioButton(String name, int size) {
        boolean selected = size == SIZE;
        JRadioButton button = new JRadioButton(name, selected);
        group.add(button);
        buttonPanel.add(button);

        //this listener sets the label font size
        ActionListener listener = event -> {
            label.setFont(new Font("Serif", Font.BOLD, size));
        };
        button.addActionListener(listener);
    }
}
