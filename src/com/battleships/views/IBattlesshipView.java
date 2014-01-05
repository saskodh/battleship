package com.battleships.views;

import Battleships.InfluenceMap;

public interface IBattlesshipView {
	public void showUserMessage(String message);
	//public void updatePlayerAttackGrid(Grid grid);
	//public void updatePlayerHomeGrid(Grid grid);
	public void updateInfluenceMap(InfluenceMap map);
}
