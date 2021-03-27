package one.random;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @Author lovely
 * @Create 2020-11-24 15:53
 * @Description todo
 */
public class Team {
    private String name;
    private int index;
    private volatile int core;
    private volatile Color color;

    private Rectangle2D.Double rectangle2D;

    public Team() {
        color = Color.green;
    }

    public Team(String name, int index, int core) {
        this.name = name;
        this.index = index;
        this.core = core;

        color = Color.green;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle2D.Double getRectangle2D() {
        return rectangle2D;
    }

    public void setRectangle2D(Rectangle2D.Double rectangle2D) {
        this.rectangle2D = rectangle2D;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }
}