package Battleships.Ships;

public class MyShip {
	private String name; 
	private int gridValue;
	private int numSegments;
	private int intactSegments;
	private boolean isHorizontal;
	
	public boolean isSunk(){
		return (intactSegments == 0);
	}
	
	public void scoreHit(){
		intactSegments--;
	}
	
	//getters and setters
	public boolean isHorizontal(){
		return isHorizontal;
	}	
	public void setHorizontalOrientation(){
		isHorizontal = true;
	}
	public void setVerticalOrientation(){
		isHorizontal = false;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGridValue() {
		return gridValue;
	}
	public void setGridValue(int gridValue) {
		this.gridValue = gridValue;
	}
	public int getNumSegments() {
		return numSegments;
	}
	public void setNumSegments(int numSegments) {
		this.numSegments = numSegments;
	}
	public int getIntactSegments() {
		return intactSegments;
	}
	public void setIntactSegments(int intactSegments) {
		this.intactSegments = intactSegments;
	}	
}
