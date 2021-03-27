package threads.bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Bounce {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame j = new BounceFrame();
            j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            j.setVisible(true);
        });
    }
}
class BounceFrame extends JFrame {
    private BallComponent comp;
    public static final long STEPS = 1000000000000000000L;    //速度
    public static final int DELAY =  3;     //延迟

    public BounceFrame(){
        setTitle("00.0");
        comp = new BallComponent();
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel,"start",event -> addBall());
        addButton(buttonPanel,"stop",event -> System.exit(0));
        add(comp, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
    private void addButton(Container c, String title, ActionListener listener){
        JButton jButton = new JButton(title);
        c.add(jButton);
        jButton.setBackground(Color.red);
        jButton.addActionListener(listener);
    }

    /**
     * Adds a bouncing ball to the panel and makes it bounce 1,1000 times
     */
    private synchronized void addBall(){
        Runnable runnable = ()->{
            try{
                Ball ball = new Ball();
                comp.add(ball);
                for (int i = 0; i <= STEPS; i++) {
                    ball.move(comp.getBounds());
                    comp.paint(comp.getGraphics());
                    Thread.sleep(DELAY);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}