package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Mouse Input
 *
 * @author Torri
 */
public class Mouse implements MouseListener {

    public int mx;
    public int my;
    public ArrayList<MButton> mb = new ArrayList<>();

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mx = e.getX() + 5;
        my = e.getY() - 30;
        for (int i = 0; i < mb.size(); i++) {
            if (mb.get(i).update(mx, my)) {
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
