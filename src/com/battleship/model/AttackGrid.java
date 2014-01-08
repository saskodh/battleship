package com.battleship.model;

public class AttackGrid extends Grid{
	
	private final int HIT = 8;
	private final int MISS = 1;

	public AttackGrid(int rows, int cols) {
		super(rows, cols);
	}
	
	public boolean isEmpty(int i, int j){
		return (board[i][j] == 0);
	}
	
	public void addHit(int i, int j){
		board[i][j] = HIT;
	}
	
	public void addMiss(int i, int j){
		board[i][j] = MISS;
	}
	
	
	/**
		Checks the grid references and returns a boolean value if there is a ship on that spot
		
		@param i the column of the grid reference
		@param j the row of the grid reference
		
		@return a boolean value, true if the grid contains a ship and false if it contains either a miss or empty		
	*/
	public boolean isValidPlaceForAShip(int i, int j)
	{
		int index;
		index = this.getGridVal(i,j);

		if (index >1 && index <8 ) 
			return true;
		
		else return false;
		
	}

}
