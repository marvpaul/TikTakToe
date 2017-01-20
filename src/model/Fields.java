package model;

import View.UI;

/**
 * Created by marvinkruger on 18.01.17.
 */
public
class Fields {
    private static boolean turnDetector = false;

    //holds the data about content of a field
    //0 - empty, 1 - o, 2 - x
    private static int[][] fields = new int[5][5];

    /**
     * Switch to the other players turn
     */
    public static void changeTurnVariable(){
        turnDetector = !turnDetector;
    }

    /**
     * Do all necessary stuff to handle when a field was clicked
     * @param x x coordinate of the clicked field
     * @param y y coordinate of the clicked field
     * @throws InterruptedException
     */
    public static void clickOnField(int x, int y) throws InterruptedException {
        if(isValidTurn(x, y)){
            if(turnDetector){
                fields[x][y] = 1;
                checkIfWon(x, y, true, 0, 0, 0);
            } else{
                fields[x][y] = 2;
                checkIfWon(x, y, false, 0, 0, 0);
            }
        }
    }

    /**
     * Getter vor turnDetector
     * @return turnDetector
     */
    public static boolean isTurnDetector() {
        return turnDetector;
    }

    /**
     * Check if you can open this field
     * @param x x coordinate of the field
     * @param y y coordinate of the field
     * @return true if you can click on the current field, else false
     */
    public static boolean isValidTurn(int x, int y){
        if(fields[x][y] == 0){
            return true;
        }
        return false;
    }

    /**
     * Is called when JFrame was resized. Rescale each image
     */
    public static void scaling() {
        for (int i = 0; i < 5; i++) {
            for (int m = 0; m < 5; m++) {
                if(fields[i][m] > 0){
                    if(fields[i][m] == 1){
                        UI.setImageToField(i, m, true);
                    } else{
                        UI.setImageToField(i, m, false);
                    }

                }
            }
        }
    }

    /**
     * Recursive method to check if the current player has won the game
     * @param x
     * @param y
     * @param o
     * @param xStep
     * @param yStep
     * @param count
     * @throws InterruptedException
     */
    private static void checkIfWon(int x, int y, boolean o, int xStep, int yStep, int count) throws InterruptedException {

        //Initialze all recursive calls for each direction
        if(xStep == 0 && yStep == 0){
            checkIfWon(x, y, o, 1, 0, 1);

            checkIfWon(x, y, o, 0, 1, 1);

            checkIfWon(x, y, o, 1, 1, 1);

            checkIfWon(x, y, o, 0, 1, 1);

            checkIfWon(x, y, o, -1, 0,1);

            checkIfWon(x, y, o, 0, -1, 1);

            checkIfWon(x, y, o, -1, -1, 1);

            checkIfWon(x, y, o, 1, -1, 1);

            checkIfWon(x, y, o, -1, 1, 1);
        }

        if(!(xStep == 0 && yStep == 0)){
            x += xStep;
            y += yStep;
            // no edge collision
            if((x > 0 && y > 0 && x < 4 && y < 4) || (!(x < 0 && xStep == -1) && !(y < 0 && yStep == -1) && !(x > 4 && xStep == 1) && !(y > 4 && yStep == 1))){
                if((fields[x][y] == 1 && o) || (fields[x][y] == 2 && !o)){
                    count++;
                    if(count == 4){

                        Thread.sleep(1000);
                        UI.showWonDialogue();
                    }    else{
                        checkIfWon(x, y, o, xStep, yStep, count);
                    }
                }
            }
        }
    }


}
