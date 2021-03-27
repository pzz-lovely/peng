package one.chapter12.toolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @Auther lovely
 * @Create 2020-02-25 15:20
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ToolBarFrame extends JFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private JPanel panel;

    public ToolBarFrame(){
        setSize(WIDTH, HEIGHT);

        //add a panel for color change
        panel = new JPanel();
        add(panel, BorderLayout.CENTER);

        //set up actions
        Action blueAction = new ColorAction("Blue", new ImageIcon("blue.icon"), Color.blue);
        Action redAction = new ColorAction("Red", new ImageIcon("red0.icon"), Color.red);
        Action yellowAction = new ColorAction("Yellow", new ImageIcon("yellow.icon"), Color.yellow);

        Action exitAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit");

        //populate toolbar
        JToolBar bar = new JToolBar();
        bar.add(blueAction);
        bar.add(redAction);
        bar.add(yellowAction);
        bar.addSeparator();
        bar.add(exitAction);
        add(bar, BorderLayout.NORTH);

        //populate menu
        JMenu menu = new JMenu("Color");
        menu.add(redAction);
        menu.add(blueAction);
        menu.add(yellowAction);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    private class ColorAction extends AbstractAction{
        public ColorAction(String name, Icon icon, Color color) {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, name + " background");
            putValue("Color", color);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = (Color) getValue("Color");
            panel.setBackground(color);
        }
    }
}
