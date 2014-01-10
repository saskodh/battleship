package Battleships;
/*
 * Author: Michael
 * Created: 16 April 2005 12:39:10
 * Modified: 16 April 2005 12:39:10
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.battleship.model.AttackGrid;
import com.battleship.model.HomeGrid;

public class Agent
{
	public Point nextShot(InfluenceMap map, AttackGrid attackGrid){
		int i = -1;
		int	j = -1;
		
		ArrayList<Point> hotSpots = map.getHotspots();
		System.out.println(hotSpots.size());
		
		boolean flag = false;
		
		for (Point point : hotSpots) {
			if(attackGrid.isEmpty(point.x, point.y)){
				i = point.x;
				j = point.y;
				flag = true;
				break;
			}				
		}
		
		if(!flag)
			System.out.println("New position choosen random");
		
		Random rand = new Random();
		while(!flag){
			i = rand.nextInt(10);
			j = rand.nextInt(10);
			
			//if co-ord is empty then set i,j to them
			if(attackGrid.isEmpty(i, j))
				flag = true;
		}
		
		return new Point(i, j);		
	}

	
	public HomeGrid placeShips() {
		//boolean
		HomeGrid g= new HomeGrid(10,10);
		
		while(!g.allShipsPlaced())
		{
			Random gen = new Random();
			int x = gen.nextInt(10);
			int y = gen.nextInt(10);
			int o = gen.nextInt(1);
					
			x = gen.nextInt(10);
			y = gen.nextInt(10);
			o = gen.nextInt(2);
			System.out.println("vertical sub x = " + x + "\n");
			System.out.println("vertical sub y = " + y + "\n");
			g.addSubmarine(new Point(x, y), (o == 0));
			
			x = gen.nextInt(10);
			y = gen.nextInt(10);
			o = gen.nextInt(2);		
			System.out.println("vertical battle x = " + x + "\n");
			System.out.println("vertical battle y = " + y + "\n");
			g.addBattleship(new Point(x, y), (o == 0));
		
			x = gen.nextInt(10);
			y = gen.nextInt(10);
			o = gen.nextInt(2);				
			System.out.println("vertical air x = " + x + "\n");
			System.out.println("vertical air y = " + y + "\n");
			g.addAircraftCarrier(new Point(x, y), (o == 0));
			
			x = gen.nextInt(10);
			y = gen.nextInt(10);
			o = gen.nextInt(2);				
			System.out.println("vertical mine x = " + x + "\n");
			System.out.println("vertical mine y = " + y + "\n");
			g.addMinesweeper(new Point(x, y), (o == 0));
	
			x = gen.nextInt(10);
			y = gen.nextInt(10);
			o = gen.nextInt(2);
			System.out.println("horizontal dest x = " + x + "\n");
			System.out.println("horizontal dest y = " + y + "\n");
			g.addDestroyer(new Point(x, y), (o == 0));
		}
		
		System.out.println("agent grid");
		System.out.println(g.toString());
		
		return g;
	}
	
}
