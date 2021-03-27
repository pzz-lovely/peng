package one.chapter12.menu.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Auther lovely
 * @Create 2020-02-25 10:44
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MenuFrame extends JFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private Action saveAction;
    private Action saveAsAction;
    private JCheckBoxMenuItem readonlyItem;
    private JPopupMenu popup;


    class TestAction extends AbstractAction{
        public TestAction(String name){
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(getValue(Action.NAME) + "selected.");
        }
    }

    public MenuFrame() {
        setSize(WIDTH, HEIGHT);

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new TestAction("New"));

        //demonstrate accelerators
        JMenuItem openItem = new JMenuItem();
        openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl 0"));

        fileMenu.addSeparator();    //添加分割线

        //给定动作的 返回JMenuItem
        saveAction = new TestAction("Save");
        JMenuItem saveItem = fileMenu.add(saveAction);
        saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        saveAsAction = new TestAction("Save As");
        JMenuItem saveAsItem = fileMenu.add(saveAsAction);
        fileMenu.addSeparator();

        fileMenu.add(new AbstractAction("Exit"){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //demonstrate checkbox and radio button menus
        readonlyItem = new JCheckBoxMenuItem();
        readonlyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean saveOk = !readonlyItem.isSelected();
                saveAction.setEnabled(saveOk);
                saveAsItem.setEnabled(saveOk);
            }
        });

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
        insertItem.setSelected(true);
        JRadioButtonMenuItem overTypeItem = new JRadioButtonMenuItem("OverType");

        group.add(insertItem);
        group.add(overTypeItem);


        //demonstrate icons
        Action cutAction = new TestAction("Cut");
        cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.gif"));
        Action copyAction = new TestAction("Copy");
        cutAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.gif"));
        Action pasteAction = new TestAction("Paste");
        cutAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.gif"));


        //添加子元素
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);


        JMenu optionMenu = new JMenu("Options");

        optionMenu.add(readonlyItem);
        optionMenu.addSeparator();
        optionMenu.add(insertItem);
        optionMenu.add(overTypeItem);

        //demonstrate mnemonics
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');

        JMenuItem indexItem = new JMenuItem("Index");
        indexItem.setMnemonic('i');
        helpMenu.add(indexItem);

        //you can also add the mnemonic key to an action
        Action aboutAction = new TestAction("About");
        aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
        helpMenu.add(aboutAction);

        //add all top-level menus bar
        JMenuBar bar =  new JMenuBar();
        setJMenuBar(bar);
        bar.add(fileMenu);
        bar.add(editMenu);
        bar.add(helpMenu);

        //demonstrate pop-ups
        popup = new JPopupMenu();
        popup.add(cutAction);
        popup.add(copyAction);
        popup.add(pasteAction);

        JPanel panel = new JPanel();
        panel.setComponentPopupMenu(popup);
        add(panel);
    }
}
