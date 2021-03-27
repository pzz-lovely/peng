package one.chapter10.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @Auther lovely
 * @Create 2020-02-22 15:30
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class DrawTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new DrawFrame();
            frame.setTitle("the program demo draw rectangle ellipse circle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class DrawFrame extends JFrame {
    public DrawFrame(){
        add(new DrawComponent());
        pack();
    }
}

class DrawComponent extends JComponent {
    private static final int width = 808;
    private static final int height = 800;

    /**
     * ÖØÐÂ»æÖÆ
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        //draw a rectangle
        double leftX = 100;
        double topY = 100;
        double width = 200;
        double height = 150;
        Rectangle2D rectangle = new Rectangle2D.Double(leftX, topY, width, height);
        graphics2D.draw(rectangle);

        //draw the enclosed ellipse
        Ellipse2D ellipse2D = new Ellipse2D.Double(leftX, topY, width * 2, height * 2);
        graphics2D.draw(ellipse2D);

        //draw circle with the same center
        double centerX = rectangle.getCenterX();
        double centerY = rectangle.getCenterY();
        double radius = 150;

        Ellipse2D circle = new Ellipse2D.Double(centerX, centerY, centerX + radius, centerY + radius);
        graphics2D.draw(circle);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
