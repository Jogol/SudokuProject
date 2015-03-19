import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jonathan on 13/03/2015.
 * Based on the backtracking solver, but added forward checking and constraint propagation.
 * Possible improvement: if a cell has value 0 and 0 possibleValues, return false.
 * Possible optimization: instead of possible values being an arrayList (with O(n))
 * for finding Object o (removing ints), it could be an boolean[] of size grid.length
 * Considering the time improvements I got from other similar optimizations though,
 * I can't imagine it would do that much, they're only ever 9-25 long.
 */
public class ImprovedBacktracking {

    int[][] grid; //The sudoku grid we work on
    int globalRow, globalCol;
    int sqrt;
    int size;
    int iterations = 0;
    ArrayList<Integer> template = new ArrayList<Integer>();

    //Every cell in the matrix(sudoku field) has an arraylist with its possible values.
    ArrayList<ArrayList<ArrayList<Integer>>> possibleValues = new ArrayList<ArrayList<ArrayList<Integer>>>();


    public int[][] SolveSudoku(int[][] f) {
        this.grid = f;
        size = grid.length;
        sqrt = (int) Math.sqrt(size);

        for (int i = 0; i < size; i++) {
            template.add(i + 1);
        }

        //Create possibleValues list, with all values possible.
        for (int i = 0; i < size; i++) {
            ArrayList<ArrayList<Integer>> rowList = new ArrayList<ArrayList<Integer>>(size);
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> colList = new ArrayList<Integer>(size);
                for (int k = 0; k < size; k++) {
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


        if (!BacktrackSudoku(this.grid))
            System.out.println("Fail...");

        //if (iterations>1000000)
        //    System.out.println(iterations);

        return grid;
    }

    boolean BacktrackSudoku(int[][] g) {

        grid = g;
        int row, col;

        //Constraint propagation: Checks for spots with only 1 possible value

        ArrayList<int[]> cPChangedList = new ArrayList<int[]>();
        ArrayList<int[]> cPValueChanges = new ArrayList<int[]>();
        whileLoop: while(true) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    iterations++;
                    if(possibleValues.get(i).get(j).isEmpty() && grid[i][j] == 0) {
                        resetSpecific(cPChangedList);
                        resetCP(cPValueChanges);
                        return false;
                    } else if (possibleValues.get(i).get(j).size() == 1 && grid[i][j] == 0) {
                        int val = possibleValues.get(i).get(j).get(0);
                        grid[i][j] = val;
                        cPChangedList.addAll(upDateAll(i, j, val));
                        int[] pos = {i, j};
                        cPValueChanges.add(pos);
                        continue whileLoop;
                    }
                }
            }
            break;
        }


        //Below is the backtracking part
        if (!FindUnassignedLocation())
            return true; //If the matrix is full, we are done

        row = globalRow;
        col = globalCol;

        //Copy the arraylist of possible values
        ArrayList<Integer> ints = new ArrayList<Integer>(possibleValues.get(row).get(col));


        if (ints.isEmpty()) {
            System.out.println("Empty ints");
            return false;
        }

        Collections.shuffle(ints);


        //Ordered for now
        for (Integer num : ints) {

            iterations++;
            grid[row][col] = num;
            ArrayList<int[]> updated = upDateAll(row, col, num);

            if (BacktrackSudoku(grid))
                return true;

            //Reset possibleValueLists here
            resetSpecific(updated);
            grid[row][col] = 0;


        }
        resetSpecific(cPChangedList);
        resetCP(cPValueChanges);
        return false;
    }

    private void resetCP(ArrayList<int[]> cPValueChanges) {
        for(int[] pos : cPValueChanges) {
            grid[pos[0]][pos[1]] = 0;
        }
    }


    /**
     *
     * @param updated A list of int[], where [0] is row and [1] is column
     * [2] is the changed value
     */
    private void resetSpecific(ArrayList<int[]> updated) {
        for(int[] pos : updated) {
            //if (!possibleValues.get(pos[0]).get(pos[1]).contains(num)) TODO if problems occur, test adding this back, but since we get a list of changed objects, they should not be there
                possibleValues.get(pos[0]).get(pos[1]).add(pos[2]);
        }
    }

    /**
     * Takes position in the array, and the added values, then updates, row, column and box.
     *
     * @param row
     * @param col
     * @param num the changed (new) value
     * @return list of the changed positions
     */
    private ArrayList<int[]> upDateAll(int row, int col, int num) {
        //All updated values, can max be size size*2 + sqrt*sqrt (only the row, column and box can be changed
        ArrayList<int[]> list = new ArrayList<int[]>(size*2 + sqrt*sqrt);
        list.addAll(updateRow(row, num));
        list.addAll(updateCol(col, num));
        list.addAll(updateBox(row - row % sqrt, col - col % sqrt, num));
        return list;
    }

    private ArrayList<int[]> updateRow(int row, int num) {
        ArrayList<int[]> list = new ArrayList<int[]>(size);
        for (int col = 0; col < grid.length; col++) {
            if (possibleValues.get(row).get(col).remove(new Integer(num))) {
                int[] pos = {row, col, num};
                list.add(pos);
            }
        }
        return list;
    }

    private ArrayList<int[]> updateCol(int col, int num) {
        ArrayList<int[]> list = new ArrayList<int[]>(size);
        for (int row = 0; row < grid.length; row++) {
            if (possibleValues.get(row).get(col).remove(new Integer(num))) {
                int[] pos = {row, col, num};
                list.add(pos);
            }
        }
        return list;
    }

    private ArrayList<int[]> updateBox(int boxStartRow, int boxStartCol, int num) {
        ArrayList<int[]> list = new ArrayList<int[]>(sqrt * sqrt);
        for (int row = 0; row < sqrt; row++) {
            for (int col = 0; col < sqrt; col++) {
                if (possibleValues.get(row + boxStartRow).get(col + boxStartCol).remove(new Integer(num))) {
                    int[] pos = {row + boxStartRow, col + boxStartCol, num};
                    list.add(pos);
                }
            }
        }
        return list;
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