package one.random;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @Author lovely
 * @Create 2020-11-24 15:36
 * @Description todo
 */
public class MyPanel extends RandomOperation implements Runnable{

    private static final int width = 100;
    private static final int height = 100;
    private static final int x = 100;
    private static final int y = 100;
    private static final int interval = 20;


    public MyPanel(Team[] team,boolean isRunning) {
        super(isRunning, team);
        for (int i = 0; i < team.length; i++) {
            teams[i].setRectangle2D(new Rectangle2D.Double(x * i + interval, interval, width, height));
        }
        thread = new Thread(this,"myPanel");
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        for (int i = 0; i < teams.length; i++) {
            graphics2D.setColor(teams[i].getColor());
            graphics2D.draw(teams[i].getRectangle2D());
            Rectangle2D.Double d = teams[i].getRectangle2D();
            graphics2D.drawString(teams[i].getName(), (int) d.getMaxX() - width / 2, (int) d.getMaxY() - interval * 3);
            graphics2D.drawString("分数" + teams[i].getCore(), (int) d.getMaxX() - width / 2,
                    (int) d.getMaxY() - interval * 2);
        }
    }



}