package swing.action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * A frame with a panel that demonstractes color change action
 */
public class ActionTest {

}
class ActionFrame extends JFrame{
    private JPanel buttonPanel;
    public ActionFrame(){
        setSize(300, 300);
        buttonPanel = new JPanel();

        //define action 定义行动
        Action yellowAction = new ColorAction("Yellow", new ImageIcon("0.0.gif"), Color.YELLOW);
        new ColorAction("Blue",new ImageIcon("0."));

    }
    class ColorAction extends AbstractAction{
        public ColorAction(String name, Icon icon, Color color) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, "set Panel color to " + name.toLowerCase());
            putValue("color",color);
        }

        public ColorAction(String blue, ImageIcon imageIcon) {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color c = (Color) getValue("color");
            buttonPanel.setBackground(c);
        }
    }
}
