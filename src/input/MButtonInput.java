package input;

import java.awt.Component;

/**
 *
 * @author Creatorri
 */
public abstract class MButtonInput {

    public Mouse m = new Mouse();

    /**
     * What to do when commands are run
     *
     * @param command
     * @param parent
     */
    public abstract void clicked(String command, Component parent);
}
