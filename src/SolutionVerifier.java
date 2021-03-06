/**
 * Created by Jonathan on 22/02/2015.
 */
public class SolutionVerifier {

    static int cubeLen;

    /**
     *
     * @param field The field to be tested
     * @return Returns True if the field was correct
     */
    public boolean verifyField(int[][] field) {

        int len = field.length;
        cubeLen = (int)Math.sqrt(len);


        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int value = field[i][j];
                if (checkRow(value, i, j, field) && checkCol(value, i, j, field) && checkSqr(value, i, j, field) && (value != 0)) {

                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkSqr(int value, int row, int col, int[][] field) {
        int sqrRow = row/cubeLen;
        int sqrCol = col/cubeLen;
        int numOfVal = 0;
        for (int i = 0; i < cubeLen; i++) {
            for (int j = 0; j < cubeLen; j++) {
                if (field[(sqrRow*cubeLen)+i][(sqrCol*cubeLen)+j] == value) {
                    numOfVal++;
                }
            }
        }

        if (numOfVal == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkCol(int value, int row, int col, int[][] field) {
        int numOfVal = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i][col] == value) {
                numOfVal++;
            }
        }
        if (numOfVal == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRow(int value, int row, int col, int[][] field) {
        int numOfVal = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[row][i] == value) {
                numOfVal++;
            }
        }
        if (numOfVal == 1) {
            return true;
        } else {
            return false;
        }
    }

}
