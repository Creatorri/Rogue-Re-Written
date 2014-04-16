package input;

import core.Rogue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Torri
 */
public class Keyboard implements KeyListener {

    public int[] keybinds;
    public double[][] actions;

    /**
     * Creates new Keyboard with key binds and corresponding actions
     *
     * @param keybind key codes for actions
     * @param action actions for key code
     * @throws java.lang.Exception in case that there are not enough actions for
     * the key binds or too many actions and not enough key binds
     */
    public Keyboard(int[] keybind, double[][] action) throws Exception {
        if (keybind.length != action.length) {
            throw new Exception("Keybinds and actions don't match up!");
        }
        if (action[0].length != 2) {
            throw new Exception("Actions in format of dx and dy");
        }
    }

    /**
     * Finds button pressed
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        for (int i = 0; i < keybinds.length; i++) {
            if (e.getKeyCode() == keybinds[i]) {
                Rogue.l.p.get(0).move(actions[i][0], actions[i][1]);
                Rogue.gp.update();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
