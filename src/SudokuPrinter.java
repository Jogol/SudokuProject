/**
 * Created by Jonathan on 09/03/2015.
 */
public class SudokuPrinter {

    public void print(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] > 9) {
                    System.out.print(grid[j][i] + " ");
                } else {
                    System.out.print(" " + grid[j][i] + " ");
                }

            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    //public void printToMatrix()

}
