
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Jonathan on 23/03/2015.
 * Creates an empty field, solves it randomly, and then removes numbers
 * Can only have one Single Solution (1 exactly)
 * Doesn't take hints as a parameter, as it will use as many hints as it needs.
 * Is pretty slow.
 */

public class PuzzleGeneratorSS {


    ArrayList<int[]> randomPosList;
    int[][] fullField;


    public ArrayList<int[][]> puzzleGenerator(int size, int number) {

        ArrayList<int[][]> fieldList = new ArrayList<int[][]>();
        SolutionVerifier sv = new SolutionVerifier();


        for (int i = 0; i < number; i++) {
            if (number > 1000 && i%100==0)
                System.out.println(i + "/" + number);
            randomPosList = makeRandomPosList(size);
            int[][] field = createEmpty(size);
            BTFCCP better = new BTFCCP();
            BT basic = new BT();

            fullField = basic.SolveSudoku(field);

            if (!sv.verifyField(fullField)) {
                System.out.println("Incorrect field! Quitting!");
                return null;
            } else {
                boolean notDone = true;
                for (int[] pos : randomPosList) {
                    int value = fullField[pos[0]][pos[1]];
                    fullField[pos[0]][pos[1]] = 0;
                    MultSolFinder msFinder = new MultSolFinder();
                    if(msFinder.hasMultSol(fullField)) {
                        fullField[pos[0]][pos[1]] = value;
                    }
                }

                fieldList.add(fullField);
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

