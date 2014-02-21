
package entity.item;

import dungeon.Level;
import entity.Entity;

/**
 *
 * @author Torri
 */
public class Armor extends Item{
    public Armor(Level l, Entity parent, int id) {
        super(l, parent, id);
    }
    public Armor(Level l, Entity parent, String id) {
        super(l, parent, id);
    }
}
