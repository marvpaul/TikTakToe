package controller;

import View.ManageGUI;
import model.gameVaribles;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

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
        if(gameVaribles.isValidTurn(x, y)){
            gameVaribles.makeTurn();
            gameVaribles.clickOnField(x, y);
            try {
                ManageGUI.setImageToField(x, y, gameVaribles.isTurnDetector());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
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
