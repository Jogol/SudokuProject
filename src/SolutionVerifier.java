/**
 * Created by Jonathan on 22/02/2015.
 */
public class SolutionVerifier {

    public boolean verifyField(Field field) {

        int len = field.len;


        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int value = field.getValue(i, j);
                if (checkRow(value, i, j, field) && checkCol(value, i, j, field) && checkSqr(value, i, j, field) && (value != 0)) {

                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkSqr(int value, int row, int col, Field field) {
        int sqrRow = row/field.cubeLen;
        int sqrCol = col/field.cubeLen;
        int numOfVal = 0;
        for (int i = 0; i < field.cubeLen; i++) {
            for (int j = 0; j < field.cubeLen; j++) {
                if (field.getValue((sqrRow*field.cubeLen)+i, (sqrCol*field.cubeLen)+j) == value) {
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

    private static boolean checkCol(int value, int row, int col, Field field) {
        int numOfVal = 0;
        for (int i = 0; i < field.len; i++) {
            if (field.getValue(i, col) == value) {
                numOfVal++;
            }
        }
        if (numOfVal == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRow(int value, int row, int col, Field field) {
        int numOfVal = 0;
        for (int i = 0; i < field.len; i++) {
            if (field.getValue(row, i) == value) {
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
