package battleship.view.eventhandlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuitButtonAction extends MouseAdapter {
	
	public QuitButtonAction() {}	
	
	public void mouseClicked(MouseEvent event) {
		System.exit(1);
	}
}
