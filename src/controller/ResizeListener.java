package controller;

import model.Fields;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by Marvin Krüger S0556109.
 */
public class ResizeListener implements ComponentListener {
    //public static int timeBetween = 0F;
    @Override
    public void componentResized(ComponentEvent e) {
        Fields.scaling();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
