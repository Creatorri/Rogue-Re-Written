package entity.hostile;

import level.Level;
import entity.Entity;

/**
 * "Bad Guys"
 *
 * @author Creatorri
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
    public void update() {
    }
}
