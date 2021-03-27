package one.chapter12.checkBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @Auther lovely
 * @Create 2020-02-24 16:18
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class CheckBoxFrame extends JFrame {
    private JLabel label;
    private JCheckBox bold;
    private JCheckBox italic;
    private static final int FONTSIZE = 24;
    public CheckBoxFrame() {
        //add the sample text label
        label = new JLabel("The quick brown fox jumps over the lazy dog");
        label.setFont(new Font("Serif", Font.BOLD, FONTSIZE));
        add(label, BorderLayout.CENTER);

        //this listener sets the font attribute of
        //the label to the check box state
        ActionListener listener = even ->{
            int mode = 0;
            if(bold.isSelected()) mode += Font.BOLD;
            if(italic.isSelected()) mode += Font.ITALIC;
            label.setFont(new Font("Serif", mode, FONTSIZE));
        };

        //add the check boxes
        JPanel panel = new JPanel();

        bold = new JCheckBox("Bold");
        bold.addActionListener(listener);
        bold.setSelected(true);
        panel.add(bold);

        italic = new JCheckBox("Italic");
        bold.addActionListener(listener);
        bold.setSelected(true);
        panel.add(italic);

        add(panel, BorderLayout.SOUTH);
        pack();
    }
}
