package battleship.view.eventhandlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import battleship.view.BattleshipView;

public class NewButtonAction extends MouseAdapter {
	
	private BattleshipView gui;
	
	public NewButtonAction(BattleshipView gui2) {		
		gui=gui2;
	}	
	
	public void mousePressed(MouseEvent event) {
		gui.newGame();
		
	}
}
