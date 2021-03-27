package one.chapter12.sizedFrame;

import javax.swing.*;
import java.awt.*;

/**
 * getDefaultToolkit()��ȡToolkit������
 *      getScreenSize()����Dimension���� Dimension������Ļ��С
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
