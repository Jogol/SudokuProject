import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SolutionVerifier sv = new SolutionVerifier();
        PuzzleGenerator pg = new PuzzleGenerator();
        SudokuPrinter sp = new SudokuPrinter();

        long programStart = System.currentTimeMillis();

        int puzzles = 100;
        int size = 9;
        ArrayList<int[][]> puzzleList = pg.PuzzleGenerator(size, puzzles);
        ArrayList<int[][]> puzzleCopy = copyList(puzzleList);


        System.out.println("Generating puzzles done");


        /**
         * Test using the basic solver
         */
        long firstTotalNanos = 0;
        for (int[][] puzzle : puzzleList) {

            SolveSudoku sol = new SolveSudoku();

            long start = System.nanoTime();
            int[][] fixed = sol.SolveSudoku(puzzle);
            long stop = System.nanoTime();

            if (!sv.verifyField(fixed)) {
                System.out.println("Incorrect field in basic! Quitting!");
                sp.Print(fixed);
                return;
            } else {
                firstTotalNanos += stop - start;
            }

        }

        long firstAverageNanos = firstTotalNanos/puzzles;

        System.out.println("Basic backtracker:\nAverage time over " + puzzles + " puzzles: " + timeFormatterNanos(firstAverageNanos));




        /**
         * Test using the improved solver
         */
        long secondTotalNanos = 0;
        for (int[][] puzzle : puzzleCopy) {

            ImprovedBacktracking imp = new ImprovedBacktracking();
            //FCBT imp = new FCBT();

            long start = System.nanoTime();
            int[][] fixed = imp.SolveSudoku(puzzle);
            long stop = System.nanoTime();

            if (!sv.verifyField(fixed)) {
                System.out.println("Incorrect field in improved! Quitting!");
                sp.Print(fixed);
                return;
            } else {
                secondTotalNanos += stop - start;
            }

        }


        long programStop = System.currentTimeMillis();




        long secondAverageNanos = secondTotalNanos/puzzles;

        System.out.println("Improved backtracker:\nAverage time over " + puzzles + " puzzles: " + timeFormatterNanos(secondAverageNanos));


        long programMillis = programStop - programStart;

        System.out.println("Program ran for: " + timeFormatterMillis(programMillis));




        //
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

        String time = String.format("%02d seconds, %02d milliseconds, and %d nanoseconds", seconds, millis, nanos);
        return time;
    }

    private static String timeFormatterMillis(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60));

        String time = String.format("%02d minutes, %02d seconds, %02d milliseconds", minutes, seconds, millis);
        return time;
    }

    private static int[][] createSimpleField() {
        int[][] simpleField = { {1,4,7,2,5,8,3,6,9} , {2,5,8,3,6,9,4,7,1} , {3,6,9,4,7,1,5,8,2}
                , {4,7,1,5,8,2,6,9,3} , {5,8,2,6,9,3,7,1,4} , {6,9,3,7,1,4,8,2,5} , {7,1,4,8,2,5,9,3,6}
                , {8,2,5,9,3,6,1,4,7} , {9,3,6,1,4,7,2,5,8}};
        return simpleField;
    }

    private static int[][] createHolyField() {
        int[][] simpleField = { {1,4,0,2,5,8,0,6,9} , {2,0,8,3,6,9,0,7,1} , {3,0,9,4,7,1,0,8,2}
                , {4,0,1,0,8,2,6,9,0} , {0,8,2,6,9,0,7,1,4} , {6,9,3,7,1,0,8,2,5} , {7,0,4,8,2,5,9,3,6}
                , {8,2,5,9,3,0,0,0,7} , {9,3,0,1,4,0,2,0,8}};
        return simpleField;
    }


    private static int[][] createEmpty(int size) {
        int[][] field = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = 0;
            }
        }

        return field;
    }
}
