package entity;

import level.Level;

/**
 * Way out of each level
 *
 * @author Creatorri
 */
public class Stairway extends Entity {

    public Stairway(Level l) {
        super(l, 0);
    }

    @Override
    public void update() {
        if (distTo(l.getPlayer(0)) < 1) {

        }
    }
}
