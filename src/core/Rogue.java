package core;

import dungeon.Level;
import java.awt.Image;
import javax.swing.JFrame;
import graphics.Sprite;
import graphics.SpriteSheet;
import ui.Gameplay;

/**
 *
 * @author Torri
 */
public class Rogue extends JFrame {

    public static Level l;
    public static Gameplay gp;

    public static void main(String[] args) {
        Rogue rogue = new Rogue("2.0.0");
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
