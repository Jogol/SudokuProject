import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jonathan on 09/03/2015.
 */
public class SolveSudoku {

    int[][] grid;
    int globalRow, globalCol;
    int sqrt;
    ArrayList<Integer> template = new ArrayList<Integer>();

    public Field SolveSudoku(Field f) {
        this.grid = f.getField();
        sqrt = (int)Math.sqrt(grid.length);

        for (int i = 0; i < grid.length; i++) {
            template.add(i+1);
        }

        if(BacktrackSudoku(this.grid))
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
            return true; //We are done
        row = globalRow;
        col = globalCol;

        ArrayList<Integer> ints = new ArrayList<Integer>(template);
        Collections.shuffle(ints);

        //Ordered for now
        for (Integer num : ints) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (BacktrackSudoku(grid))
                    return true;

                grid[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int[][] grid, int row, int col, int num) {

        return (!UsedInRow(row, num) && !UsedInCol(col, num) && !UsedInBox(row - row%sqrt, col - col%sqrt, num));
    }

    private boolean UsedInBox(int boxStartRow, int boxStartCol, int num) {

        for (int row = 0; row < sqrt; row++) {
            for (int col = 0; col < sqrt; col++) {
                if(grid[row+boxStartRow][col+boxStartCol] == num)
                    return true;
            }
        }

        return false;
    }

    private boolean UsedInCol(int col, int num) {

        for (int row = 0; row < grid.length; row++) {
            if (grid[row][col] == num)
                return true;
        }

        return false;
    }

    private boolean UsedInRow(int row, int num) {

        for(int col = 0; col < grid.length; col++) {
            if(grid[row][col] == num)
                return true;
        }

        return false;
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
