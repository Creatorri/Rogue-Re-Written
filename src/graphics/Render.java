package graphics;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;

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

    /**
     * Conversion
     *
     * @param img
     * @return
     */
    public static Render imgToRen(BufferedImage img) {
        Render render = new Render(img.getWidth(), img.getHeight());
        System.arraycopy(img.getRaster().getPixels(0, 0, img.getWidth(), img.getHeight(), render.pixels), 0, render.pixels, 0, render.pixels.length);
        return render;
    }

    /**
     * Conversion
     *
     * @param render
     * @return
     */
    public static BufferedImage renToImg(Render render) {
        int[] bitMasks = new int[]{0xFF0000, 0xFF00, 0xFF, 0xFF000000};
        SinglePixelPackedSampleModel sm = new SinglePixelPackedSampleModel(DataBuffer.TYPE_INT, render.width, render.height, bitMasks);
        DataBufferInt db = new DataBufferInt(render.pixels, render.pixels.length);
        WritableRaster wr = Raster.createWritableRaster(sm, db, new Point());
        return new BufferedImage(ColorModel.getRGBdefault(), wr, false, null);
    }

    /**
     * puts a render onto the screen @ offx,offy
     *
     * @param render
     * @param offx
     * @param offy
     */
    public void draw(Render render, int offx, int offy) {
        if (offx >= width || offx < 0 || offy >= height || offy < 0) {
            return;
        }
        for (int y = 0; y < render.height; y++) {
            int ypix = y + offy;
            if (ypix < 0 || ypix >= height) {
                continue;
            }
            for (int x = 0; x < render.width; x++) {
                int xpix = x + offx;
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

    /**
     * Puts a bufferedImage onto the screen @ offx,offy
     *
     * @param img
     * @param offx
     * @param offy
     */
    public void draw(BufferedImage img, int offx, int offy) {
        Render render = new Render(img.getWidth(), img.getHeight());
        System.arraycopy(img.getRaster().getPixels(0, 0, img.getWidth(), img.getHeight(), render.pixels), 0, render.pixels, 0, render.pixels.length);
        draw(render, offx, offy);
    }
}
