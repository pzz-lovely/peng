package one.chapter11.summary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @Auther lovely
 * @Create 2020-02-23 21:09
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ActionComponent extends JComponent {
    private JButton redButton;
    private JButton yellowButton;
    private JButton blueButton;


    public ActionComponent(){
        redButton = new JButton("redButton");
        blueButton = new JButton("blueButton");
        yellowButton = new JButton("yellowButton");




    }


    private class ButtonAction extends AbstractAction {
        public ButtonAction(String name, Icon icon, Color color) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION,"set panel color to " + name.toLowerCase());
            putValue("color", color);
        }



        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = (Color) getValue("color");
            ActionComponent.this.setBackground(color);
        }
    }
}
