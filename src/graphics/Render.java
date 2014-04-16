package graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author Creatorri
 */
public class Render {

    public final int width;
    public final int height;
    public final int[] pixels;

    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void draw(Render render, int xOffset, int yOffset) {
        for (int y = 0; y < render.height; y++) {
            int ypix = y + yOffset;
            if (ypix < 0 || ypix >= height) {
                continue;
            }
            for (int x = 0; x < render.width; x++) {
                int xpix = x + xOffset;
                if (xpix < 0 || xpix >= width) {
                    continue;
                }
                int color = render.pixels[x + y * render.width];
                if (color > 0) {
                    pixels[xpix + ypix * width] = color;
                }
            }
        }
    }

    public void draw(BufferedImage img, int xOffset, int yOffset) {
        Render render = new Render(img.getWidth(), img.getHeight());
        System.arraycopy(img.getRaster().getPixels(0, 0, img.getWidth(), img.getHeight(), render.pixels), 0, render.pixels, 0, render.pixels.length);
        for (int y = 0; y < render.height; y++) {
            int ypix = y + yOffset;
            if (ypix < 0 || ypix >= height) {
                continue;
            }
            for (int x = 0; x < render.width; x++) {
                int xpix = x + xOffset;
                if (xpix < 0 || xpix >= width) {
                    continue;
                }
                int color = render.pixels[x + y * render.width];
                if (color > 0) {
                    pixels[xpix + ypix * width] = color;
                }
            }
        }
    }
}
