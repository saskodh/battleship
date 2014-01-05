package com.battleship.controllers;

import java.awt.Point;

import com.battleship.model.BattleshipModel;
import com.battleship.model.GameState;
import com.battleships.views.BattleshipView;

public class BattleshipController {
	
	private BattleshipModel model;
	private BattleshipView view;
	
	private int playerShipsPlaced;
	public BattleshipController(BattleshipView view) {
		this.view = view;
		newGame();
	}
	
	public void attackGridTurn(Point p){
		if(GameState.PLAYER_TURN == model.getState()){
			model.acceptPlayerShot(p);
		}
	}
	
	public void homeGridTurn(Point p, boolean isHorizontal){
		if(model.getState() == GameState.DEPLOY){
			boolean flag = false;
			switch(playerShipsPlaced){
				case 0: flag = model.placeBattleship(p, isHorizontal); break;
				case 1: flag = model.placeDestroyer(p, isHorizontal); break;
				case 2: flag = model.placeSubmarine(p, isHorizontal); break;
				case 3: flag = model.placeMinesweeper(p, isHorizontal); break;
				case 4: flag = model.placeAircraft(p, isHorizontal); break;
			}
			
			if(flag)
				playerShipsPlaced++;
		}
	}
	
	public void newGame(){
		model = new BattleshipModel(view);
		playerShipsPlaced = 0;
	}
}
