package core;

import dungeon.Level;
import java.awt.Image;
import javax.swing.JFrame;
import graphics.Sprite;
import graphics.SpriteSheet;

/**
 *
 * @author Torri
 */
public class Rogue extends JFrame implements Runnable {

    public static Level l;
    public Thread thread;
    public boolean running = false;
    public int fps = 0;

    public static void main(String[] args) {
        new Rogue("2.0.0");
    }

    public static void tick() {

    }

    public static void render() {

    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    @Override
    public void run() {
        int frames = 0;
        double unprocessedSeconds = 0;
        long previousTime = System.nanoTime();
        double secondsPerTick = 1 / 60.0;
        int tickCount = 0;
        while (running) {
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previousTime;
            previousTime = currentTime;
            unprocessedSeconds += passedTime / 1000000000.0;
            while (unprocessedSeconds > secondsPerTick) {
                tick();
                unprocessedSeconds -= secondsPerTick;
                tickCount++;
                if (tickCount % 60 == 0) {
                    fps = frames;
                    previousTime += 1000;
                    frames = 0;
                }
            }
            render();
            frames++;
        }
        System.gc();
    }

    public Rogue(String version) {
        super("Rogue Re-Rezzed v" + version);
        setSize(750, 500);
        this.setFocusable(true);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image ico = (new Sprite(SpriteSheet.QUATOPULARSLITH, 64, l != null ? l.RENDERLEVEL : 0)).img;
        this.setIconImage(ico);
    }
}
