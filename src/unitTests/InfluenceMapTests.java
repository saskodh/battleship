package unitTests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import battleship.core.InfluenceMap;
import battleship.core.Ship;
import battleship.core.ShipCreator;

public class InfluenceMapTests {
	private InfluenceMap infMap;
	
	@Before
	public void setUp(){
		infMap = new InfluenceMap();
	}
	
	@Test
	public void testShip(){
		Ship air = ShipCreator.createAircraftCarrier(new Point(0, 0));
		air.setHorizontalOrientation();
		
		for(Point p : air.getShipPositionsList()){
			System.out.print(p.x + "," + p.y + " | ");
		}
		
		System.out.println();
		
		air.setVerticalOrientation();
		
		for(Point p : air.getShipPositionsList()){
			System.out.print(p.x + "," + p.y + " | ");
		}
	}
//	
//	@Test
//	public void sunkMethod(){
//		
//		infMap.hit(5, 5);
//		infMap.hit(0, 0);
//		infMap.hit(0, 5);
//		infMap.hit(5, 0);
//		infMap.hit(9, 5);
//		infMap.hit(5, 9);
//		infMap.hit(4, 4);
//		infMap.hit(0, 9);
//		infMap.hit(9, 9);
//		infMap.hit(3, 8);
//		infMap.hit(8, 9);
//
//		infMap.hit(3, 4);
//		infMap.hit(5, 4);
//
//		System.out.println(infMap.toString());
//		
//		infMap.sunk(5, 5);
//		infMap.sunk(0, 0);
//		infMap.sunk(0, 5);
//		infMap.sunk(5, 0);
//		infMap.sunk(9, 5);
//		infMap.sunk(5, 9);
//		infMap.sunk(4, 4);
//		infMap.sunk(0, 9);
//		infMap.sunk(9, 9);
//		infMap.sunk(3, 8);
//		
//		infMap.sunk(3, 4);
//		infMap.sunk(5, 4);
//		
//		String expected = infMap.toString();		
//		System.out.println(expected);
//		
//		infMap = new InfluenceMap();
//		infMap.hit(5, 5);
//		infMap.hit(0, 0);
//		infMap.hit(0, 5);
//		infMap.hit(5, 0);
//		infMap.hit(9, 5);
//		infMap.hit(5, 9);
//		infMap.hit(4, 4);
//		infMap.hit(0, 9);
//		infMap.hit(9, 9);
//		infMap.hit(3, 8);
//		infMap.hit(8, 9);
//		
//		infMap.hit(3, 4);
//		infMap.hit(5, 4);
//		
//		infMap.sunkk(5, 5);
//		infMap.sunkk(0, 0);
//		infMap.sunkk(0, 5);
//		infMap.sunkk(5, 0);
//		infMap.sunkk(9, 5);
//		infMap.sunkk(5, 9);
//		infMap.sunkk(4, 4);
//		infMap.sunkk(0, 9);
//		infMap.sunkk(9, 9);
//		infMap.sunkk(3, 8);
//		infMap.sunkk(3, 4);
//		infMap.sunkk(5, 4);
//		
//		String received = infMap.toString();
//		System.out.println(received);
//		
//		assertTrue(expected.equals(received));
//	}
//
//	@Test
//	public void setDeadEndsMethod(){
//		infMap.hit(5, 5);
//		infMap.hit(0, 0);
//		infMap.hit(0, 5);
//		infMap.hit(5, 0);
//		infMap.hit(9, 5);
//		infMap.hit(5, 9);
//		infMap.hit(4, 4);
//		infMap.hit(0, 9);
//		infMap.hit(9, 9);
//		infMap.hit(3, 8);
//		infMap.hit(8, 9);
//		
//		
//System.out.println(infMap.toString());
//		
//		infMap.setDeadends(5, 5);
//		infMap.setDeadends(0, 0);
//		infMap.setDeadends(0, 5);
//		infMap.setDeadends(5, 0);
//		infMap.setDeadends(9, 5);
//		infMap.setDeadends(5, 9);
//		infMap.setDeadends(4, 4);
//		infMap.setDeadends(0, 9);
//		infMap.setDeadends(9, 9);
//		infMap.setDeadends(3, 8);
//		
//		
//		String expected = infMap.toString();		
//		System.out.println(expected);
//		
//		infMap = new InfluenceMap();
//		infMap.hit(5, 5);
//		infMap.hit(0, 0);
//		infMap.hit(0, 5);
//		infMap.hit(5, 0);
//		infMap.hit(9, 5);
//		infMap.hit(5, 9);
//		infMap.hit(4, 4);
//		infMap.hit(0, 9);
//		infMap.hit(9, 9);
//		infMap.hit(3, 8);
//		infMap.hit(8, 9);
//		
//		infMap.setDeadendss(5, 5);
//		infMap.setDeadendss(0, 0);
//		infMap.setDeadendss(0, 5);
//		infMap.setDeadendss(5, 0);
//		infMap.setDeadendss(9, 5);
//		infMap.setDeadendss(5, 9);
//		infMap.setDeadendss(4, 4);
//		infMap.setDeadendss(0, 9);
//		infMap.setDeadendss(9, 9);
//		infMap.setDeadendss(3, 8);
//		
//		String received = infMap.toString();
//		System.out.println(received);
//		
//		assertTrue(expected.equals(received));
//	}
	
}
