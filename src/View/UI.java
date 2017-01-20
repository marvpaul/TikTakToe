package View;

import controller.ClickListener;
import controller.ResizeListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Main class to handle view
 */
public class UI {

    private static JFrame frame;
    static final int FRAME_SIZE = 330, SIZE = 5;

    public static JPanel[][] panelHolder = new JPanel[SIZE][SIZE];


    public static void main(String[] args){
        initializeMainFrame();
    }

    /**
     * Initialize the main frame
     * @throws IOException
     */
    private static void initializeMainFrame() {

        frame = new JFrame();
        frame.setLayout ( new GridLayout(SIZE,SIZE));
        frame.setMinimumSize(new Dimension(FRAME_SIZE,FRAME_SIZE));

        //Add panelHolders to frame and add a mouse listener for each field
        for(int m = 0; m < SIZE; m++) {
            for (int n = 0; n < SIZE; n++) {
                panelHolder[m][n] = new JPanel();
                panelHolder[m][n].addMouseListener(new ClickListener(n, m));
                frame.add(panelHolder[m][n]);
            }
        }

        frame.setVisible(true);
        frame.addComponentListener(new ResizeListener());
    }

    /**
     * Load an image from harddrive, scale it to 50 x 50 px, add it to a JPanel
     * and return this JPanel
     * @param name the name of the image to load
     * @return a JPanel which includes the 50 x 50 image
     * @throws IOException when there is a loading problem caused f.e. by harddrive failures
     */
    private static JPanel loadImage(String name) throws IOException{

        JPanel imageWrapper = new JPanel();
        //Try to read assets from harddisk
        BufferedImage bufImage= ImageIO.read(new File("./" + name + ".png"));

        //Scale image, add to JLabel and add this JLabel to JPanel imageWrapper
        imageWrapper.add(new JLabel(new ImageIcon(bufImage.getScaledInstance(frame.getWidth()/6, frame.getHeight()/6, Image.SCALE_DEFAULT))));

        return imageWrapper;
    }

    /**
     * Add a certain image to field x, y on the JFrame
     * @param x the x coordinate
     * @param y the y coordinate
     * @param o if true x image will be loaded, else the o image
     */
    public static void setImageToField(int x, int y, boolean o) {
        panelHolder[y][x].removeAll();
        if(o){
            try {
                panelHolder[y][x].add(loadImage("x"));
            } catch (Exception e){
                System.out.println("Failed to load image");
            }
        } else{
            try {
                panelHolder[y][x].add(loadImage("o"));
            } catch (Exception e){
                System.out.println("Failed to load image");
            }
        }
        panelHolder[y][x].setVisible(true);
        frame.setVisible(true);
    }


    public static void showWonDialogue(){
        for(int m = 0; m < SIZE; m++) {
            for (int n = 0; n < SIZE; n++) {
                frame.remove(panelHolder[m][n]);
            }
        }
                JLabel text = new JLabel();
                text.setText("Won!");
                text.setVisible(true);
                frame.add(text);

    }
}
