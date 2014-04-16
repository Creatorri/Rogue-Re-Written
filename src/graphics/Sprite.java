package graphics;

import assets.LoadArt;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 *
 * @author Torri
 */
public class Sprite {

    /**
     *
     */
    public BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);

    /**
     *
     * @param <E> sprite sheet type
     * @param s Sprite
     * @param size square size of final image
     * @param renderLevel which sprite sheet level to choose
     */
    public <E extends Enum> Sprite(E s, int size, int renderLevel) {
        img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        BufferedImage temp = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        if (s instanceof SpriteSheet) {
            String imdir = "spritesheet" + renderLevel + ".png";
            BufferedImage spritesheet = (new LoadArt()).createBufferedImage(imdir);
            int sx = spritesheet.getWidth() / 4;
            int sy = spritesheet.getHeight() / 3;
            int x, y;
            switch ((SpriteSheet) s) {
                case BAG:
                    x = 0;
                    y = 0;
                    break;
                case BANDIT:
                    x = 1;
                    y = 0;
                    break;
                case DIALOGUE:
                    x = 2;
                    y = 0;
                    break;
                case FLOOR:
                    x = 3;
                    y = 0;
                    break;
                case FISH:
                    x = 0;
                    y = 1;
                    break;
                case GOBLIN:
                    x = 1;
                    y = 1;
                    break;
                case GOLD:
                    x = 2;
                    y = 1;
                    break;
                case MORTUUS_TRABAJOS:
                    x = 3;
                    y = 1;
                    break;
                case PLAYER:
                    x = 0;
                    y = 2;
                    break;
                case QUATOPULARSLITH:
                    x = 1;
                    y = 2;
                    break;
                case SNAKE:
                    x = 2;
                    y = 2;
                    break;
                case STAIRWAY:
                    x = 3;
                    y = 2;
                    break;
                case NPC:
                    x = 0;
                    y = 2;
                    break;
                default:
                    x = 2;
                    y = 0;
                    break;
            }
            temp = spritesheet.getSubimage(x * sx, y * sy, sx, sy);
            if (s == SpriteSheet.NPC) {
                temp = colorImage(temp, 0, 100, 0);
            }
            if (s == SpriteSheet.QUESTMAN) {
                temp = colorImage(temp, 0, 0, 100);
            }
        } else if (s instanceof ItemSheet) {
            String imdir = "itemsheet" + renderLevel + ".png";
            BufferedImage spritesheet = (new LoadArt()).createBufferedImage(imdir);
            int sx = spritesheet.getWidth() / 3;
            int sy = spritesheet.getHeight() / 3;
            int x, y;
            switch ((ItemSheet) s) {
                case OUTLINE_SHIELD:
                    x = 0;
                    y = 1;
                    break;
                case OUTLINE_SWORD:
                    x = 1;
                    y = 1;
                    break;
                case TEMPLATE_SWORD:

            }
        }
        img.createGraphics().drawImage(temp.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);
        img.createGraphics().dispose();
    }

    private BufferedImage colorImage(BufferedImage image, int r, int b, int g) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if (pixels[0] + r <= 255) {
                    pixels[0] += r;
                }
                if (pixels[1] + b <= 255) {
                    pixels[1] += b;
                }
                if (pixels[2] + g <= 255) {
                    pixels[2] += g;
                }
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;
    }
}
