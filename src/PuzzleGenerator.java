/*
import java.util.ArrayList;

*/
/**
 * Created by Jonathan on 16/03/2015.
 * Creates an empty field, solves it randomly, and then removes numbers
 *//*

public class PuzzleGenerator {


    public ArrayList<int[][]> PuzzleGenerator(int size, int number) {

        SolutionVerifier sv = new SolutionVerifier();

        long totalNanos = 0;
        for (int i = 0; i < number; i++) {
            Field field = new Field(createEmpty(size));
            ImprovedBacktracking better = new ImprovedBacktracking();
            SolveSudoku basic = new SolveSudoku();

            long start = System.nanoTime();
            Field fixed = better.SolveSudoku(field);
            totalNanos += System.nanoTime() - start;
            if (!sv.verifyField(fixed)) {
                System.out.println("Incorrect field! Quitting!");
                return null;
            } else {
                System.out.println("");
            }

        }

    }


    private int[][] createEmpty(int size) {
        int[][] field = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = 0;
            }
        }

        return field;
    }
}
*/
