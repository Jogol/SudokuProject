/**
 * Created by Jonathan on 09/03/2015.
 */
public class SudokuPrinter {

    SudokuPrinter(Field f) {

        for (int i = 0; i < f.xLen; i++) {
            for (int j = 0; j < f.xLen; j++) {
                if (f.field[i][j] > 9) {
                    System.out.print(f.field[i][j] + " ");
                } else {
                    System.out.print(" " + f.field[i][j] + " ");
                }

            }
            System.out.println("");
        }
    }

}
