public class Main {

    public static void main(String[] args) {

        //Field field = new Field(createEmpty(9));
        //SolveSudoku basic = new SolveSudoku();
        //ImprovedBacktracking better = new ImprovedBacktracking();
        SolutionVerifier sv = new SolutionVerifier();

        long programStart = System.currentTimeMillis();
        //Field fixed = impSolver.SolveSudoku(field);
        //Field fixed = sudokuSolver.SolveSudoku(field);
        //long millis = System.currentTimeMillis() - start;

        //Loop trying 10 times to get an average.

        int laps = 1000;
        long totalNanos = 0;
        for (int i = 0; i < laps; i++) {
            Field field = new Field(createEmpty(9));
            ImprovedBacktracking better = new ImprovedBacktracking();
            SolveSudoku basic = new SolveSudoku();

            long start = System.nanoTime();
            Field fixed = better.SolveSudoku(field);
            totalNanos += System.nanoTime() - start;
            if (!sv.verifyField(fixed)) {
                System.out.println("Incorrect field! Quitting!");
                return;
            } else {
                System.out.println("");
            }

        }
        long programStop = System.currentTimeMillis();

        float averageNanos = totalNanos/laps;

        long chosenNanos = totalNanos/laps;

        long nanos = (chosenNanos) % 1000000;
        long millis = (chosenNanos / 1000000) % 1000;
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60));
        //long hour = (millis / (1000 * 60 * 60)) % 24;

        String time = String.format("Average time: %02d minutes, %02d seconds, %02d milliseconds, and %d nanoseconds", minute, second, millis, nanos); //%02d minutes, %02d seconds,
        System.out.println(time);

        long programMillis = programStop - programStart;
        long programSecond = (programMillis / 1000) % 60;
        long programMinutes = (programMillis / (1000 * 60));
        //long hour = (millis / (1000 * 60 * 60)) % 24;

        String programTime = String.format("Program runtime: %02d minutes, %02d seconds, %02d milliseconds", programMinutes, programSecond, programMillis); //%02d minutes, %02d seconds,
        System.out.println(programTime);



        //SudokuPrinter sP = new SudokuPrinter(fixed);
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
