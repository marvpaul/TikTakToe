package View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by marvinkruger on 18.01.17.
 */
public class ClickListener implements MouseListener {
    private int x, y;

    public ClickListener(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x"+x + "y" + y);
        ManageGUI.panelHolder[y][x].setVisible(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
