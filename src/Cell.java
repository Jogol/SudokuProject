import java.util.ArrayList;

/**
 * Created by Jonathan on 07/03/2015.
 * This class represents one little cell in the sudoku field.
 * The cells keep track of what values they can hold.
 */
public class Cell {

    int value;
    ArrayList<Integer> possibleValues;
    int xPos;
    int yPos;

    /**
     *
     * @param value The value this cell contains
     * @param length The size of the sudoku puzzle,
     *               and therefore the range of possible
     *               values for the list possibleValues.
     */
    public Cell(int value, int x, int y, int length) {

        this.value = value;
        xPos = x;
        yPos = y;
        //If it is 0, that means there is no value

        //All values are said ot be possible at first
        //When you check possible numbers it will update
        resetPossible(length);
    }

    private void resetPossible(int length) {
        possibleValues = null; //Maybe unnecessary
        possibleValues = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            possibleValues.add(i+1);
        }
    }

    public int getValue() {
        return value;
    }

    public boolean isPossible(int num, Field f) {
        updatePossible(f);
        return possibleValues.contains(num);
    }

    /**
     * Updates the possibleValues array by checking its row, column and square
     */
    private void updatePossible(Field f) {

    }

    /**
     * Basically a setter
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }
}
