package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marvinkruger on 18.01.17.
 */
public class ManageGUI {
    public static void main(String[] args) {
        GridLayout layout = new GridLayout(5,5);
        JFrame frame = new JFrame();
        frame.setLayout(layout);
        frame.setSize(400,400);
        JButton jButton[] = new JButton[25];
        for ( int i = 0; i<25; i++ ) {
            jButton[i] = new javax.swing.JButton ( "JButton" + (i+1) );
            //jButton[i].addActionListener ( bl );
            frame.add ( jButton[i] );
        }


        frame.setVisible(true);
    }
}
