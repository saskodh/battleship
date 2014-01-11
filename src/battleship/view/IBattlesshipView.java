package battleship.view;

import java.awt.Point;

import battleship.core.InfluenceMap;

public interface IBattlesshipView {
	public void showUserMessage(String message);
	public void updateInfluenceMap(InfluenceMap map);
	public void placeBattleship(Point position, boolean isHorizontal);
	public void placeAircraftCarrier(Point position, boolean isHorizontal);
	public void placeDestroyer(Point position, boolean isHorizontal);
	public void placeMinesweeper(Point position, boolean isHorizontal);
	public void placeSubmarine(Point position, boolean isHorizontal);
	public void placeAttackMove(Point position, boolean isHit);
	public void placeHomeMove(Point position, boolean isHit);
}
