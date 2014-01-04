package Battleships;

import javax.sound.midi.ControllerEventListener;

import com.battleship.controllers.BattleshipController;
import com.battleships.views.BattleshipView;

public class TestClass {

	public static void main(String[] args) {
		BattleshipView view = new BattleshipView();
		BattleshipController controller = new BattleshipController(view);
		view.setController(controller);
	}

}
