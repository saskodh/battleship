package Battleships;
/*
 * Author: Michael
 * Created: 12 February 2005 14:57:34
 * Modified: 12 February 2005 14:57:34
 */

import java.awt.Point;
import java.io.Serializable; 
import java.util.ArrayList;
public class InfluenceMap implements Serializable
{
	private int[][] map;
	
	private int hit = 9;

	/**
		Creates an influence map from a two dimensional array as a 10 by 10 cells.
		All cell values are set to 0
	*/
	public InfluenceMap() {
		map = new int[10][10];
			
		for (int a = 0; a< 10; a++)
			for (int b = 0; b < 10; b++)
				map[a][b] = 0;
	}
	
	/**
		This method is used sets an element to a value on the influence map
		
		@param i the row index
		@param j the column index
		@param value the value of the square 
	*/
	public void set(int i, int j, int value) {
		if(i > 10 || j > 10)
			throw new IllegalArgumentException("Number is bigger that the grid size");
		if(i < 0 || j < 0) 
			throw new IllegalArgumentException("Cordinate  cannot be negative");
		map[i][j] = value;
	}

	public int getVal(int i, int j)	{
		if(i < 0 || j < 0)
			throw new IllegalArgumentException("Number cannot be negative");
		if(i > 10 || j > 10)
			throw new IllegalArgumentException("Number is bigger that the grid size");
		return map[i][j];	
	}	
	
	/**
		Returns value of the cell or cells with the highest number on the influence map.
	
	*/
	private int getMaxHotspotVal() {
		int maxVal= 0;
		for(int i = 0; i<10; i++) {	
			for (int j = 0; j<10; j++) {
				if(map[i][j]>= maxVal && map[i][j]!= hit){				
					maxVal= map[i][j];
				}
			}
		}
		
		return maxVal;
	}

	/**Returns an int array containing the hotspots*/
	public ArrayList<Point> getHotspots(){
		ArrayList<Point> result = new ArrayList<Point>();
		
		int maxVal = getMaxHotspotVal();
		
		if(maxVal == 0)
			return result;
		
		for(int i = 0; i<10; i++) {	
			for (int j = 0; j<10; j++) {
				if(map[i][j] == maxVal) {
					result.add(new Point(i, j));
				}			
			}
		}		
		
		return result;
	}
	
