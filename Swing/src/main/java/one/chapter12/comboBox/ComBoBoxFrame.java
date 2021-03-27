package one.chapter12.comboBox;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-24 21:19
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ComBoBoxFrame extends JFrame {
    private JComboBox<String> faceComboBox;
    private JLabel label;
    private static final int SIZE = 24;

    public ComBoBoxFrame(){
        //add the sample text label
        label = new JLabel("The quick brown fox jumps over the lazy dog");
        label.setFont(new Font("Serif", Font.PLAIN, SIZE));
        add(label, BorderLayout.CENTER);

        //make a combo box and add face names
        faceComboBox = new JComboBox<>();
        faceComboBox.addItem("Serif");
        faceComboBox.addItem("SansSerif");
        faceComboBox.addItem("Monospaced");
        faceComboBox.addItem("Dialog");
        faceComboBox.addItem("DialogInput");

        //the combo box listener changes the label font to the selected face name
        faceComboBox.addActionListener(event ->{
            label.setFont(new Font(faceComboBox.getItemAt(faceComboBox.getSelectedIndex()), Font.PLAIN, SIZE));
        });
        //add combo box to a panel at the frame's southern border
        JPanel comboPanel = new JPanel();
        comboPanel.add(faceComboBox);
        add(comboPanel, BorderLayout.SOUTH);
        pack();
    }

}
