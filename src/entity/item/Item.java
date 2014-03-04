
package entity.item;

import dungeon.Level;
import entity.Entity;

/**
 * Items
 * @author Torri
 */
public abstract class Item extends Entity{
    /**
     * creates item
     * @param l 
     */
    public Item(Level l) {
        super(l);
    }
    /**
     * parent object
     */
    public Entity parent;
    /**
     * Puts item on the floor
     */
    public void drop(){
        x=parent.x+(rand.nextInt(64)/64);
        y=parent.y+(rand.nextInt(64)/64);
        l.getChunk(x, y).enities.add(this);
    }
}
