package controller;

import View.UI;
import model.Fields;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by marvinkruger on 18.01.17.
 */
public class ClickListener implements MouseListener {
    private int x, y;

    /**
     * Public constructor
     * @param x the x coordinate of this ClickLiseteners JPanel in the Grid
     * @param y the y coordinate of this ClickLiseteners JPanel in the Grid
     */
    public ClickListener(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Fires when a mouse click was performed
     * Handles all necessary functions to open a field and add a x or o
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(Fields.isValidTurn(x, y)){
            Fields.changeTurnVariable();

            try {
                Fields.clickOnField(x, y);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            UI.setImageToField(x, y, Fields.isTurnDetector());

        }

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
