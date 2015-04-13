import java.io.IOException;


public class Reduction implements SuperSolver{
	
	private static int CLAUSE_COUNT = 11988;
	
	@Override
	public int[][] SolveSudoku(int[][] f) {
		
		StringBuilder sb = new StringBuilder();
		
		int clauseCount = 0;
		
		for(int i = 0; i < f.length; i++){
			for(int j = 0; j < f.length; j++){
				if(f[i][j]>0){
					sb.append(i+""+j+""+f[i][j]+" 0 \n");
					clauseCount ++;
				}
				
			}
		}
		
		int totalClauseCount = CLAUSE_COUNT + clauseCount;
		
		StringBuffer buf = new StringBuffer("p cnf 999 "+totalClauseCount+"\n");
		
		buf.append(sb.toString());
		
		Mapper map = new Mapper(buf);
		
		try {
			map.buildSudokuClauses();
			dpSolver solver = new dpSolver();
			int[] solution = solver.solve(buf);
			return UnMapper.unMap(solution);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}

	@Override
	public String getName() {

		return this.getClass().getSimpleName();
	}
	
}
