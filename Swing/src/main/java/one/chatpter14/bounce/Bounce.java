package one.chatpter14.bounce;

import one.chapter12.factory.FrameFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @Auther lovely
 * @Create 2020-02-25 15:46
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description shows an animated bouncing ball
 */
public class Bounce {
    public static void main(String[] args) {
        FrameFactory.runFrame(new BounceFrame(),"Bounds Demo");
    }
}

class BounceFrame extends JFrame {
    private BallComponent comp;
    private static final int STEPS = 1000;//steps
    private static final int DELAY = 3; //ÑÓ³ÙÎª3

    public BounceFrame(){
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Exit", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }


    private void addBall() {
        try{
            Ball ball = new Ball();
            comp.add(ball);
            for (int i = 1; i <= STEPS; i++) {
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}