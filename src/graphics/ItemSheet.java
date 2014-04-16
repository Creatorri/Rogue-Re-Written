package graphics;

import assets.LoadArt;
import entity.item.Item;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 *
 * @author Torri
 */
public enum ItemSheet {

    EFFECT_BROKEN(0, 0), EFFECT_SHATTERED(0, 1), EFFECT_MAGICAL(0, 2),
    OUTLINE_SHIELD(1, 0), OUTLINE_SWORD(1, 1), TEMPLATE_SWORD(1, 2),
    TEMPLATE_AXE(2, 0), TEMPLATE_SHIELD(2, 1), INVENTORY(2, 2);
    /*
     Template Colors (RBG):
     Material (255,0,0)
     Hilt (0,255,0)
     Grip (0,0,255)
     Taper (255,255,0)
     */
    private final int x;
    private final int y;
    private final int[][] mat = {{132, 64, 24}, {}};
    private final int[][] hilt = {};
    private final int[][] grip = {};
    private final int[][] taper = {};

    private ItemSheet(int x1, int y1) {
        x = x1;
        y = y1;
    }

    public BufferedImage getSprite(Item item, int renderLevel, int size) {
        String imdir = "itemsheet" + renderLevel + ".png";
        BufferedImage i = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        BufferedImage temp;
        BufferedImage spritesheet = (new LoadArt()).createBufferedImage(imdir);
        int sx = spritesheet.getWidth() / 3;
        int sy = spritesheet.getHeight() / 3;
        temp = spritesheet.getSubimage(x * sx, y * sy, sx, sy);
        Graphics g = i.createGraphics();
        g.drawImage(temp.getScaledInstance(size, size, Image.SCALE_SMOOTH), 0, 0, null);
        WritableRaster raster = i.getRaster();
        for (int xx = 0; xx < size; xx++) {
            for (int yy = 0; yy < size; yy++) {
                int[] color = raster.getPixel(xx, yy, (int[]) null);
                if (color[0] == 255 && color[1] == 0 && color[2] == 0) {

                }
                raster.setPixel(xx, yy, color);
            }
        }
        g.dispose();
        return i;
    }
}
