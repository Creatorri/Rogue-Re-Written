
package entity.item;

import dungeon.Level;
import entity.Entity;

/**
 *
 * @author Torri
 */
public class Potion extends Item{
    public enum modifiers{
        NOMODIFIER,Powerful,Weak,Lesser,Greater
    }
    public enum materials{
        Mana,Health
    }
    public enum type{
        Potion
    }
    public Potion(Level l, Entity parent, int id){
        super(l, parent, id);
    }
    public Potion(Level l, Entity parent, String id){
        super(l,parent,id);
    }
}
