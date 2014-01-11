package battleship.gamestart;

import battleship.controller.BattleshipController;
import battleship.view.BattleshipView;

public class BattleshipGameStart {

	public static void main(String[] args) {
		BattleshipView view = new BattleshipView();
		BattleshipController controller = new BattleshipController(view);
		view.setController(controller);
	}

}
