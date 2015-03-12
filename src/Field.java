import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Jonathan on 22/02/2015.
 */
public class Field {

	public static final int ORDERED = 0;
	public static final int RANDOM = 1;
	public static final int ALL = 0;
	public static final int EMPTY = 1;
	public static final int FILLED = 2;
    private int[][] field;
    private int cubeLen;
    private Cell[][] cellField;
    private int xLen;
    private int yLen;
    private int len;

    /**
     *
     * @param field
     */
    public Field(int[][] field) {
        this.field = field;

        this.xLen = field.length;
        this.yLen = field[0].length; //Should always be same as xLen
        len = xLen;

        cellField = new Cell[xLen][yLen];

        cubeLen = (int)Math.sqrt(xLen);
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                cellField[i][j] = new Cell(field[i][j], i, j, xLen);
            }
        }
    }


    //Maybe use the cellField instead?
    public int getValue(int x, int y) {
        return field[x][y];
    }

    /**
     *
     * order: ORDERED = 0, RANDOM = 1
     * type: ALL = 0, EMPTY = 1, FILLED = 2
     * @return
     */
    public Stack<Cell> getCells(int order, int type) {

        Stack<Cell> resultStack = new Stack<Cell>();
        ArrayList<Cell> tempList = new ArrayList<Cell>();

        if(order == 0) {

            if (type == 0) {

                for (int i = 0; i < xLen; i++) {
                    for (int j = 0; j < yLen; j++) {
                        resultStack.push(cellField[i][j]); //Will stack make it backwards?
                    }
                }

            } else if (type == 1) {

                for (int i = 0; i < xLen; i++) {
                    for (int j = 0; j < yLen; j++) {
                        if (cellField[i][j].value == 0)
                            resultStack.push(cellField[i][j]);
                    }
                }

            } else if (type == 2) {

                for (int i = 0; i < xLen; i++) {
                    for (int j = 0; j < yLen; j++) {
                        if (cellField[i][j].value != 0)
                            resultStack.push(cellField[i][j]);
                    }
                }

            } else {
                System.out.println("Wrong type value");
            }

        } else if(order == 1) {
            if (type == 0) {

                for (int i = 0; i < xLen; i++) {
                    for (int j = 0; j < yLen; j++) {
                        tempList.add(cellField[i][j]); //Not backwards??
                    }
                }

            } else if (type == 1) {

                for (int i = 0; i < xLen; i++) {
                    for (int j = 0; j < yLen; j++) {
                        if (cellField[i][j].value == 0)
                            tempList.add(cellField[i][j]);
                    }
                }

            } else if (type == 2) {

                for (int i = 0; i < xLen; i++) {
                    for (int j = 0; j < yLen; j++) {
                        if (cellField[i][j].value != 0)
                            tempList.add(cellField[i][j]);
                    }
                }

            } else {
                System.out.println("Wrong type value");
            }


            //Put tempList into the stack
            for (Cell cell : tempList) {
                resultStack.push(cell);
            }


        } else {
            System.out.println("Wrong order value");
        }




        return resultStack;
    }
}
