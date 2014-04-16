package entity.npc;

import dungeon.Level;
import entity.Entity;

/**
 *
 * @author Torri
 */
public class NPC extends Entity {

    public NPC(Level l) {
        super(l, Level.NUMLEVELS);
    }

    @Override
    public void turn() {
    }
}
