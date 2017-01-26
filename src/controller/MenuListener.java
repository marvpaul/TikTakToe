package controller;

import View.UI;
import model.Fields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marvin Krüger S0556109.
 */
public class MenuListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Restart");
        Fields.Restart();
        UI.Restart();
    }
}
