package one.chapter11.plaf;

import javax.swing.*;

/**
 * @Auther lovely
 * @Create 2020-02-22 21:02
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description A frame with a button panel for changing look-and-feel
 */
public class PlafFrame extends JFrame {
    private JPanel buttonPanel;
    public PlafFrame(){
        buttonPanel = new JPanel();
        UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lookAndFeelInfo : installedLookAndFeels) {
            makeButton(lookAndFeelInfo.getName(), lookAndFeelInfo.getClassName());
        }
        add(buttonPanel);

    }

    /**
     * Makes a button to change the pluggable look-and-feel
     * @param name the button name
     * @param className the name of the look-and-feel class
     */
    private void makeButton(String name, String className) {
        //add button to panel
        JButton button = new JButton(name);
        buttonPanel.add(button);

        //set button action
        button.addActionListener(event ->{
            try{
                UIManager.setLookAndFeel(className);
                SwingUtilities.updateComponentTreeUI(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
