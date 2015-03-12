import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jonathan on 08/03/2015.
 */
public class GenerateGridTL {

    ArrayList<ArrayList<Integer>> available = new ArrayList<ArrayList<Integer>>();

    public GenerateGridTL(Field f) {

        //Populate "available"

        ArrayList<Integer> tempList = new ArrayList<Integer>();
        for (int i = 0; i < f.xLen; i++) {
            tempList.add(i+1);
        }
        for (int j = 0; j < Math.pow(f.xLen, 2); j++) {
            ArrayList<Integer> list = new ArrayList<Integer>(tempList);
            available.add(list); //Add copies of the '1 to num' list, one for each cell
        }

        int c = 0;

        while (c != Math.pow(f.xLen, 2)+1) {
            if (available.get(c).size() != 0) {
                int index = getRand(0, available.get(c).size()-1);


            }
        }


    }

    private int getRand(int min, int max) {
        Random rand = new Random();
        return (rand.nextInt((max - min) + 1) + min);
    }
}
