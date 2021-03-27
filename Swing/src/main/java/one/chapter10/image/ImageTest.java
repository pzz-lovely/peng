package one.chapter10.image;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther lovely
 * @Create 2020-02-22 17:09
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class ImageTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new ImageFrame();
            frame.setTitle("the program demo Image");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
class ImageFrame extends JFrame{
    public ImageFrame() {
        add(new ImageComponent());
        pack();
    }
}
class ImageComponent extends JComponent{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private Image image;

    public ImageComponent() {
        this.image = new ImageIcon("blue-ball.gif").getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        if(image == null){
            return;
        }
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        //draw the image in the upper-left corner
        g.drawImage(image, 100, 100, null);

        //tile the image across component
        for (int i = 0; i * imageWidth <= getWidth(); i++) {
            for (int j = 0; j * imageHeight <= getHeight(); j++) {
                if(i+j > 0)
                    g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
