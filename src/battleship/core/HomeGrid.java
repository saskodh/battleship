package battleship.core;

import java.awt.Point;
import java.util.ArrayList;

public class HomeGrid extends Grid{
	
    private Ship minesweeper;
    private Ship submarine;
    private Ship destroyer;
    private Ship battleship;
    private Ship aircraftCarrier;
    
    private boolean lastShotSunkedShip;
    private Ship lastSunkedShip;
    
	public HomeGrid(int rows, int cols) {
		super(rows, cols);
	}
	
	public boolean lastShotSunkedShip(){
		return lastShotSunkedShip;
	}
	
	public ArrayList<Point> getLastSunkedShipPositions(){
		return lastSunkedShip.getShipPositionsList();
	}
	
    private boolean isValidPlaceForAShip(Ship ship) {
    	boolean valid = true;
    	
    	for(Point p: ship.getShipPositionsList()){
    		
    		if(!isCoordinateValid(p.x, p.y) || !isPositionFree(p.x, p.y))
    			valid = false;
    	}
    	
    	return valid;   
    }
    
    private boolean isPositionFree(int i, int j){
    	return (board[i][j] == 0);
    }
    
    private void placeShip(Ship ship){

    	for(Point p: ship.getShipPositionsList()){

    		board[p.x][p.y] = ship.getGridValue();
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
   
    public boolean addMinesweeper(Point p, boolean isHorizontal) {
    	if(minesweeper != null)
    		return false;
    	
    	Ship mine = ShipCreator.createMinesweeper(p);
    	if(isHorizontal)
    		mine.setHorizontalOrientation();
    	else
    		mine.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(mine)){
    		minesweeper = mine;
    		placeShip(mine);
    		return true;
    	}
    	
    	return false;
    }

    public boolean addAircraftCarrier(Point p, boolean isHorizontal) {
    	if(aircraftCarrier != null)
    		return false;
    	
    	Ship ship = ShipCreator.createAircraftCarrier(p);
    	if(isHorizontal)
    		ship.setHorizontalOrientation();
    	else
			ship.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(ship)){
    		aircraftCarrier = ship;
    		placeShip(ship);
    		return true;
    	}
    	
    	return false;
    }

    public boolean addSubmarine(Point p, boolean isHorizontal) {
    	if(submarine != null)
    		return false;
    	
    	Ship ship = ShipCreator.createSubmarine(p);
    	if(isHorizontal)
    		ship.setHorizontalOrientation();
    	else
			ship.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(ship)){
    		submarine = ship;
    		placeShip(ship);
    		return true;
    	}
    	
    	return false;
    }
     
    public boolean addDestroyer(Point p, boolean isHorizontal) {
    	if(destroyer != null)
    		return false;
    	
    	Ship ship = ShipCreator.createDestroyer(p);
    	if(isHorizontal)
    		ship.setHorizontalOrientation();
    	else
			ship.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(ship)){
    		destroyer = ship;
    		placeShip(ship);
    		return true;
    	}
    	
    	return false;
    }

    public boolean addBattleship(Point p, boolean isHorizontal) {
    	if(battleship != null)
    		return false;
    	
    	Ship ship = ShipCreator.createBattleship(p);
    	if(isHorizontal)
    		ship.setHorizontalOrientation();
    	else
			ship.setVerticalOrientation();
    	
    	if(isValidPlaceForAShip(ship)){
    		battleship = ship;
    		placeShip(ship);
    		return true;
    	}
    	
    	return false;
    }
       
    /**
            Fires a shot on the grid
    */
    public boolean shot(int i, int j){
    	lastShotSunkedShip = false;
    	
    	if(isPositionFree(i, j))
    		return false;
    	
    	int gridValue = getGridVal(i,j);
    	
    	//if was shut before
    	if(gridValue < 0)
    		return false;
        
    	//TODO: needs another solution
        if(gridValue == minesweeper.getGridValue()){ 
        	minesweeper.scoreHit();
        	if(minesweeper.isSunk()){
        		lastShotSunkedShip = true;
        		lastSunkedShip = minesweeper;
        	}
        }
        if(gridValue == submarine.getGridValue()){
        	submarine.scoreHit();
        	if(submarine.isSunk()){
        		lastShotSunkedShip = true;
        		lastSunkedShip = submarine;
        	}
        }
        if(gridValue == battleship.getGridValue()){
        	battleship.scoreHit(); 
        	if(battleship.isSunk()){
        		lastShotSunkedShip = true;
        		lastSunkedShip = battleship;
        	}
        }
        if(gridValue == aircraftCarrier.getGridValue()){
        	aircraftCarrier.scoreHit(); 
        	if(aircraftCarrier.isSunk()){
        		lastShotSunkedShip = true;
        		lastSunkedShip = aircraftCarrier;
        	}
        }
        if(gridValue == destroyer.getGridValue()){
        	destroyer.scoreHit();
        	if(destroyer.isSunk()){
        		lastShotSunkedShip = true;
        		lastSunkedShip = destroyer;
        	}
        }
        
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
}