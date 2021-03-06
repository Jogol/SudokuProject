
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Jonathan on 16/03/2015.
 * Creates an empty field, solves it randomly, and then removes numbers
 * Can have Multiple Solutions (1 minimum)
 */

public class PuzzleGeneratorMS {


    ArrayList<int[]> randomPosList;
    int hints;

    public ArrayList<int[][]> puzzleGenerator(int size, int number, int hints) {

        this.hints = hints;
        ArrayList<int[][]> fieldList = new ArrayList<int[][]>();
        SolutionVerifier sv = new SolutionVerifier();
        randomPosList = makeRandomPosList(size);

        for (int i = 0; i < number; i++) {
            int[][] field = createEmpty(size);
            BTFCCPMRV better = new BTFCCPMRV();
            BT basic = new BT();

            int[][] fullField = basic.SolveSudoku(field);

            if (!sv.verifyField(fullField)) {
                System.out.println("Incorrect field! Quitting!");
                return null;
            } else {
                fieldList.add(makePartialRnd(fullField, hints));
            }

        }

        return fieldList;

    }

    private ArrayList<int[]> makeRandomPosList(int size) {

        ArrayList<int[]> posList = new ArrayList<int[]>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int[] coord = {i, j};
                posList.add(coord);
            }
        }

        Collections.shuffle(posList);
        return posList;
    }

    /**
     *  Takes a full field and removes a set amount of numbers
     * These puzzles can have many solutions
     * @param field The full field
     * @param num The number of numbers to be left
     * @return
     */
    private int[][] makePartialRnd(int[][] field, int num) {
        int size = field.length;
        int remove = size * size - num;

        for (int[] pos : randomPosList) {
            if (remove == 0) {
                break;
            }

            field[pos[0]][pos[1]] = 0;
            remove--;

        }

        return field;

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

