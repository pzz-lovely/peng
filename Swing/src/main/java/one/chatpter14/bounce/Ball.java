package one.chatpter14.bounce;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @Auther lovely
 * @Create 2020-02-25 15:46
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Ball {
    private static final int X_SIZE = 15;
    private static final int Y_SIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    /**
     * Moves the ball to the next position,reversing direction if it hit one of the edges
     * @param bounds
     */
    public void move(Rectangle2D bounds) {
        x += dx;
        y += dy;
        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
            dx = -dx;
        }
        if (x + X_SIZE >= bounds.getMaxX()) {
            x = bounds.getMaxX() - X_SIZE;
            dx = -dx;
        }
        if (y < bounds.getMinY()) {
            y = bounds.getMaxY();
            dy = -dy;
        }
        if (y + Y_SIZE >= bounds.getMaxY()) {
            y = bounds.getMaxY() - Y_SIZE;
            dy = -dy;
        }
    }

    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x, y, X_SIZE, Y_SIZE);
    }
}
