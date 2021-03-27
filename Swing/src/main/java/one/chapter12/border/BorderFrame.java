package one.chapter12.border;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-24 20:48
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class BorderFrame extends JFrame {
    private JPanel demoPanel;
    private JPanel buttonPanel;
    private ButtonGroup group;

    public BorderFrame(){
        demoPanel = new JPanel();
        buttonPanel = new JPanel();
        group = new ButtonGroup();

        addRadioButton("Lowered bevel", BorderFactory.createLoweredBevelBorder());
        addRadioButton("Raised bevel", BorderFactory.createRaisedBevelBorder());
        addRadioButton("Etched", BorderFactory.createEtchedBorder());
        addRadioButton("Line", BorderFactory.createLineBorder(Color.BLUE));
        addRadioButton("Matte", BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
        addRadioButton("Empty", BorderFactory.createEmptyBorder());

        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Border types");
        buttonPanel.setBorder(titled);

        setLayout(new GridLayout(2, 1));
        add(buttonPanel);
        add(demoPanel);
        pack();
    }


    private void addRadioButton(String buttonName, Border border) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(event->{
            demoPanel.setBorder(border);
        });
        group.add(button);
        buttonPanel.add(button);
    }
}
