package one.chapter11.action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @Auther lovely
 * @Create 2020-02-23 13:45
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description A frame with a panel that demonstrates color change actions
 */
public class ActionFrame extends JFrame {
    private JPanel buttonPanel;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    public ActionFrame(){
        setSize(WIDTH, HEIGHT);
        buttonPanel = new JPanel();

        //define actions new ColorAction定义颜色动作
        Action yellowAction = new ColorAction("Yellow", new ImageIcon(), Color.YELLOW);
        Action blueAction = new ColorAction("Blue", new ImageIcon(), Color.blue);
        Action redAction = new ColorAction("red", new ImageIcon(), Color.red);

        //add buttons for these actions 将动作添加至 button里面
        buttonPanel.add(new JButton(yellowAction));
        buttonPanel.add(new JButton(blueAction));
        buttonPanel.add(new JButton(redAction));

        //add panel to frame
        add(buttonPanel);


        //associate the Y , B and R keys with names 快捷键设置
        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");

        //associate the names with actions
        ActionMap amap = buttonPanel.getActionMap();
        amap.put("panel.yellow", yellowAction);
        amap.put("panel.blue", blueAction);
        amap.put("panel.red", redAction);
    }


    public class ColorAction extends AbstractAction {

        /**
         * the construct a color action
         * @param name the name to show on the button
         * @param icon the icon to display on the button
         * @param c the background color
         */
        public ColorAction(String name, Icon icon, Color c) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, "set panel color to " + name.toLowerCase());
            putValue("color",c);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = (Color) getValue("color");
            buttonPanel.setBackground(color);
        }
    }
}
