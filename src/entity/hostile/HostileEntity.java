package entity.hostile;

import dungeon.Level;
import entity.Entity;

/**
 * "Bad Guys"
 *
 * @author Torri
 */
public class HostileEntity extends Entity {

    /**
     * Creates a bad guy
     *
     * @param l
     */
    public HostileEntity(Level l, int lvl) {
        super(l, lvl);
    }

    @Override
    public void turn() {
    }
}
