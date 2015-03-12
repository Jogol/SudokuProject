/*
import java.util.ArrayList;
import java.util.Stack;

*/
/**
 * Created by Jonathan on 08/03/2015.
 *//*

public class BacktrackingFiller {

    ArrayList<Integer> possibleNumbers = new ArrayList<Integer>();

    */
/**
     * Recursively fills an empty field.
     * This is not the recursive function.
     * @param f
     * @return
     *//*

    public Field BacktrackingFiller(Field f) {
        for (int i = 0; i < f.len; i++) {
            possibleNumbers.add(i+1);
        }

        return backTrack(f, 0, 0);

    }

    private Field backTrack(Field f, int x, int y) {

        //We want to go to the next empty spot and guess
        while(f.getValue(x, y) != 0) {
            y++;

            if (y == f.yLen) {
                y = 0;
                x++;
            }
            if (x == f.xLen)
                return f;

        }

        //We an empty spot in (x,y)
        ArrayList<Integer> possible = getPossible(x,y,1);
        for (Integer number : possible) {

        }

        return f; //TODO temp

    }

    */
/**
     *
     * @param x
     * @param y
     * @param mode 0 = ordered, 1 = random
     * @return
     *//*

    private ArrayList<Integer> getPossible(int x, int y, int mode) {
        //We'll remove numbers from possible as we find them
        ArrayList<Integer> possible = new ArrayList<Integer>(possibleNumbers);
        return possible; //TODO temp
    }

}
*/
