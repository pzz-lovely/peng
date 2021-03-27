package one.random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author lovely
 * @Create 2020-11-24 16:31
 * @Description todo
 */
public abstract class RandomOperation extends JPanel implements Runnable {
    protected volatile Thread thread;
    protected volatile boolean isRunning;
    protected Team teams[];
    protected Color color = Color.BLUE;
    private long randomTeam = 1;
    private Random random = new Random();
    private int count = 0;
    private final JButton addCoreButton;
    private volatile int index = 0;

    public RandomOperation( boolean isRunning, Team[] teams) {
        this.isRunning = isRunning;
        this.teams = teams;
        randomTeam <<= 63;
        for (int i = 0; i < 64; i++) {
            randomTeam |= random.nextInt(1) >> i;
        }
        addCoreButton = new JButton("¼Ó·Ö");

        addCoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public void run() {
        while (true) {
            if (!isRunning) {
                LockSupport.park();
            }else{
                try {
                    index = getIndex();
                    teams[index].setColor(color);
                    repaint();
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public int getIndex(){
        String[] values = new String[teams.length];
        for (int i = 0; i < teams.length; i++) {
            values[i] = (randomTeam >>> random.nextInt(64) & 1) + "";
        }
        int result = 0;
        for (int i = 0; i < values.length; i++) {
            result += Integer.parseInt(values[i]);
        }
        if (result == 4) {
            result = random.nextInt(4);
        }
        teams[result].setColor(Color.BLUE);
        count++;
        if (count == 4) {
            for (int i = 0; i < 64; i++) {
                randomTeam |= random.nextInt(1) >> i;
            }
        }
        return result;
    }


    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public long getRandomTeam() {
        return randomTeam;
    }

    public void setRandomTeam(long randomTeam) {
        this.randomTeam = randomTeam;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}