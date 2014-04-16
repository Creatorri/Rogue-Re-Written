package graphics;

import assets.LoadArt;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Torri
 */
public enum SpriteSheet {

    BAG(0, 0), BANDIT(1, 0), DIALOGUE(2, 0), FLOOR(3, 0),
    FISH(0, 1), GOBLIN(1, 1), GOLD(2, 1), MORTUUS_TRABAJOS(3, 1),
    PLAYER(0, 2), QUATOPULARSLITH(1, 2), SNAKE(2, 2), STAIRWAY(3, 2),
    NPC(0, 2), QUESTMAN(0, 2);
    private final int x;
    private final int y;

    private SpriteSheet(int x1, int y1) {
        x = x1;
        y = y1;
    }

    public BufferedImage getSprite(int renderLevel, int size) {
        String imdir = "spritesheet" + renderLevel + ".png";
        BufferedImage i = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        BufferedImage temp;
        BufferedImage spritesheet = (new LoadArt()).createBufferedImage(imdir);
        int sx = spritesheet.getWidth() / 4;
        int sy = spritesheet.getHeight() / 3;
        temp = spritesheet.getSubimage(x * sx, y * sy, sx, sy);
        i.createGraphics().drawImage(temp.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);
        i.createGraphics().dispose();
        return i;
    }

    public String toString() {
        String[] name = super.toString().split("_");
        if (name.length == 1) {
            return super.toString();
        }
        String out = "";
        for (String str : name) {
            out += str + " ";
        }
        return out;
    }
}
