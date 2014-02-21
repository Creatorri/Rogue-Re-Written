
package entity.item;

import dungeon.Level;
import entity.Entity;

/**
 *
 * @author Torri
 */
public class Weapon extends Item{
    public Weapon(Level l, Entity parent, int id) {
        super(l, parent, id);
    }
    public Weapon(Level l, Entity parent, String id) {
        super(l, parent, id);
    }
}
