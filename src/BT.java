import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jonathan on 09/03/2015.
 * Uses basic backtracking.
 */
public class BT {

    public String name = "BT";
    int[][] grid;
    int globalRow, globalCol;
    int sqrt;
    int iterations;
    ArrayList<Integer> template = new ArrayList<Integer>();

    public int[][] SolveSudoku(int[][] f) {
        this.grid = f;
        sqrt = (int)Math.sqrt(grid.length);

        for (int i = 0; i < grid.length; i++) {
            template.add(i+1);
        }

        if (!BacktrackSudoku(this.grid)) {
            System.out.println("Fail in " + this.name);
            return null;
        }


        //System.out.println(iterations);
        return grid;
    }

    boolean BacktrackSudoku(int[][] g) {

        grid = g;
        int row, col;

        if (!FindUnassignedLocation())
            return true; //We are done if there are no zeros
        row = globalRow;
        col = globalCol;

        ArrayList<Integer> ints = new ArrayList<Integer>(template);
        Collections.shuffle(ints);

        //Ordered for now
        for (Integer num : ints) {
            iterations++;
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
