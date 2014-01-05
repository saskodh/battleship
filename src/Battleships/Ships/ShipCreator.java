package Battleships.Ships;

public class ShipCreator {
	public static Ship createAircraftCarrier(){
		Ship ship = new Ship();
		
		ship.setName("Aircraft Carrier");
		ship.setGridValue(5);
		ship.setNumSegments(5);
		ship.setIntactSegments(5);		
		
		return ship;
	}
	
	public static Ship createBattleship(){
		Ship ship = new Ship();
		
		ship.setName("Battleship");
		ship.setGridValue(4);
		ship.setNumSegments(4);
		ship.setIntactSegments(4);
		
		return ship;
	}
	
	public static Ship createDestroyer(){
		Ship ship = new Ship();
		
		ship.setName("Destroyer");
		ship.setGridValue(7);
		ship.setNumSegments(3);
		ship.setIntactSegments(3);
		
		return ship;
	}
	
	public static Ship createMinesweeper(){
		Ship ship = new Ship();
		
		ship.setName("Minesweeper");
		ship.setGridValue(2);
		ship.setNumSegments(2);
		ship.setIntactSegments(2);
		
		return ship;
	}
	
	public static Ship createSubmarine(){
		Ship ship = new Ship();
		
		ship.setName("Submarine");
		ship.setGridValue(3);
		ship.setNumSegments(3);
		ship.setIntactSegments(3);
		
		return ship;
	}
}
