package View;

import controller.ClickListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Main class to handle view
 */
public class ManageGUI {
    private static JFrame frame;
    private static JLabel xImage, oImage;
    private static Image xScaled, oScaled;
    public static JPanel[][] panelHolder = new JPanel[5][5];

    public static void main(String[] args) throws IOException {
        initializeMainFrame();
    }

    private static void initializeMainFrame() throws IOException {
        GridLayout layout = new GridLayout(5,5);

        frame = new JFrame();
        frame.setLayout (layout);
        frame.setSize(250,300);
        frame.setMinimumSize(new Dimension(330,330));

        //Add panelHolders to frame and add a mouse listener
        for(int m = 0; m < 5; m++) {
            for (int n = 0; n < 5; n++) {
                panelHolder[m][n] = new JPanel();
                panelHolder[m][n].addMouseListener(new ClickListener(n, m));
                frame.add(panelHolder[m][n]);
            }
        }

        //Test to assign some images to the panel holder
        /*
        for(int m = 0; m < 5; m++) {
            for (int n = 0; n < 5; n++) {
                if(n % 2 == 1)
                    panelHolder[m][n].add(loadImage("x"));
                else
                    panelHolder[m][n].add(loadImage("o"));
            }
        }*/
        frame.setVisible(true);
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
        switch (name){
            case "x":
                //Try to read assets from harddisk
                BufferedImage x = ImageIO.read(new File("./x.png"));
                //Scale images
                Image xScaled = x.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                //Create JLabels with images
                xImage = new JLabel(new ImageIcon(xScaled));
                imageWrapper.add(xImage);
                break;
            case "o":
                //Try to read assets from harddisk
                BufferedImage o = ImageIO.read(new File("./o.png"));
                //Scale images
                Image oScaled = o.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                //Create JLabels with images
                oImage = new JLabel(new ImageIcon(oScaled));
                imageWrapper.add(oImage);
        }
        return imageWrapper;
    }

    public static void setImageToField(int x, int y, boolean o) throws IOException {
        if(o){
            panelHolder[y][x].add(loadImage("x"));
        } else{
            panelHolder[y][x].add(loadImage("o"));
        }
        panelHolder[y][x].setVisible(true);
        frame.setVisible(true);
    }
}
