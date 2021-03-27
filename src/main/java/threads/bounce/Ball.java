package threads.bounce;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * a ball that moves and bounces off the edges of a rectangle
 */
public class Ball {
    private static final int X_SIZE = 15;
    private static final int Y_SIZE = 15;
    private double x = 0 ;
    private double y = 0 ;
    private double dx = 1;
    private double dy = 1;

    /**
     * Moves the ball the next position,reversing direction if it hits one of he edges 将球移动到下一个位置，如果它击中其中的一条边，则反向移动
     * @param bounds 跳跃
     */
    public void move(Rectangle2D bounds){
        x += dx;
        y += dy;
        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
            dx = -dx;
        }
        if(x + X_SIZE >= bounds.getMaxX()){
            x =bounds.getMaxX() - X_SIZE;
            dx= -dx;
        }
        if (y < bounds.getMinY()) {
            y = bounds.getMinY();
            dy = -dy;
        }
        if (y + Y_SIZE >= bounds.getMaxY()) {
            y = bounds.getMaxY()-Y_SIZE;
            dy = -dy;
        }
    }

    /**
     * 返回球的 位置
     * @return
     */
    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x, y, X_SIZE, Y_SIZE);
    }
}
