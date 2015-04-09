import java.io.*;
import java.util.ArrayList;

/**
 * Created by Jonathan on 08/04/2015.
 */
public class SudokuFileWriter {

    /**
     *
     * @param puzzleList
     * @param path
     * @param format 1 for single line 9x9 input with dots for empty spaces, 2 for multiple lines
     * @throws IOException
     */
    public void write(ArrayList<int[][]> puzzleList, String path, int format) throws IOException {

        File fout = new File(path);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int[][] field : puzzleList) {
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    if (field[j][i] == 0) {
                        bw.write(".");
                    } else {
                        bw.write(field[j][i] + "");
                    }
                }
                if(format == 2) {
                    bw.newLine();
                }
            }
            bw.newLine();
        }

        bw.close();

    }
}
