package com.battleship.model;

import Battleships.Ships.Ship;
import Battleships.Ships.ShipCreator;

public class HomeGrid extends Grid{
	
    private Ship minesweeper;
    private Ship submarine;
    private Ship destroyer;
    private Ship battleship;
    private Ship aircraftCarrier;
    
	public HomeGrid(int rows, int cols) {
		super(rows, cols);
	}
	
    private boolean isValidPlaceForAShip(int row, int column, Ship ship) {
    	boolean valid = true;
    	int i = row;
    	int j = column;
    	
    	for(int dx=0; dx<ship.getNumSegments(); dx++){
    		if(ship.isHorizontal()){
    			j = column + dx;
    		}else {
    			i = row + dx;
    		}  
    		
    		if(!isCoordinateValid(i, j) || !isPositionFree(i, j))
    			valid = false;
    	}
    	
    	return valid;   
    }
    
    private boolean isPositionFree(int i, int j){
    	return (board[i][j] == 0);
    }
    
    private void placeShip(int row, int col, Ship ship){
    	int i = row;
    	int j = col;
    	
    	for(int dx=0; dx<ship.getNumSegments(); dx++){
    		if(ship.isHorizontal()){
    			j = col + dx;
    		}else {
    			i = row + dx;
    		}  
    		
    		board[i][j] = ship.getGridValue();
    	}
    }
    
    public boolean allShipsSunked() {
            boolean res =  minesweeper.isSunk() && 
                           submarine.isSunk() && 
                           destroyer.isSunk() && 
                           battleship.isSunk() && 
                           aircraftCarrier.isSunk();
            
            return res;
    }               
    
    public boolean allShipsPlaced(){
    	boolean res = true;
    	
    	if(minesweeper == null)
			res = false;
    	if(submarine == null)
			res = false;
    	if(destroyer == null)
			res = false;
    	if(battleship == null)
			res = false;
    	if(aircraftCarrier == null)
			res = false;
    	
    	return res;
    }
   
    public boolean addMinesweeper(int i, int j, boolean isHorizontal) {
    	if(minesweeper != null)
    		return false;
    	
    	Ship mine = ShipCreator.createMinesweeper();
    	if(isHorizontal)
    		mine.setHorizontalOrientation();
    	else
    		mine.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(i, j, mine)){
    		minesweeper = mine;
    		placeShip(i, j, mine);
    		return true;
    	}
    	
    	return false;
    }

    public boolean addAircraftCarrier(int i, int j, boolean isHorizontal) {
    	if(aircraftCarrier != null)
    		return false;
    	
    	Ship ship = ShipCreator.createAircraftCarrier();
    	if(isHorizontal)
    		ship.setHorizontalOrientation();
    	else
			ship.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(i, j, ship)){
    		aircraftCarrier = ship;
    		placeShip(i, j, ship);
    		return true;
    	}
    	
    	return false;
    }

    public boolean addSubmarine(int i, int j, boolean isHorizontal) {
    	if(submarine != null)
    		return false;
    	
    	Ship ship = ShipCreator.createSubmarine();
    	if(isHorizontal)
    		ship.setHorizontalOrientation();
    	else
			ship.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(i, j, ship)){
    		submarine = ship;
    		placeShip(i, j, ship);
    		return true;
    	}
    	
    	return false;
    }
     
    public boolean addDestroyer(int i, int j, boolean isHorizontal) {
    	if(destroyer != null)
    		return false;
    	
    	Ship ship = ShipCreator.createDestroyer();
    	if(isHorizontal)
    		ship.setHorizontalOrientation();
    	else
			ship.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(i, j, ship)){
    		destroyer = ship;
    		placeShip(i, j, ship);
    		return true;
    	}
    	
    	return false;
    }

    public boolean addBattleship(int i, int j, boolean isHorizontal) {
    	if(battleship != null)
    		return false;
    	
    	Ship ship = ShipCreator.createBattleship();
    	if(isHorizontal)
    		ship.setHorizontalOrientation();
    	else
			ship.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(i, j, ship)){
    		battleship = ship;
    		placeShip(i, j, ship);
    		return true;
    	}
    	
    	return false;
    }
       
    /**
            Fires a shot on the grid
    */
    public boolean shot(int i, int j){
    	
    	if(isPositionFree(i, j))
    		return false;   
    	
    	int gridValue = getGridVal(i,j);
    	
    	//if was shut before
    	if(gridValue < 0)
    		return false;
        
    	//TODO: needs another solution
        if(gridValue == minesweeper.getGridValue()) 
        	minesweeper.scoreHit();         
        if(gridValue == submarine.getGridValue()) 
        	submarine.scoreHit();
        if(gridValue == battleship.getGridValue())
        	battleship.scoreHit(); 
        if(gridValue == aircraftCarrier.getGridValue())
        	aircraftCarrier.scoreHit(); 
        if(gridValue == destroyer.getGridValue())
        	destroyer.scoreHit();
        
        setGridValue(i, j, gridValue - 8);
        
        return true;
    }

    public String toString() {
        String r = "";
        for (int i = 0; i < numRows; i++) {                    
            r = r + "|";
            for (int j = 0; j < numCols; j++)
                    r = r + board[i][j];
            r= r + "|\n";
        }
        return r;
    }

    public String printIsSunk() {
        String MINESWEEPER =("Minesweeper is intact");
        String SUBMARINE =("Submarine is intact");
        String DESTROYER =("Destroyer is intact");
        String BATTLESHIP =("Battleship is intact");
        String AIRCRAFTCARRIER =("Aircraft Carrier is intact");
                
        if (minesweeper.isSunk())
                 MINESWEEPER =("Minesweeper is SUNK");
        
        if (submarine.isSunk())
                 SUBMARINE =("Submarine is SUNK");
        
        if (destroyer.isSunk())
                 DESTROYER =("Destroyer is SUNK");
        
        
        if (battleship.isSunk())
                 BATTLESHIP =("Battleship is SUNK");
        
        if (aircraftCarrier.isSunk())
                 AIRCRAFTCARRIER =("Aircraft Carrier is SUNK");

        
        return (MINESWEEPER + "\n" +SUBMARINE + "\n" +  DESTROYER + "\n" + BATTLESHIP+ "\n" +AIRCRAFTCARRIER); 
    }

    public String printIsPlaced() {
        System.out.println("The following ships are now placed ");
        String Minesweeper="Minesweeper NOT Placed";
        String Destroyer="Destroyer NOT Placed";
        String Submarine="Submarine NOT placed";
        String Battleship="Battleship NOT placed";
        String AircraftCarrier="Aircraft Carrier NOT placed";
        
        if (minesweeper != null) 
                Minesweeper="Minesweeper has been placed";

        if (destroyer != null) 
                Destroyer="Destroyer has been placed";
        
        if (submarine != null) 
                Submarine="Submarine has been placed";
        
        if (battleship != null)
                Battleship="Battleship has been placed";
        
        if(aircraftCarrier != null)
                AircraftCarrier="Aircraft Carrier has been placed";
        
        return Minesweeper + "\n" + Destroyer + "\n" + Submarine + "\n" + Battleship + "\n" + AircraftCarrier;
    }
}