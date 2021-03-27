package threads.bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The component that draws the balls
 * 绘制球的组件
 */
public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;
    private java.util.List<Ball> balls = new ArrayList<Ball>();

    /**
     * Add a ball to the component 添加球到组件
     * @param b 球 b the to add
     */
    public void add(Ball b){
        balls.add(b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.RED);
        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }
    //drop table if exists `result`
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
