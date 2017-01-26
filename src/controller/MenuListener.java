package controller;

import View.UI;
import model.Fields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marvin Krüger S0556109.
 */
public class MenuListener implements ActionListener {
    /**
     * Fires when the user click on restart in menu
     * Calls necessary functions to realize a restart
     * @param e the current action event 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Restart");
        Fields.Restart();
        UI.Restart();
    }
}
