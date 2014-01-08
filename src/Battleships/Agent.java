package Battleships;
/*
 * Author: Michael
 * Created: 16 April 2005 12:39:10
 * Modified: 16 April 2005 12:39:10
 */
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import com.battleship.model.AttackGrid;
import com.battleship.model.HomeGrid;

public class Agent
{
//	public Point nextShot(InfluenceMap m, AttackGrid g)
//	{
//		int i = -1;
//		int j = -1;
//		
//		int numHotSpots = m.getNumberOfHotspots();
//		
//		
//		if (numHotSpots==0 )
//		{
//			Random Powergen = new Random();
//			
//			//while(compAtt.getGridVal(i-
//			i = Powergen.nextInt(10);
//			j = Powergen.nextInt(10);
//		}
//		
//		
//		//if there is only one HS get it's co-ordinates on the IM
//		
//				if (numHotSpots==1 )
//				{
//					int checki = m.getHotspotI();
//					int checkj = m.getHotspotJ();
//					
//					if(g.getGridVal(checki, checkj) !=0)
//					{
//						m.set(checki, checkj, 0);
//						i= checki;
//						j= checkj;
//					}
//					
//					// if the element on the attack grid has not been hit then set i,j to it.
//					if(g.getGridVal(checki, checkj) ==0)
//					{
//						i= checki;
//						j= checkj;
//					}
//					
//					
//					else //set i, j to a random that has not been hit
//					{
//						Random generator = new Random();
//						
//						boolean empty =false;
//						//create random numbers
//						while(!empty)
//						{
//							checki = generator.nextInt(10);
//							checkj = generator.nextInt(10);
//							//if co-ord is empty then set i,j to them
//							if(g.getGridVal(checki, checkj) ==0)
//							{
//								empty = true;
//							}
//								
//						}
//					
//						i= checki;
//						j= checkj;
//					}
//						
//				}
//				
//				
//				//code to choose hotspots	
//				//pulls the first pair of co-ords from an array
//					if (numHotSpots>1)
//					{	
//						boolean noneFound = false;
//						System.out.println("Target multiple hotspots");
//						int[] refs = m.getIntHotspots();
//					
//						if(g.getGridVal(refs[0],refs[1]) == 0)	
//							{
//								i=refs[0];
//								j=refs[1];
//							}
//						
//						else 
//						{
//							int loop =0;
//							while(g.isValidPlaceForAShip(i,j) && !noneFound)
//							{
//								if (loop ==100)
//									noneFound = true;
//								for (int q= 2; q < refs.length-1; q++)
//								{
//									i=refs[q];
//									j=refs[q+1];
//									
//								}
//								loop++;
//							}
//							
//						}
//
//						int length = refs.length-2;
//					
//						for (int z= 0; z < length; z++)
//						{
//							refs[z] = refs[z+2];
//							//refs[z+1] = z+2;
//						}
//					
//					}
//					
//					
//		return new Point(i, j);
//	}
//	
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
		
		if(hotSpots.size() == 1){
			return new Point(hotSpots.get(0).x, hotSpots.get(0).y);
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
