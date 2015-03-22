/**
 * Created by Jonathan on 22/03/2015.
 * This is a superclass to all other sudoku solvers.
 * It's just a dummy to allow easier testing
 */
interface SuperSolver {

    public int[][] SolveSudoku(int[][] f);

    public String getName();

}

