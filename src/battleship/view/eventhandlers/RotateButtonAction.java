package battleship.view.eventhandlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import battleship.view.BattleshipView;

public class RotateButtonAction extends MouseAdapter {
	
	private BattleshipView gui;
	
	public RotateButtonAction(BattleshipView gui2) {		
		gui=gui2;
	}	
	
	public void mousePressed(MouseEvent event) {
		//System.out.println("Horiz = " +gui.rotate());
		gui.toggleShipOrientation();
	}

}