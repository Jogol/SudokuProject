/**
 * Created by Jonathan on 09/03/2015.
 */
public class SudokuPrinter {

    SudokuPrinter(Field f) {

        int[][] grid = f.getField();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] > 9) {
                    System.out.print(grid[i][j] + " ");
                } else {
                    System.out.print(" " + grid[i][j] + " ");
                }

            }
            System.out.println("");
        }
    }

}
