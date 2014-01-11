package battleship.view.eventhandlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import battleship.view.BattleshipView;

public class ShowButtonAction extends MouseAdapter {
	
	private BattleshipView gui;
	
	public ShowButtonAction(BattleshipView gui2) {
		
		gui=gui2;
	}	
	
	public void mousePressed(MouseEvent event) {
		gui.showInfluenceMap();
	}
}
