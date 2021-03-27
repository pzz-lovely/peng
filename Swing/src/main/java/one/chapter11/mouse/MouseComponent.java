package one.chapter11.mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * @Auther lovely
 * @Create 2020-02-23 15:08
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MouseComponent extends JComponent {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static final int SIDELENGTH = 10;

    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;

    public MouseComponent() {
        squares = new ArrayList<>();
        current = null;

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        for(Rectangle2D rectangle : squares)
            graphics.draw(rectangle);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    /**
     * finds the first square containing a point
     * @param point a point
     * @return the first square that contains p
     */
    public Rectangle2D find(Point2D point) {
        for (Rectangle2D rectangle : squares) {
            if(rectangle.contains(point)) return rectangle;
        }
        return  null;
    }


    public void add(Point2D point){
        double x = point.getX();
        double y = point.getY();

        current = new Rectangle2D.Double(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
        squares.add(current);
        repaint();
    }

    public void remove(Rectangle2D rectangle){
        if(rectangle == null)return;
        if(rectangle ==  current) current =null;
        squares.remove(rectangle);
        repaint();
    }

    private class MouseHandler extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            //add a new square if the cursor isn't inside a square
            current = find(e.getPoint());
            if(current == null) add(e.getPoint());
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //remove the current square if double clicked
            current = find(e.getPoint());
            if(current != null && e.getClickCount() >= 2) remove(current);
        }
    }



    private class MouseMotionHandler implements MouseMotionListener{

        @Override
        public void mouseMoved(MouseEvent e) {
            //set the mouse cursor to cross hairs if it is inside
            //a rectangle
            if(find(e.getPoint())  == null) setCursor(Cursor.getDefaultCursor());
            else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (current != null) {
                int x = e.getX();
                int y = e.getY();

                //drag the current rectangle to center it at (x,y)
                current.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
                repaint();
            }
        }
    }
}

