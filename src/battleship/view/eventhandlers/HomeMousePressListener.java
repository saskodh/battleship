package battleship.view.eventhandlers;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import battleship.view.BattleshipView;

public class HomeMousePressListener extends MouseAdapter {
	
	private BattleshipView view;
	
	public HomeMousePressListener(BattleshipView gui2)	{
		view=gui2;
	}	
	
	public void mousePressed(MouseEvent event)	{
		
		view.homeGridClick(new Point(event.getX(), event.getY()));
	}
}
