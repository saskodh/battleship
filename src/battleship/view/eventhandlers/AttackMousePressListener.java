package battleship.view.eventhandlers;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import battleship.view.BattleshipView;

public class AttackMousePressListener extends MouseAdapter {
	
	private BattleshipView view;
	
	public AttackMousePressListener(BattleshipView gui2)	{
		view=gui2;
	}	  
	
	public void mousePressed(MouseEvent event)	{		
		view.attackGridClick(new Point(event.getX(), event.getY()));		
	}			
}
