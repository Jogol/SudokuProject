import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jonathan on 13/03/2015.
 * Based on the backtracking solver, but added forward checking and constraint propagation.
 * Possible improvement: if a cell has value 0 and 0 possibleValues, return false.
 */
public class ImprovedBacktracking {

    int[][] grid;
    int globalRow, globalCol;
    int sqrt;
    ArrayList<Integer> template = new ArrayList<Integer>();

    //Every cell in the matrix(sudoku field) has an arraylist with its possible values.
    ArrayList<ArrayList<ArrayList<Integer>>> possibleValues = new ArrayList<ArrayList<ArrayList<Integer>>>();


    public Field SolveSudoku(Field f) {
        this.grid = f.getField();
        sqrt = (int) Math.sqrt(grid.length);

        for (int i = 0; i < grid.length; i++) {
            template.add(i + 1);
        }

        //Create possibleValues list, with all values possible.
        for (int i = 0; i < grid.length; i++) {
            ArrayList<ArrayList<Integer>> rowList = new ArrayList<ArrayList<Integer>>();
            for (int j = 0; j < grid.length; j++) {
                ArrayList<Integer> colList = new ArrayList<Integer>();
                for (int k = 0; k < grid.length; k++) {
                    colList.add(k + 1);
                }
                rowList.add(colList);
            }
            possibleValues.add(rowList);
        }


        //Go through the whole board to update possibleValues.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] != 0)
                    upDateAll(i, j, grid[i][j]);
            }
        }


        if (BacktrackSudoku(this.grid))
            System.out.println("Success!");
        else
            System.out.println("Fail...");

        f.setField(this.grid);
        return f;
    }

    boolean BacktrackSudoku(int[][] g) {

        grid = g;
        int row, col;

        if (!FindUnassignedLocation())
            return true; //If the matrix is full, we are done

        row = globalRow;
        col = globalCol;

        //Copy the arraylist of possible values
        ArrayList<Integer> ints = new ArrayList<Integer>(possibleValues.get(row).get(col));
        Collections.shuffle(ints);

        if (ints.isEmpty())
            return false;

        //Ordered for now
        for (Integer num : ints) {

            grid[row][col] = num;
            upDateAll(row, col, num);

            if (BacktrackSudoku(grid))
                return true;

            //Reset possibleValueLists here
            resetAll(row, col, grid[row][col]);
            grid[row][col] = 0;


        }
        return false;
    }

    /**
     * Will undo the input of num
     *
     * @param row
     * @param col
     * @param num the value we are undoing, and setting to 0 basically
     */
    private void resetAll(int row, int col, int num) {
        resetRow(row, num);
        resetCol(col, num);
        resetBox(row - row % sqrt, col - col % sqrt, num);
    }

    private void resetRow(int row, int num) {
        for (int col = 0; col < grid.length; col++) {
            if (!possibleValues.get(row).get(col).contains(num))
                possibleValues.get(row).get(col).add(num);
        }
    }

    private void resetCol(int col, int num) {
        for (int row = 0; row < grid.length; row++) {
            if (!possibleValues.get(row).get(col).contains(num))
                possibleValues.get(row).get(col).add(num);
        }
    }

    private void resetBox(int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < sqrt; row++) {
            for (int col = 0; col < sqrt; col++) {
                if (!possibleValues.get(row + boxStartRow).get(col + boxStartCol).contains(num))
                    possibleValues.get(row + boxStartRow).get(col + boxStartCol).add(num);
            }
        }
    }


    /**
     * Takes position in the array, and the added values, then updates, row, column and box.
     *
     * @param row
     * @param col
     * @param num the changed (new) value
     */
    private void upDateAll(int row, int col, int num) {
        updateRow(row, num);
        updateCol(col, num);
        updateBox(row - row % sqrt, col - col % sqrt, num);
    }

    private void updateRow(int row, int num) {
        for (int col = 0; col < grid.length; col++) {
            if (possibleValues.get(row).get(col).contains(num))
                possibleValues.get(row).get(col).remove(new Integer(num));
        }

    }

    private void updateCol(int col, int num) {
        for (int row = 0; row < grid.length; row++) {
            if (possibleValues.get(row).get(col).contains(num))
                possibleValues.get(row).get(col).remove(new Integer(num));
        }
    }

    private void updateBox(int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < sqrt; row++) {
            for (int col = 0; col < sqrt; col++) {
                if (possibleValues.get(row + boxStartRow).get(col + boxStartCol).contains(num))
                    possibleValues.get(row + boxStartRow).get(col + boxStartCol).remove(new Integer(num));
            }
        }
    }

    private boolean FindUnassignedLocation() {

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col] == 0) {
                    globalRow = row;
                    globalCol = col;
                    return true;
                }

            }
        }
        return false;
    }

}