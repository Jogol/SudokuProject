import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jonathan on 02/04/2015.
 * Reads in sudokupuzzles from file and returns them as an Arraylist<int[][]>
 * Assumes correct input
 *
 * Example: "D:\\Library\\Desktop\\sudoku.txt"
 */
public class SudokuReader {

    /**
     * Accepted formats:
     * 1: Empty spaces as dots or zeroes, numbers normally. Only 9x9
     *
     * 2:Empty spaces as zeroes, numbers normally. New line designates new line in puzzle. Only 9x9
     */
    private int format;

    /**
     *
     * @param format 1 for single line 9x9 input with dots or zeroes for empty spaces, 2 for multiple lines
     * @param fileName Name of file containing text
     * @return An ArrayList<int[][]> with sudoku fields. Null if none were found.
     */
    public ArrayList<int[][]> readSudoku(int format, String fileName) {

        this.format = format;

        BufferedReader reader = null;
        ArrayList<int[][]> list = null;

        try {
            File file = new File(fileName);
            reader = new BufferedReader(new FileReader(file));


            switch (format) {
                case 1:
                    list = lineFormat(reader);
                    break;
                case 2:
                    list = squareFormat(reader);
                    break;
                default:
                    System.out.println("Wrong format code! Printing file");
                    int ch;
                    while ((ch = reader.read()) != -1) {
                        System.out.println(ch);
                    }


            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Index out of bounds!");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return list;
    }

    private ArrayList<int[][]> squareFormat(BufferedReader reader) throws IOException, IndexOutOfBoundsException {

        ArrayList<int[][]> fieldList = new ArrayList<int[][]>();
        int[][] field = null;
        int x = 0;
        int y = 0;
        int ch;

        while ((ch = reader.read()) != -1) {

            if (field == null)
                field = new int[9][9];


            if (ch == 13) {
                reader.read(); //Drop the 10
                ch = reader.read();

                if (ch == 13) {
                    ch = reader.read(); //Drop the 10
                    fieldList.add(field);
                    field = null;
                    field = new int[9][9];
                    x = 0;
                    y = 0;
                    continue;
                } else {
                    x = 0;
                    y++;
                }


            }

            if (y >= 9) {
                System.out.println("Error, y = " + y);
            } else {
                char c = (char) ch;
                if (ch == '.' || ch == '0') {
                    field[x][y] = 0;
                    x++;
                } else {
                    field[x][y] = Character.getNumericValue(ch);
                    x++;
                }

                if(x > 9) {
                    System.out.println("should not happen");
                    x = 0;
                    y++;
                }

            }

        }
        fieldList.add(field);

        return  fieldList;

    }

    private ArrayList<int[][]> lineFormat(BufferedReader reader) throws IOException, IndexOutOfBoundsException{
        //do shit


        ArrayList<int[][]> fieldList = new ArrayList<int[][]>();
        int[][] field = null;
        int x = 0;
        int y = 0;
        int ch;

        while ((ch = reader.read()) != -1) {

            if (field == null)
                field = new int[9][9];


            if (ch == 13) {
                fieldList.add(field);
                field = null;
                reader.read(); //Empty the 10
                field = new int[9][9];
                x = 0;
                y = 0;
            } else if (y >= 9) {
                System.out.println("Error, y = " + y);
            } else {
                char c = (char) ch;
                if (ch == '.' || ch == '0') {
                    field[x][y] = 0;
                    x++;
                } else {
                    field[x][y] = Character.getNumericValue(ch);
                    x++;
                }

                if(x >= 9) {
                    x = 0;
                    y++;
                }

            }

        }
        fieldList.add(field);

        return  fieldList;
    }



}
