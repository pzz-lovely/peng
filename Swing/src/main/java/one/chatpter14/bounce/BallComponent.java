package one.chatpter14.bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @Auther lovely
 * @Create 2020-02-25 16:02
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class BallComponent extends JComponent {
    private static final int WIDTH = 450;
    private static final int HEIGHT = 350;
    private java.util.List<Ball> balls = new ArrayList<>();


    /**
     * add a ball to the component
     * @param ball
     */
    public void add(Ball ball) {
        balls.add(ball);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //erase background
        Graphics2D g2 = (Graphics2D) g;
        for(Ball ball : balls) {
            g2.fill(ball.getShape());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
}
