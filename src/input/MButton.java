package input;

import assets.LoadArt;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Mouse Button
 *
 * @version 2.0.0
 * @author Creatorri
 */
public class MButton {

    protected static ArrayList<Double> xFrac = new ArrayList<>();
    protected static ArrayList<Double> yFrac = new ArrayList<>();
    protected static ArrayList<Double> sxFrac = new ArrayList<>();
    protected static ArrayList<Double> syFrac = new ArrayList<>();

    protected final int num;
    public BufferedImage img;
    protected boolean visible = true;
    protected final LoadArt la = new LoadArt();
    protected MButtonInput mbi;
    public final String name;
    protected Component parent;

    public MButton(double x, double y, double sx, double sy, String name, Component parent) {
        this.name = name;
        this.parent = parent;
        num = xFrac.size() - 1;
        xFrac.add(num, x / ((double) (parent.getWidth())));
        yFrac.add(num, y / ((double) (parent.getHeight())));
        sxFrac.add(num, sx / ((double) (parent.getWidth())));
        syFrac.add(num, sy / ((double) (parent.getHeight())));
    }

    public int getX() {
        return (int) (xFrac.get(num) * parent.getWidth());
    }

    public int getY() {
        return (int) (yFrac.get(num) * parent.getHeight());
    }

    public int getWidth() {
        return (int) (sxFrac.get(num) * parent.getWidth());
    }

    public void setPos(double x, double y, double sx, double sy, Component parent) {
        this.parent = parent;
        xFrac.add(num, x / ((double) (parent.getWidth())));
        yFrac.add(num, y / ((double) (parent.getHeight())));
        sxFrac.add(num, sx / ((double) (parent.getWidth())));
        syFrac.add(num, sy / ((double) (parent.getHeight())));
        refreshImage();
    }

    public void addListener(MButtonInput mbi, Component parent) {
        this.parent = parent;
        for (MButton mb : mbi.m.mb) {
            if (mb.equals(this)) {
                return;
            }
        }
        this.mbi = mbi;
    }

    public BufferedImage refreshImage() {
        int sx = (int) (sxFrac.get(num) * parent.getWidth());
        int sy = (int) (syFrac.get(num) * parent.getHeight());
        img = new BufferedImage(sx, sy, BufferedImage.TYPE_INT_ARGB);
        img = la.createBufferedImage("Button.png", sx, sy);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.BLUE);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, sy / 2));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int width = fm.stringWidth(name);
        g.drawString(name, (sx / 2 - width / 2), (sy / 2 + sy / 4));
        g.dispose();
        return img;
    }

    public boolean update(int mx, int my) {
        int x = (int) (xFrac.get(num) * parent.getWidth());
        int y = (int) (yFrac.get(num) * parent.getHeight());
        int sx = (int) (sxFrac.get(num) * parent.getWidth());
        int sy = (int) (syFrac.get(num) * parent.getHeight());
        boolean go = (mx > x + 8 && mx < x + sx && my > y && my < y + sy && parent.isVisible() && visible);
        if (go) {
            mbi.clicked(name, parent);
        }
        return (go);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean newState) {
        visible = newState;
    }

    public void setParent(Component c) {
        parent = c;
    }

    public boolean equals(Object o) {
        if (!(o instanceof MButton)) {
            return false;
        }
        MButton obj = (MButton) o;
        return obj.num == this.num;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.num;
        hash = 43 * hash + Objects.hashCode(this.img);
        hash = 43 * hash + (this.visible ? 1 : 0);
        hash = 43 * hash + Objects.hashCode(this.mbi);
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.parent);
        return hash;
    }
}
