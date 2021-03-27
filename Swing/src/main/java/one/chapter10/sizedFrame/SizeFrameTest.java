package one.chapter10.sizedFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-22 13:43
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class SizeFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame jFrame = new SizedFrame();
            jFrame.setTitle("0.0");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
        });
    }
}

class SizedFrame extends JFrame {
    public SizedFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);

        Image img = new ImageIcon("panda.ico").getImage();
        setIconImage(img);
    }
}

