import java.util.ArrayList;

/**
 * Created by Jonathan on 22/03/2015.
 */
public class TestObjects {

    public TestObjects(ArrayList<BT> classList, int puzzles, int hints, int size) { //TODO only handles BT, needs to be subclasses or something to work


        long programStart = System.currentTimeMillis();

        SolutionVerifier sv = new SolutionVerifier();
        PuzzleGenerator pg = new PuzzleGenerator();
        SudokuPrinter sp = new SudokuPrinter();

        ArrayList<int[][]> puzzleList = pg.PuzzleGenerator(size, puzzles, hints);

        for (BT solver : classList) {
            ArrayList<int[][]> puzzleCopy = copyList(puzzleList);

            long firstTotalNanos = 0;
            long hardestNanos = 0; //How long it took to solve the "hardest" puzzle
            for (int[][] puzzle : puzzleList) {

                BT sol = new BT();

                long start = System.nanoTime();
                int[][] fixed = solver.SolveSudoku(puzzle);
                long stop = System.nanoTime();

                if (!sv.verifyField(fixed)) {
                    System.out.println("Incorrect field in basic! Quitting!");
                    sp.Print(fixed);
                    return;
                } else {
                    if(stop - start > hardestNanos)
                        hardestNanos = stop - start;

                    firstTotalNanos += stop - start;
                }

            }

            long firstAverageNanos = firstTotalNanos/puzzles;

            System.out.println("Basic backtracker:\nAverage over " + puzzles + " puzzles: " + timeFormatterNanos(firstAverageNanos));
            System.out.println("Longest: " + timeFormatterNanos(hardestNanos));



            System.out.println("Program has been running for: " + timeFormatterMillis(System.currentTimeMillis() - programStart) + "\n");

        }

    }

    private static ArrayList<int[][]> copyList(ArrayList<int[][]> puzzleList) {

        ArrayList<int[][]> copy = new ArrayList<int[][]>();
        for(int[][] puzzle : puzzleList) {
            int[][] fieldCopy = new int[puzzle.length][puzzle.length];
            for (int i = 0; i < puzzle.length; i++) {
                for (int j = 0; j < puzzle.length; j++) {
                    fieldCopy[i][j] = puzzle[i][j];
                }
            }
            copy.add(fieldCopy);
        }

        return copy;

    }

    private static String timeFormatterNanos(long inNanos) {
        long nanos = (inNanos) % 1000000;
        long millis = (inNanos / 1000000) % 1000;
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60));

        String time = String.format("%02d s, %02d ms, and %d ns", seconds, millis, nanos);
        return time;
    }

    private static String timeFormatterMillis(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60));

        String time = String.format("%02d min, %02d s, %02d ms", minutes, seconds, millis);
        return time;
    }
}
