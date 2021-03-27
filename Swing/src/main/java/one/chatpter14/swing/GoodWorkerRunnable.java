package one.chatpter14.swing;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @Auther lovely
 * @Create 2020-02-27 15:40
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description this runnable modifies a combo box by randomly adding and removing numbers
 */
public class GoodWorkerRunnable implements Runnable {
    private JComboBox<Integer> combo;
    private Random generator;

    public GoodWorkerRunnable(JComboBox<Integer> aCombo){
        combo = aCombo;
        generator = new Random();
    }

    @Override
    public void run() {
        try{
            while (true) {
                EventQueue.invokeLater(()->{
                    int i = Math.abs(generator.nextInt());
                    if(i % 2 == 0)
                        combo.insertItemAt(i, 0);
                    else if(combo.getItemCount() > 0)
                        combo.removeItem(i % combo.getItemCount());
                });
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
