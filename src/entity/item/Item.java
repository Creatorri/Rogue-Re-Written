
package entity.item;

import dungeon.Level;
import entity.Entity;

/**
 * Items
 * @author Torri
 */
public class Item extends Entity{
    @Override
    public void turn() {
    }
    public enum materials{
        Wood,Stone,Copper,Bronze,Silver,Gold,Iron,Steel,Unknown
    }
    public enum type{
        Empty,Sword,Axe,Shield,Ring
    }
    public enum modifiers{
        NOMODIFIER,Broken,Ordinary,Shattered,Old,Healthy,Magical,Strong
    }
    /**
     * parent object
     */
    public Entity parent;
    /**
     * Creates Item
     * @param l
     * @param parent
     * @param id 
     */
    public Item(Level l,Entity parent,int id){
        super(l);
        int lvl = rand.nextInt(Level.NUMLEVELS+3)+1;
        this.parent=parent;
        if(id==0) name=type.Empty.name();
        name="Lvl "+lvl+" "+modifiers.values()[id%modifiers.values().length].name()+" "+materials.values()[id%materials.values().length].name()+" "+type.values()[(id%(type.values().length-1))+1];
        switch(materials.values()[id%materials.values().length]){
            
        }
        switch(type.values()[id%type.values().length]){
            
        }
        switch(modifiers.values()[id%modifiers.values().length]){
            
        }
    }
    /**
     * Creates Item
     * @param l
     * @param parent
     * @param id 
     */
    public Item(Level l,Entity parent,String id){
        super(l);
        this.parent=parent;
    }
    /**
     * Puts item on the floor
     */
    public void drop(){
        x=parent.x+(rand.nextInt(64)/64);
        y=parent.y+(rand.nextInt(64)/64);
        l.getChunk(x, y).enities.add(this);
    }
}
