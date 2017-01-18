package model;

/**
 * Created by marvinkruger on 18.01.17.
 */
public class gameVaribles {
    private static boolean turnDetector = false;

    //holds the data about content of a field
    //0 - empty, 1 - o, 2 - x
    private static int[][] fields = new int[5][5];

    public static void makeTurn(){
        turnDetector = !turnDetector;
    }

    public static void clickOnField(int x, int y){
        if(turnDetector && fields[x][y] == 0){
            fields[x][y] = 1;
            checkIfWon(x, y, true, 0, 0, 0);

        } else if(fields[x][y] == 0){
            fields[x][y] = 2;
            checkIfWon(x, y, false, 0, 0, 0);
        }
    }

    public static boolean isTurnDetector() {
        return turnDetector;
    }

    public static boolean isValidTurn(int x, int y){
        if(fields[x][y] == 0){
            return true;
        }
        return false;
    }

    private static void checkIfWon(int x, int y, boolean o, int xStep, int yStep, int count){

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
                        System.out.println("won");
                    }    else{
                        checkIfWon(x, y, o, xStep, yStep, count);
                    }
                }
            }
        }
    }


}
