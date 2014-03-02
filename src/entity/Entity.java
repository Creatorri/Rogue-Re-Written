
package entity;

import entity.item.Item;
import dungeon.Chunk;
import dungeon.Level;
import java.util.Random;
import render.Sprite;

/**
 * Parent Entity class
 * @author Torri
 */
public abstract class Entity {
    /**
     * Current chunk
     */
    public Chunk c;
    /**
     * Position Data
     */
    public double x,y;
    /**
     * Health data
     */
    public double health,maxhealth;
    /**
     * Mana Data
     */
    public double mana,maxmana;
    /**
     * xp data
     */
    public double xp=0,xplevels=1;
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
    /**
     * 
     */
    public final Random rand = new Random();
    /**
     * Creates Entity on level l
     * @param l 
     */
    public Entity(Level l){
        this.l=l;
        spawn();
    }
    /**
     * Distance
     * @param e
     * @return 
     */
    public double distTo(Entity e){
        return Math.sqrt((x-e.x)*(x-e.x)+(y-e.y)*(y-e.y));
    }
    /**
     * Updates entity
     */
    public abstract void turn();
    /**
     * Spawns randomly on the level
     */
    public void spawn(){
        while(l.solid(x,y)){
            x=(double) l.rand.nextInt(Level.SIZE);
            y=(double) l.rand.nextInt(Level.SIZE);
        }
        switchChunks(l.getChunk(x, y));
    }
    /**
     * Moves a distance
     * @param dx
     * @param dy 
     */
    public void move(double dx,double dy){
        if(l.solid(x, y)) return;
        for(Entity e : l.getChunk(x, y).enities){
            if(x+dx==e.x && y+dy==e.y) return;
        }
        x+=dx;
        y+=dy;
        switchChunks(l.getChunk(x, y));
    }
    /**
     * Moves chunks
     * @param nc 
     */
    public void switchChunks(Chunk nc){
        if(c==nc) return;
        if(c!=null) c.enities.remove(this);
        c=nc;
        if(c!=null) c.enities.add(this);
    }
    /**
     * What to do when an entity dies
     */
    public void death(){
        if(inv!=null){
            for (Item inv1 : inv) {
                inv1.drop();
            }
        }
        c.enities.remove(this);
    }
    /**
     * Spawns a bunch of entities
     * @param am
     * @param lvl
     * @param l 
     */
    public static void spawner(int am,int lvl,Level l){
        
    }
}
