package one.chapter12.sizedFrame;

import javax.swing.*;
import java.awt.*;

/**
 * getDefaultToolkit()获取Toolkit工具箱
 *      getScreenSize()返回Dimension对象 Dimension返回屏幕大小
 */
public class SizedFrame extends JFrame {
    public SizedFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWith = screenSize.width;

        setSize(screenWith / 2, screenHeight / 2);
        setLocationByPlatform(true);

        Image img = new ImageIcon("/Bear.ico").getImage();
        setIconImage(img);
    }
}
