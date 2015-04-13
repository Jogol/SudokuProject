import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * Good values for hints are:
 * 9: 20
 * 16: 64
 */
public class Main {

    public static void main(String[] args) {

        SudokuReader sr = new SudokuReader();
        SudokuPrinter sp = new SudokuPrinter();
        SudokuFileWriter sfw = new SudokuFileWriter();
        ArrayList<int[][]> list = sr.readSudoku(2, "D:\\Library\\Desktop\\sudoku.txt");
        try {
            sfw.write(list, "D:\\Library\\Desktop\\sudoku2.txt", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int[][] field : list) {
            sp.print(field);
        }


    }


    private static String timeFormatterNanos(long inNanos) {
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

    private static String timeFormatterMillis(long millis) {
        long seconds = TimeUnit.SECONDS.convert(millis, TimeUnit.MILLISECONDS);
        long minutes = TimeUnit.MINUTES.convert(millis, TimeUnit.MILLISECONDS);

        String time = String.format("%02d min, %02d s, %02d ms", minutes, seconds, millis);
        return time;
    }

    private static int[][] createSimpleField() {
        int[][] simpleField = { {1,4,7,2,5,8,3,6,9} , {2,5,8,3,6,9,4,7,1} , {3,6,9,4,7,1,5,8,2}
                , {4,7,1,5,8,2,6,9,3} , {5,8,2,6,9,3,7,1,4} , {6,9,3,7,1,4,8,2,5} , {7,1,4,8,2,5,9,3,6}
                , {8,2,5,9,3,6,1,4,7} , {9,3,6,1,4,7,2,5,8}};
        return simpleField;
    }

    private static int[][] createTwoSolutions() {
        int[][] simpleField = { {9,0,6,0,7,0,4,0,3} ,
                                {0,0,0,4,0,0,2,0,0} ,
                                {0,7,0,0,2,3,0,1,0} ,
                                {5,0,0,0,0,0,1,0,0} ,
                                {0,4,0,2,0,8,0,6,0} ,
                                {0,0,3,0,0,0,0,0,5} ,
                                {0,3,0,7,0,0,0,5,0} ,
                                {0,0,7,0,0,5,0,0,0} ,
                                {4,0,5,0,1,0,7,0,8}};
        return simpleField;
    }

    private static int[][] createOneSolution() {
        int[][] simpleField = { {1,4,7,2,5,8,3,6,9} , {2,5,8,3,6,9,4,7,1} , {3,6,9,4,7,1,5,8,2}
                , {4,7,1,5,8,2,6,9,3} , {5,8,2,6,0,3,7,1,4} , {6,9,3,7,1,4,8,2,5} , {7,1,4,8,2,5,9,3,6}
                , {8,2,5,9,3,6,1,4,7} , {9,3,6,1,4,7,2,5,8}};
        return simpleField;
    }

    private static int[][] createHolyField() {
        int[][] simpleField = { {1,4,0,2,5,8,0,6,9} , {2,0,8,3,6,9,0,7,1} , {3,0,9,4,7,1,0,8,2}
                , {4,0,1,0,8,2,6,9,0} , {0,8,2,6,9,0,7,1,4} , {6,9,3,7,1,0,8,2,5} , {7,0,4,8,2,5,9,3,6}
                , {8,2,5,9,3,0,0,0,7} , {9,3,0,1,4,0,2,0,8}};
        return simpleField;
    }

    private static int[][] createHard16x16() {
        int[][] field = {{3,13,0,0,0,0,0,9,0,0,0,1,0,2,0,0} ,
                {0,0,10,0,0,0,0,0,0,0,0,0,0,11,0,0} ,
                {0,0,0,11,10,0,0,2,12,0,0,0,0,0,0,0} ,
                {6,0,0,0,0,0,0,16,0,0,0,0,0,0,0,0} ,
                {0,0,0,0,0,0,0,0,0,0,0,0,0,4,15,0} ,
                {0,7,0,0,0,0,0,0,11,0,13,0,8,0,3,1} ,
                {0,0,9,0,0,13,0,0,0,0,0,8,0,0,0,0} ,
                {0,8,11,0,2,0,0,0,0,0,0,15,0,0,0,0} ,
                {1,0,4,13,9,0,5,0,0,0,0,0,0,0,0,0} ,
                {0,3,0,7,0,0,6,10,0,0,0,0,0,0,0,0} ,
                {8,0,0,0,0,11,1,0,0,0,0,0,5,0,0,15} ,
                {11,0,0,2,0,16,15,0,9,0,0,10,0,0,0,0} ,
                {0,0,0,16,0,0,2,1,0,8,0,0,0,5,0,4} ,
                {0,0,0,0,0,0,0,13,0,5,0,9,16,0,7,0} ,
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0} ,
                {0,6,7,0,0,0,0,0,0,14,0,0,11,0,0,0 }};

        return field;
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
