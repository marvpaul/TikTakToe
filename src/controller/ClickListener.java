package controller;

import View.UI;
import model.Fields;
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
        if(Fields.isValidTurn(x, y)){
            Fields.changeTurnVariable();

            try {
                Fields.clickOnField(x, y);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            try {
                UI.setImageToField(x, y, Fields.isTurnDetector());
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