	/**
		Increases the value of the specified cell's northern, southern, eastern and western
		neighbour by one. The actual specified cell has it's value changed to 9. This method will not alter 
		any cells who's value is already 9. If it is the second consquetive hit in a row or column the next element in that sequence 
		will be weighted to an even higher value
	*/
	public void hit(int i, int j) {
		
		map[i][j] =hit;
		
		try	{	
			//if southern is not a hit, increment it
			if(map[i+1][j] !=hit)
			{
				map[i+1][j] = map[i+1][j] + 2;
			}
			
			// if southern was also a hit and the northern isn't then increment eastern by 5
			if(map[i+1][j] ==hit && map[i-1][j] != hit)
			{
				map[i-1][j] = map[i-1][j] + 11;
			}
			
			//if northern is not a hit, increament it
			if(map[i-1][j] !=hit)
			{
				map[i-1][j] = map[i-1][j] + 2;
			}
			
			// if northern is a hit and southern isn't then increment southern by 8
			if(map[i-1][j] ==hit && map[i+1][j] !=hit)
			{
				map[i+1][j] = map[i+1][j] + 11;
			}
			
			//if eastern is not a hit, increment it
			if(map[i][j+1] !=hit)
				map[i][j+1] = map[i][j+1] + 4;
			
			//if eastern is a hit, and western isn't increment western by 11
			if(map[i][j+1] ==hit && map[i][j-1]!= hit)
			{
				map[i][j-1] = map[i][j-1] + 11;
			}

			// western is not a hit increment it
			if(map[i][j-1] !=hit)
				map[i][j-1] = map[i][j-1] + 4;			
			
			// western is a hit and eastern isn't increment eastern by 8
			if(map[i][j-1] ==hit && map[i][j+1] !=hit)
			{
				map[i][j+1] = map[i][j+1] + 11;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
	}	
	
	public void sunk(int i, int j){
		
		if(map[i][j] != hit)
			return;
		
		int[] dx = new int[]{-1, 1, 0, 0};
		int[] dy = new int[]{0, 0, -1, 1};
		int ii, jj;
		
		int k;
		for(k=0; k<dx.length; k++){
			ii = i+dx[k];
			jj = j+dy[k];
			
			try{
				if(map[ii][jj] != hit){
					if(map[ii][jj] % 2 == 1){
						//if odd
						map[ii][jj] -= 9;
					}else {
						if(k<2){
							//above and below for -2
							map[ii][jj] = map[ii][jj] - 2;
						}else {
							//left and right for -4
							map[ii][jj] = map[ii][jj] - 4;					
						}						
					}
				}
			} catch(Exception e){
				//e.printStackTrace();
			}
		}
	}
	
	/**
		Marks on the influence map where a miss is.
	*/
	public void miss(int i, int j)
	{
		map[i][j] = -5;
		
		try
		{ // if i,j is a hit and north east, and south east are all misses then set east to +9.
			/*0
			 0Xinc
			  0
			*//*element to the right is not hit or miss*/
				if(map[i-1][j+1] == -5/*above right a miss*/ && map[i+1][j+1] ==-5/*below right a miss*/ && map[i][j+1] ==hit /*east is a hit*/ && map[i][j+2] !=-5 /*east + 2 is not hit or miss*/&& map[i][j+2]  !=hit)
				{	
				
					map[i][j+2] = map[i][j+2] + 9;
					
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		try
		{ // north east and south east are both misses and east is a hit then east +2 is set to +9.
			/*0
			 0XXinc
			  0
			*/
				if(map[i-1][j+1] == -5/*above right is a miss*/ && map[i+1][j+1] ==-5/*below right is a miss*/ && map[i][j+2]  ==hit /*right is a hit*/ && map[i][j+3] !=-5 /*east + 3 is not a hit or miss*/ && map[i][j+3] !=-5)
				{	
					map[i][j+3] = map[i][j+3] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		
			try
			{ //  j+1 and j+2 and j+3 are hits then j +4 is set to +9.
			/*
			 0XXXinc
			  
			*//*element to the right is not hit or miss*/
				if(map[i][j+1] ==hit /*j+1 is a hit*/ && map[i][j+2] ==hit /*j + 2 is a hit*/&& map[i][j+3]  ==hit /*j +3 is a hit*/ && map[i][j+4] !=-5 /*j +4 is not a hit or a miss*/&& map[i][j+4] !=hit)
				{	
					map[i][j+4] = map[i][j+4] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		
		try
		{ //  j+1 and j+2 and j+3 and j+4 is set to +9.
			/*
			 0XXXXinc
			  
			*//*element to the right is not hit or miss*/
				if(map[i][j+1] ==hit /*east is a hit*/ && map[i][j+2] ==hit /*east + 2 is a hit*/&& map[i][j+3]  ==hit /*east +3 is a hit*/ && map[i][j+4] ==hit /*east +4 is a hit */&& map[i][j+5] !=hit &&/*east +5 is not a hit or a miss*/ map[i][j+5] !=-5)
				{	
					map[i][j+5] = map[i][j+5] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		/*western hits*/
		
		try
		{ // if i,j is a hit and north west, and south west are all misses then set west to +9.
			/*0
		   incX0
			  0
			//*element to the left is not hit or miss*/
				if(map[i-1][j-1] == -5/*above left a miss*/ && map[i+1][j-1] ==-5/*below left a miss*/ && map[i][j-1] ==hit /* right is a hit*/ && map[i][j-2] !=-5 /*j - 2 is not hit or miss*/&& map[i][j-2]  !=hit)
				{	
				
					map[i][j-2] = map[i][j-2] + 9;
					
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		
		try
		{ // three hits to the left of a miss then east +2 is set to +9.
			/*0
		  incXX0
			  0
			*/
				if(map[i-1][j-1] == -5/*above left is a miss*/ && map[i+1][j-1] ==-5/*below left is a miss*/ && map[i][j-2]  ==hit /*left is a hit*/ && map[i][j-3] !=-5 /*east + 3 is not a hit or miss*/ && map[i][j-3] !=-5)
				{	
					map[i][j-3] = map[i][j-3] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		
		try
		{ //  j-1 and j-2 and j-3 are hits then j -4 is set to +9.
			/*
			
			 incXXX0
			  
			*/
				if(map[i][j-1] ==hit /*j+1 is a hit*/ && map[i][j-2] ==hit /*j + 2 is a hit*/&& map[i][j-3]  ==hit /*j +3 is a hit*/ && map[i][j-4] !=-5 /*j +4 is not a hit or a miss*/&& map[i][j-4] !=hit)
				{	
					map[i][j-4] = map[i][j-4] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		
		try
		{ //  j-1 and j-2 and j-3 and j-4 is set to +9.
			/*
			 incXXXX0
			  
			*/
				if(map[i][j-1] ==hit /*east is a hit*/ && map[i][j-2] ==hit /*east + 2 is a hit*/&& map[i][j-3]  ==hit /*east +3 is a hit*/ && map[i][j-4] ==hit /*east +4 is a hit */&& map[i][j-5] !=hit &&/*east +5 is not a hit or a miss*/ map[i][j-5] !=-5)
				{	
					map[i][j-5] = map[i][j-5] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		
		/*southern hits*/
		
		
		try
		{ // if i,j +1 is a hit and north, west, east are all misses then set south to +9.
		/*	  0<
		     OX0
			 inc
			*/
				if(map[i+1][j] ==hit /*below is hit*/ && map[i+1][j-1] ==-5/*below left a miss*/ && map[i+1][j+1] ==-5 /*below right is a miss*/ && map[i+2][j] !=-5 /*i + 2 is not hit or miss*/&& map[i+2][j]  !=hit)
				{	
					map[i+2][j] = map[i+2][j] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		
		try
		{ // if i+1 is a hit and i+2 is a hit and north, west, east are all misses then set i+3 to +9.
		/*	  0<
		     OX0
			  X
			 inc
			*/
				if(map[i+1][j] ==hit /*below is a hit*/&& map[i+2][j] ==hit /*i+ 2 is a hit*/&& map[i+1][j-1] ==-5/*below left a miss*/ && map[i+1][j+1] ==-5 /*below right is a miss*/ && map[i+3][j] !=-5 /*i + 3 is not hit or miss*/&& map[i+3][j]  !=hit)
				{	
					map[i+3][j] = map[i+3][j] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}

		try
		{ // if i+1 is a hit and i+2 is a hit and i +3 is a hit then set i+4 to +9.
		/*	  0<
		      X
			  X
			  X
			 inc
			*/
				if(map[i+1][j] ==hit /*i+1 is a hit*/&& map[i+2][j] ==hit /*i+ 2 is a hit*/&& map[i+3][j] ==hit /*i+ 3 is a hit*/ && map[i+4][j] !=-5 /*i + 3 is not hit or miss*/&& map[i+4][j]  !=hit)
				{	
					map[i+4][j] = map[i+4][j] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		try
				{ // if i+1 is a hit and i+2 is a hit and i +3 i+4 are hits then set i + 5 to +9.
		/*	  0<
		      X
			  X
			  X
			  X
			 inc
			*/
				if(map[i+1][j] ==hit /*i+1 is a hit*/&& map[i+2][j] ==hit /*i+ 2 is a hit*/&& map[i+3][j] ==hit /*i+ 3 is a hit*/ && map[i+4][j] ==hit /*i+ 4 is a hit*/ && map[i+5][j] !=-5 /*i + 3 is not hit or miss*/&& map[i+5][j]  !=hit)
				{	
					map[i+5][j] = map[i+5][j] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}

		/*northern hits*/

		try
		{ // if i-1 is a hit and south, west, east are all misses then set -2 to +9.
		/*	 inc
		     OX0
			  0<
			*/
				if(map[i-1][j] ==hit /*above is hit*/ && map[i-1][j-1] ==-5/*above left a miss*/ && map[i-1][j+1] ==-5 /*above right is a miss*/ && map[i-2][j] !=-5 /*i - 2 is not hit or miss*/&& map[i-2][j]  !=hit)
				{	
					map[i-2][j] = map[i-2][j] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		
		
		try
		{ // if i-1 is a hit and i-2 is a hit and south, west, east are all misses then set i-3 to +9.
		/*	 inc
			  X
		     OX0
			  0<
			*/
				if(map[i-1][j] ==hit /*i-1 is hit*/ && map[i-2][j] ==hit /*i-2 is hit*/ && map[i-1][j-1] ==-5/*above left a miss*/ && map[i-1][j+1] ==-5 /*above right is a miss*/ && map[i-3][j] !=-5 /*i - 3 is not hit or miss*/&& map[i-3][j]  !=hit)
				{	
					map[i-3][j] = map[i-3][j] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}

		try
		{ // if i-1 is a hit and i-2 is a hit and i-3 is a hit then set i-4 to +9.
		/*	 inc
			  X
			  X
		      X
			  0<
			*/
				if(map[i-1][j] ==hit /*i-1 is hit*/ && map[i-2][j] ==hit /*i-2 is hit*/&& map[i-3][j] ==hit /*i-3 is hit*/ && map[i-4][j] !=-5 /*i - 4 is not hit or miss*/&& map[i-4][j]  !=hit)
				{	
					map[i-4][j] = map[i-4][j] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}
		
		
		try
		{ // if i-1 is a hit and i-2 is a hit and i-3 and i-4 is a hit then set i-5 to +9.
		/*	 inc
			  X
			  X
			  X
		      X
			  0<
			*/
				if(map[i-1][j] ==hit /*i-1 is hit*/ && map[i-2][j] ==hit /*i-2 is hit*/&& map[i-3][j] ==hit /*i-3 is hit*/ && map[i-4][j] ==hit /*i-4 is hit*/ && map[i-5][j] !=-5 /*i - 5 is not hit or miss*/&& map[i-5][j]  !=hit)
				{	
					map[i-5][j] = map[i-5][j] + 9;
				}
		}
		catch (ArrayIndexOutOfBoundsException e){/* do nothing*/}

	}
	
	public void searchDeadends() {
		for (int a = 0; a< 10; a++)
			for (int b = 0; b < 10; b++)
				setDeadends(a,b);		
	}
			
	private void setDeadends(int i, int j) {

		if(map[i][j]!=-5)
		{
			if(i==0 && j==0)
				if((map[i+1][j]==-9)&&(map[i][j+1]==-9))	
					map[i][j]=-7;

	
			// enters statement if it is in a corner
			//top right corner		
			if((i==0)&&(j==9))
				if((map[i+1][j]==-9)&&(map[i][j-1]==-9))				
					map[i][j]=-7;

		
			// enters statement if it is in a corner
			//bottom left corner		
			if((i==9)&&(j==0))			
				if((map[i-1][j]==-9)&&(map[i][j+1]==-9))				
					map[i][j]=-7;

			// enters statement if it is in a corner
			//bottom right corner		
			if((i==9)&&(j==9))			
				if((map[i-1][j]==-9)&&(map[i][j-1]==-9))				
					map[i][j]=-7;
		
		}	
	}

	/**Adds two influence maps objects together by summing their elements */
	public void addMap(InfluenceMap i){
		for(int x = 0; x <10; x++) {
			for(int y = 0; y <10; y++) {
				if(map[x][y]>=hit || i.getVal(x,y)>=hit)			
					map[x][y]= map[x][y] + 5;
				else				
					map[x][y] = map[x][y] + i.getVal(x,y);
					
			}
		}
	}	
	
	/**
		Sets all values in the influence map to 0
	*/
	public void clearAll() {
		for (int i = 0; i <10; i++)
			for (int j=0; j<10; j++)
				map[i][j] = 0;
	}
				
	public String toString() {
		String r = "";
		for (int i = 0; i < 10; i++) //change these to ROWS to use the default
		{			
			r = r + "|";
			for (int j = 0; j < 10; j++)
				r = r + map[i][j] ;
			r= r + "|\n";
		}
		return r;
	}
	
}