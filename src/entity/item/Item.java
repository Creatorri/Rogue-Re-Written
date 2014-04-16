package entity.item;

import dungeon.Level;
import entity.Entity;

/**
 * Items
 *
 * @author Torri
 */
public class Item extends Entity {

    public static enum type {

        EMPTY, SWORD, AXE, SHEILD;

        public String toString() {
            String out = super.toString();
            out = out.toLowerCase();
            char[] word = out.toCharArray();
            word[0] = Character.toUpperCase(word[0]);
            out = "";
            for (int i = 0; i < word.length; i++) {
                out += Character.toString(word[i]);
            }
            return out;
        }
    }

    public static enum modifier {

        NONE, BROKEN, ORDINARY, SHATTERED, OLD, HEALTHY, MAGICAL, STRONG;

        public String toString() {
            if (this == NONE) {
                return "";
            }
            String out = super.toString();
            out = out.toLowerCase();
            char[] word = out.toCharArray();
            word[0] = Character.toUpperCase(word[0]);
            out = "";
            for (int i = 0; i < word.length; i++) {
                out += Character.toString(word[i]);
            }
            return out;
        }
    }

    public static enum material {

        WOOD, STONE, COPPER, BRONZE, IRON, STEEL, SHADOW, DARKNESS, UNKNOWN;

        public String toString() {
            String out = super.toString();
            out = out.toLowerCase();
            char[] word = out.toCharArray();
            word[0] = Character.toUpperCase(word[0]);
            out = "";
            for (int i = 0; i < word.length; i++) {
                out += Character.toString(word[i]);
            }
            return out;
        }
    }
    /**
     * parent entity
     */
    public Entity parent;
    public final type thing;
    public final modifier mod;
    public final material mat;

    /**
     * creates item
     *
     * @param l
     * @param lvl
     * @param id
     * @param random denotes if level is to be random or not
     */
    public Item(Level l, int lvl, int id, boolean random) {
        super(l, lvl);
        this.xplevels = random ? rand.nextInt(lvl - 1) + 1 : lvl;
        mod = modifier.values()[id % modifier.values().length];
        mat = material.values()[id % material.values().length];
        thing = type.values()[id % type.values().length];
        name = mod.toString().equals("") ? "Lvl " + xplevels + " " + mat.toString() + " " + thing.toString() : "Lvl " + xplevels + " " + mod.toString() + " " + mat.toString() + " " + thing.toString();
    }

    /**
     * Puts item on the floor
     */
    public void drop() {
        x = parent.x + (rand.nextInt(64) / 64);
        y = parent.y + (rand.nextInt(64) / 64);
        l.getChunk(x, y).enities.add(this);
    }

    @Override
    public void turn() {
    }
}
