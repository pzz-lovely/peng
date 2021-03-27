package one.chapter11.mouse;

import javax.swing.*;

/**
 * @Auther lovely
 * @Create 2020-02-23 15:05
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MouseFrame extends JFrame {

    public MouseFrame(){
        add(new MouseComponent());
        pack();
    }

}
