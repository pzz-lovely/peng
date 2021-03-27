package one.chapter12.text;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-24 15:25
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class TextComponentFrame extends JFrame{
    private static final int TEXTAREA_ROWS = 8 ;
    private static final int TEXTAREA_COLUMNS = 20;



    public TextComponentFrame(){
        JTextField textField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,2));
        northPanel.add(new JLabel("User name", SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("Password ", SwingConstants.LEFT));
        northPanel.add(passwordField);


        add(northPanel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_ROWS);

        JScrollPane scrollPanel = new JScrollPane(textArea);
        add(scrollPanel, BorderLayout.CENTER);

        //add button to append text into the text area
        JPanel southPanel = new JPanel();
        JButton insertButton = new JButton("Insert");
        southPanel.add(insertButton);
        insertButton.addActionListener(event ->{
            textArea.append("User name: " + textField.getText() + " Password : " + new String(passwordField.getPassword()) +
                    "\n");
        });
        add(southPanel, BorderLayout.SOUTH);
        pack();
    }
}
