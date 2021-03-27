package one.chapter10.font;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * @Auther lovely
 * @Create 2020-02-22 16:06
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class FontTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            FontFrame fontFrame = new FontFrame();
            fontFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fontFrame.setTitle("the program demo font and line-height");
            fontFrame.setVisible(true);
        });
    }

}

class FontFrame extends JFrame {
    public FontFrame() {
        add(new FontComponent());
        pack();
    }
}

/**
 * A component that show a centered message in box
 */
class FontComponent extends JComponent {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        String message = "Hello world";
        Font font = new Font("方正姚体", Font.BOLD, 36);
        graphics2D.setFont(font);


        //measure the size of the message
        FontRenderContext context = graphics2D.getFontRenderContext();
        //bound 界限
        Rectangle2D bounds = font.getStringBounds(message, context);

        //set(x,y) = top left corner of text 当前宽度 - 字体宽度 /2
        double x = (getWidth() - bounds.getWidth()) /2;
        double y = (getHeight() - bounds.getHeight()) /2;

        //add ascent to y to reach the baseline 增加上升到y以达到基线
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        //draw the message
        graphics2D.drawString(message, (int) x, (int) baseY);
        graphics2D.setPaint(Color.LIGHT_GRAY);

        //draw the baseline
        graphics2D.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));

        //draw the enclosing rectangle
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
        graphics2D.draw(rectangle);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
