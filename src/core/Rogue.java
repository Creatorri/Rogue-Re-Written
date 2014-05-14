package core;

import graphics.Sprite;
import graphics.SpriteSheet;
import input.InputHandler;
import java.awt.Image;
import javax.swing.JFrame;
import level.Level;

/**
 *
 * @author Creatorri
 */
public class Rogue extends JFrame implements Runnable {

    public static Level l;
    public Thread thread;
    public boolean running = false;
    public int fps = 0;
    public int tps = 0;
    public static InputHandler ih;

    public static void main(String[] args) {
        new Rogue("2.0.0").start();
    }

    public static void update() {

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
        long previousTime = System.nanoTime();
        double ns = 1000000000.0 / 60.0;
        int frames = 0;
        int ticks = 0;
        double delta = 0;
        long timer = System.currentTimeMillis();
        requestFocus();
        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - previousTime) / ns;
            previousTime = currentTime;
            if (delta >= 1) {
                update();
                ticks++;
                delta--;
            }
            render();
            frames++;
            while (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                fps = frames;
                tps = ticks;
                frames = 0;
                ticks = 0;
            }
        }
    }

    public Rogue(String version) {
        super("Rogue Re-Rezzed Alpha v" + version);
        setSize(750, 500);
        this.setFocusable(true);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ih = new InputHandler();
        this.addInputHandler(ih);

        Image ico = (new Sprite(SpriteSheet.QUATOPULARSLITH, 64, 0)).img;
        this.setIconImage(ico);

        this.pack();
    }
    
    private void addInputHandler(InputHandler ih){
        this.addKeyListener(ih);
        this.addMouseListener(ih);
        this.addMouseMotionListener(ih);
    }
}
