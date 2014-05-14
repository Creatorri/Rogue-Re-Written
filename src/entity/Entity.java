package entity;

import level.Chunk;
import level.Level;
import entity.item.Item;
import graphics.Sprite;
import java.util.Random;

/**
 * Parent Entity class
 *
 * @author Creatorri
 */
public abstract class Entity {

    /**
     * Current chunk
     */
    public Chunk c;
    /**
     * Position Data
     */
    public double x, y;
    /**
     * Health data
     */
    public double health, maxhealth;
    /**
     * Mana Data
     */
    public double mana, maxmana;
    /**
     * xp data
     */
    public double xp = 0, xplevels = 1;
    /**
     * Number of kills the entity has
     */
    public int kills;
    /**
     * Sprite
     */
    public Sprite sp;
    /**
     * Display name of Entity
     */
    public String name = this.getClass().getName();
    /**
     * Level of which the Entity resides in
     */
    public Level l;
    /**
     * Inventory
     */
    public Item[] inv;
    /**
     * identifier in the world
     */
    public final int ID;

    protected static final Random rand = new Random();

    /**
     * Creates Entity on level l with lvl levels
     *
     * @param l
     * @param lvl
     */
    public Entity(Level l, int lvl) {
        this.l = l;
        this.xplevels = lvl;
        ID = l.allEnts.size();
        l.allEnts.add(this);
        if (!(this instanceof Item)) {
            spawn();
        }
    }

    /**
     * Distance
     *
     * @param e
     * @return
     */
    public double distTo(Entity e) {
        return Math.sqrt((x - e.x) * (x - e.x) + (y - e.y) * (y - e.y));
    }

    /**
     * Updates entity
     */
    public abstract void update();

    /**
     * Spawns randomly on the level
     */
    public void spawn() {
        while (l.solid(x, y)) {
            x = (double) l.rand.nextInt(Level.SIZE);
            y = (double) l.rand.nextInt(Level.SIZE);
        }
        switchChunks(l.getChunk(x, y));
    }

    /**
     * Moves a distance
     *
     * @param dx
     * @param dy
     */
    public void move(double dx, double dy) {
        if (l.solid(x, y)) {
            return;
        }
        for (Entity e : l.getChunk(x, y).entities) {
            if (x + dx == e.x && y + dy == e.y) {
                return;
            }
        }
        x += dx;
        y += dy;
        if (x % Chunk.SIZE == 0 || y % Chunk.SIZE == 0) {
            switchChunks(l.getChunk(x, y));
        }
    }

    /**
     * Moves chunks
     *
     * @param nc
     */
    public void switchChunks(Chunk nc) {
        if (c == nc) {
            return;
        }
        if (c != null) {
            c.entities.remove(this);
        }
        c = nc;
        if (c != null) {
            c.entities.add(this);
        }
    }

    /**
     * What to do when an entity dies
     */
    public void death() {
        if (inv != null) {
            for (Item inv1 : inv) {
                inv1.drop();
            }
        }
        c.entities.remove(this);
    }
}
