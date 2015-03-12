/*
import java.util.Stack;


*/
/**
 * 
 * @author joelkallinkilman
 *//*






public class Solver {
	
	*/
/*
	 * These variables are passed as arguments to an object of class
	 * Field, to get the corresponding cells. i.e. Field.getCells(ORDERED,ALL);
	 * returns a stack of all (blank and filled) cells ordered from top left to bottom right.
	 * While Field.getCells(RANDOM,EMPTY); returns all empty cells in a randomly ordered list.
	 *//*

	public static final int ORDERED = 0;
	public static final int RANDOM = 1;
	public static final int ALL = 0;
	public static final int EMPTY = 1;
	public static final int FILLED = 2;
	
	public Stack<Cell> unvisited;
	public Stack<Cell> guessed = new Stack<Cell>();
	
	
	
	public Field solve(Field f){
		
		//put all empty cells on a stack
		unvisited = f.getCells(ORDERED,EMPTY);
		while(!unvisited.isEmpty()){
			//current cell being investigated
			Cell investigate = unvisited.pop();
			//guess a number for the cell
			if(guess(investigate, f)){
				//guess was succesful, move along
				//TODO gissar att guessed.push ska ligga här
			} else {
				//no matching number could be found, we need to backtrack!
				//we take our latest guess and push it to the stack, so it will be investigated next.
				if(guessed.isEmpty){
					//we can backtrack no further, impossible field
					System.exit(1);
				}
				unvisited.push(guessed.pop());
			}
		}
		



	}
	
	public boolean guess(Cell c, Field f){
		
		for(int i = c.getValue()+1; i < f.len+1; i++){
			if(c.isPossible(i, f)){
				//found a viable number!
				c.setValue(i);
				return true;
			}
		}
		//No guesses could be found
		return false;
	}
}
*/
