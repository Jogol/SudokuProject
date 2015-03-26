import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jonathan on 22/03/2015.
 */
public class TestObjects {

    public TestObjects(ArrayList<String> classList, int puzzles, int hints, int size) throws ClassNotFoundException, IllegalAccessException, InstantiationException { //TODO only handles BT, needs to be subclasses or something to work

        long programStart = System.currentTimeMillis();

        if (classList == null) { //Default list
            classList = new ArrayList<String>();
            //classList.add("BT");
            //classList.add("BTFC");
            //classList.add("BTFCCP");
            classList.add("BTFCCPMRV");
        }

        SolutionVerifier sv = new SolutionVerifier();
        PuzzleGeneratorMS pg = new PuzzleGeneratorMS();
        SudokuPrinter sp = new SudokuPrinter();

        ArrayList<int[][]> puzzleList = pg.puzzleGenerator(size, puzzles, hints);

        System.out.println("Done generating");

        for (String solverName : classList) {
            ArrayList<int[][]> puzzleCopy = copyList(puzzleList);

            Class theClass = Class.forName(solverName);
            SuperSolver solver = (SuperSolver)theClass.newInstance();

            long totalNanos = 0;
            long hardestNanos = 0; //How long it took to solve the "hardest" puzzle
            for (int[][] puzzle : puzzleCopy) {

                solver = (SuperSolver)theClass.newInstance();

                long start = System.nanoTime();
                int[][] fixed = solver.SolveSudoku(puzzle);
                long stop = System.nanoTime();

                if (fixed == null) {
                    System.out.println(solver.getName() + " failed.");
                } else if (!sv.verifyField(fixed)) {
                    System.out.println("Solution was incorrect! Quitting!");
                    sp.print(fixed);
                    return;
                } else {
                    if(stop - start > hardestNanos)
                        hardestNanos = stop - start;

                    totalNanos += stop - start;
                }

            }

            long averageNanos = totalNanos/puzzles;

            System.out.println(solver.getName() + ":\nAverage over " + puzzles + " puzzles: " + timeFormatterNanos(averageNanos));
            System.out.println("Longest: " + timeFormatterNanos(hardestNanos));



            System.out.println("Program has been running for: " + timeFormatterMillis(System.currentTimeMillis() - programStart) + "\n");

        }

    }

    private ArrayList<int[][]> copyList(ArrayList<int[][]> puzzleList) {

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

    private String timeFormatterNanos(long inNanos) {
        /*long nanos = (inNanos) % 1000000;
        long millis = (inNanos / 1000000) % 1000;
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60));
        */

        long nanos = (inNanos) % 1000000;
        long millis = TimeUnit.MILLISECONDS.convert(inNanos, TimeUnit.NANOSECONDS);
        long seconds = TimeUnit.SECONDS.convert(inNanos, TimeUnit.NANOSECONDS);
        long minutes = TimeUnit.MINUTES.convert(inNanos, TimeUnit.NANOSECONDS);

        String time = "";
        if(minutes!=0)
            time += (minutes + " min, ");

        time += (seconds + " s, " + millis + " ms, " + nanos + " ns");

        //String time = String.format("%d min, %d s, %d ms, and %d ns", minutes, seconds, millis, nanos);
        return time;
    }

    private String timeFormatterMillis(long millis) {
        long seconds = TimeUnit.SECONDS.convert(millis, TimeUnit.MILLISECONDS);
        long minutes = TimeUnit.MINUTES.convert(millis, TimeUnit.MILLISECONDS);

        String time = String.format("%02d min, %02d s, %02d ms", minutes, seconds, millis);
        return time;
    }
}
