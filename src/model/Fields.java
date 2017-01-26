package model;

import View.UI;

import java.util.Arrays;

/**
 * Created by marvinkruger on 18.01.17.
 */
public
class Fields {
    private static boolean turnDetector = false;
    public static final int SIZE = 5;
    //holds the data about content of a field
    //0 - empty, 1 - o, 2 - x
    private static int[][] fields = new int[SIZE][SIZE];

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
                if(checkIfWonRec(x, y, true, 0, 0, 0) >= 5){
                    UI.showWonDialogue(true);
                }
            } else{
                fields[x][y] = 2;
                if(checkIfWonRec(x, y, false, 0, 0, 0) >= 5){
                    UI.showWonDialogue(false);
                }
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
        for (int i = 0; i < SIZE; i++) {
            for (int m = 0; m < SIZE; m++) {
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
     * @param x the current opened field x coordinate
     * @param y the current opened field y coordinate
     * @param o true if the current opened field is a o, else it's a x
     * @param xStep the x offset
     * @param yStep the y offset
     * @param count the count for following fields of same species
     */
    private static void checkIfWon(int x, int y, boolean o, int xStep, int yStep, int count)  {

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
                    if(count == SIZE){
                        UI.showWonDialogue(o);
                    }    else{
                        checkIfWon(x, y, o, xStep, yStep, count);
                    }
                }
            }
        }
    }


    /**
     * recursive method to check the maximum x or o depending on a certain field
     * @param x the current opened field x coordinate
     * @param y the current opened field y coordinate
     * @param o true if the current opened field is a o, else it's a x
     * @param xStep the x offset
     * @param yStep the y offset
     * @param count the count for following fields of same species
     * @return the maximum value of x or o in a row
     */
    private static int checkIfWonRec(int x, int y, boolean o, int xStep, int yStep, int count)  {

        //Initialze all recursive calls for each direction
        if(xStep == 0 && yStep == 0){
            int count1 = checkIfWonRec(x, y, o, -1, -1, 1) + checkIfWonRec(x, y, o, 1, 1, 1) -1;

            int count2 = checkIfWonRec(x, y, o, 1, 0, 1) + checkIfWonRec(x, y, o, -1, 0,1) -1;

            int count3 = checkIfWonRec(x, y, o, 0, 1, 1)  + checkIfWonRec(x, y, o, 0, -1, 1) -1 ;

            int count4 = checkIfWonRec(x, y, o, 1, -1, 1) + checkIfWonRec(x, y, o, -1, 1, 1) -1;

            int[] countArray = new int[]{count1, count2, count3, count4};
            System.out.println(Arrays.toString(countArray));
            Arrays.sort(countArray);
            return countArray[countArray.length-1];

        }
        else{
            x += xStep;
            y += yStep;
            // no edge collision
            if((x > 0 && y > 0 && x < 4 && y < 4) || (!(x < 0 && xStep == -1) && !(y < 0 && yStep == -1) && !(x > 4 && xStep == 1) && !(y > 4 && yStep == 1))){
                if((fields[x][y] == 1 && o) || (fields[x][y] == 2 && !o)){
                    count++;
                    return checkIfWonRec(x, y, o, xStep, yStep, count);
                } else{
                    return count;
                }
            }
            return count;
        }
    }


    /**
     * Function to handle the restart in the model class
     * It resets the field and turn variable
     */
    public static void Restart(){
        fields = new int[SIZE][SIZE];
        turnDetector = false;
    }

}
