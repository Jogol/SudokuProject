/**
 * Created by Jonathan on 16/03/2015.
 *
 * Verifies a Field
 */
public class VerifyField {

    static int cubeLen;

    /**
     *
     * @param field The field to be tested
     * @return Returns True if the field was correct
     */
    public boolean verifyField(Field field) {

        int len = field.getField().length;
        cubeLen = (int)Math.sqrt(len);


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
        int sqrRow = row/cubeLen;
        int sqrCol = col/cubeLen;
        int numOfVal = 0;
        for (int i = 0; i < cubeLen; i++) {
            for (int j = 0; j < cubeLen; j++) {
                if (field.getValue((sqrRow*cubeLen)+i, (sqrCol*cubeLen)+j) == value) {
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
        for (int i = 0; i < field.getField().length; i++) {
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
        for (int i = 0; i < field.getField().length; i++) {
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
