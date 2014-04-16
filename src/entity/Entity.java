package entity;

import dungeon.Chunk;
import dungeon.Level;
import entity.item.Item;
import graphics.Sprite;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Parent Entity class
 *
 * @author Torri
 */
public abstract class Entity {

    private static ArrayList<Entity> allEntityTypes = new ArrayList<>();

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
     * Level of the Entity
     */
    public Level l;
    /**
     * Inventory
     */
    public Item[] inv;

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
        spawn();
        if (this instanceof Item) {
            return;
        }
        for (Entity e : allEntityTypes) {
            if (e.getClass() == this.getClass()) {
                return;
            }
        }
        allEntityTypes.add(this);
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
    public abstract void turn();

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
        for (Entity e : l.getChunk(x, y).enities) {
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
            c.enities.remove(this);
        }
        c = nc;
        if (c != null) {
            c.enities.add(this);
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
        c.enities.remove(this);
    }

    /**
     * Spawns a bunch of entities
     *
     * @param am
     * @param lvl
     * @param l
     */
    public static void spawner(int am, int lvl, Level l) {
        int ent = 0;
        for (int i = 0; i < am; i++) {
            ent = rand.nextInt(allEntityTypes.size());
            try {
                Entity entity = (Entity) allEntityTypes.get(ent).getClass().getConstructors()[0].newInstance(l, rand.nextInt(lvl));
                entity.spawn();
            } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            }
        }
    }
}
