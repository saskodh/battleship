package battleship.core;

public class Grid{
	int[][] board;
	int numRows, numCols;
	
	public Grid(int rows, int cols){
		numRows = rows;
		numCols = cols;
		board = new int[numRows][numCols];
		
		//init the grid
		for(int i=0; i<numRows; i++)
			for(int j=0; j<numCols; j++)
				board[i][j] = 0;
	}
	
    /**
	    Returns the value of the given grid index
	    @param i the row index
	    @param j the column index
	*/
	public int getGridVal(int i, int j)	{
	    return board[i][j];
	}
	
	public void setGridValue(int i, int j, int value){
		board[i][j] = value;
	}
	
	public boolean isCoordinateValid(int i, int j){
		boolean valid = true;
		
		if(i<0 || i>=numRows)
			valid = false;
		
		if(j<0 || j>=numCols)
			valid = false;
		
		return valid;
	}
}