package one.chatpter14.swing;

import javax.swing.*;

/**
 * @Auther lovely
 * @Create 2020-02-27 15:40
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class SwingThreadFrame extends JFrame {
    public SwingThreadFrame() {
        setSize(500,500);
        final JComboBox<Integer> combo = new JComboBox<>();
        combo.insertItemAt(Integer.MAX_VALUE,0);
        combo.setPrototypeDisplayValue(combo.getItemAt(0));
        combo.setSelectedIndex(0);

        JPanel panel = new JPanel();
        JButton goodButton = new JButton("good");
        goodButton.addActionListener(event ->{
            new Thread(new GoodWorkerRunnable(combo), "good thread").start();
        });
        JButton badButton = new JButton("bad");
        badButton.addActionListener(event ->{
            new Thread(new BadWorkerRunnable(combo), "bad thread").start();
        });

        panel.add(combo);
        panel.add(goodButton);
        panel.add(badButton);
        add(panel);
        pack();
    }
}
