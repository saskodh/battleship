package Battleships.Ships;

public class ShipCreator {
	public static MyShip createAircraftCarrier(){
		MyShip ship = new MyShip();
		
		ship.setName("Aircraft Carrier");
		ship.setGridValue(5);
		ship.setNumSegments(5);
		ship.setIntactSegments(5);		
		
		return ship;
	}
	
	public static MyShip createBattleship(){
		MyShip ship = new MyShip();
		
		ship.setName("Battleship");
		ship.setGridValue(4);
		ship.setNumSegments(4);
		ship.setIntactSegments(4);
		
		return ship;
	}
	
	public static MyShip createDestroyer(){
		MyShip ship = new MyShip();
		
		ship.setName("Destroyer");
		ship.setGridValue(7);
		ship.setNumSegments(3);
		ship.setIntactSegments(3);
		
		return ship;
	}
	
	public static MyShip createMinesweeper(){
		MyShip ship = new MyShip();
		
		ship.setName("Minesweeper");
		ship.setGridValue(2);
		ship.setNumSegments(2);
		ship.setIntactSegments(2);
		
		return ship;
	}
	
	public static MyShip createSubmarine(){
		MyShip ship = new MyShip();
		
		ship.setName("Submarine");
		ship.setGridValue(3);
		ship.setNumSegments(3);
		ship.setIntactSegments(3);
		
		return ship;
	}
}
