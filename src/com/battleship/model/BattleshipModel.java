package com.battleship.model;

import java.awt.Point;

import com.battleships.views.BattleshipView;

import Battleships.Agent;
import Battleships.InfluenceMap;

public class BattleshipModel {
	private static final int WidthOfGrid = 10;
	private static final int HeightOfGrid = 10;
	
	private HomeGrid playerHomeGrid;
	private HomeGrid agentHomeGrid;
	private AttackGrid playerAttackGrid;
	private AttackGrid agentAttackGrid;
	private InfluenceMap influenceMap;
	
	private Agent agent;
	
	private BattleshipView view;
	
	private GameState state;
	
	public GameState getState(){
		return state;
	}
	
	public BattleshipModel(BattleshipView view){
		playerHomeGrid = new HomeGrid(WidthOfGrid, HeightOfGrid);
		playerAttackGrid = new AttackGrid(WidthOfGrid, HeightOfGrid);
		agentHomeGrid = new HomeGrid(WidthOfGrid, HeightOfGrid);
		agentAttackGrid = new AttackGrid(WidthOfGrid, HeightOfGrid);
		influenceMap = new InfluenceMap();
		
		agent = new Agent();
		
		state = GameState.DEPLOY;
		
		this.view = view;
	}
	
	public boolean placeAircraft(Point position, boolean isHorizontal) {		
		boolean valid = playerHomeGrid.addAircraftCarrier(position.x, position.y, isHorizontal);

		if(valid) {	
			view.placeAircraftCarrier(position, isHorizontal);
			view.showUserMessage("Aircraft Carrier Placed");
			
			//if all are placed the game can begin
			checkGameCanBegin();
		}else {
			view.showUserMessage("Aircraft Carrier Will Not Fit Here");
		}	
		
		return valid;
	}	
	
	public boolean placeBattleship(Point position, boolean isHorizontal) {
		boolean valid = playerHomeGrid.addBattleship(position.x, position.y, isHorizontal);

		if(valid) {	
			view.placeBattleship(position, isHorizontal);
			view.showUserMessage("Battleship Placed");
			
			//if all are placed the game can begin
			checkGameCanBegin();
		}else {
			view.showUserMessage("Battleship Will Not Fit Here");
		}	
		
		return valid;
	}	
	
	public boolean placeDestroyer(Point position, boolean isHorizontal)	{
		boolean valid = playerHomeGrid.addDestroyer(position.x, position.y, isHorizontal);

		if(valid) {	
			view.placeDestroyer(position, isHorizontal);
			view.showUserMessage("Destroyer Placed");
			
			//if all are placed the game can begin
			checkGameCanBegin();
		}else {
			view.showUserMessage("Destroyer Will Not Fit Here");
		}	
		
		return valid;
	}
	
	public boolean placeSubmarine(Point position, boolean isHorizontal)	{
		boolean valid = playerHomeGrid.addSubmarine(position.x, position.y, isHorizontal);

		if(valid) {	
			view.placeSubmarine(position, isHorizontal);
			view.showUserMessage("Submarine Placed");
			
			//if all are placed the game can begin
			checkGameCanBegin();
		}else {
			view.showUserMessage("Submarine Will Not Fit Here");
		}	
		
		return valid;
	}		
	
	public boolean placeMinesweeper(Point position, boolean isHorizontal) {
		boolean valid = playerHomeGrid.addMinesweeper(position.x, position.y, isHorizontal);

		if(valid) {	
			view.placeMinesweeper(position, isHorizontal);
			view.showUserMessage("Minesweeper Placed");
			
			//if all are placed the game can begin
			checkGameCanBegin();
		}else {
			view.showUserMessage("Minesweeper Will Not Fit Here");
		}	
		
		return valid;
	}	

	
	public void acceptPlayerShot(Point pos) {
		
		if(playerAttackGrid.isEmpty(pos.x, pos.y)){
		
			boolean isHit = agentHomeGrid.shot(pos.x, pos.y);
			if(isHit){
				playerAttackGrid.addHit(pos.x, pos.y);
				view.showUserMessage("HIT! Have Another Turn!");				
				
				checkPlayerWon();
			}else {
				playerAttackGrid.addMiss(pos.x, pos.y);
				view.showUserMessage("Miss. Agent's Turn");
				
				state = GameState.AGENT_TURN;
			}
	
			view.placeAttackMove(pos, isHit);
		}
		
		if(state == GameState.AGENT_TURN && agent != null){
			autoAgentTurn();
		}
	}
	
	public void acceptAgentShot(Point pos){
		
		if(agentAttackGrid.isEmpty(pos.x, pos.y)){			
		
			boolean isHit = playerHomeGrid.shot(pos.x, pos.y);
			if(isHit){
				agentAttackGrid.addHit(pos.x, pos.y);			
				influenceMap.hit(pos.x, pos.y);
				
				view.showUserMessage("Agent Has Hit One Of your ships! Agent's Turn again");
				
				checkAgentWon();
			}else {
				agentAttackGrid.addMiss(pos.x, pos.y);
				influenceMap.miss(pos.x, pos.y);
	
				view.showUserMessage("Agent Has Missed. Player's Turn");
				
				state = GameState.PLAYER_TURN;
				//view.showUserMessage("Player turn, take a shot");
			}
	
			view.placeHomeMove(pos, isHit);
			view.updateInfluenceMap(influenceMap);
		}
		
		if(state == GameState.AGENT_TURN && agent != null){
			autoAgentTurn();
		}
	}

	private void autoAgentTurn(){		
		//TODO: Bug: acceptAgentShot call is put on the stack while the thread is sleeping
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Point p = agent.nextShot(influenceMap, agentAttackGrid);
		acceptAgentShot(p);
		
		System.out.println("shot at " + p.x + " " + p.y);
		//System.out.println(agentAttackGrid.toString());
	}
	
	private void checkGameCanBegin(){
		if( playerHomeGrid.allShipsPlaced() ){
			agentHomeGrid = agent.placeShips();
			
			state = GameState.PLAYER_TURN;
			view.showUserMessage("Player turn, take a shot");
		}
	}
	
	private void checkPlayerWon(){
		boolean playerWon = agentHomeGrid.allShipsSunked();
		
		if(playerWon){
			view.showUserMessage("Game Over! You Win!");
			state = GameState.GAME_OVER;
		}		
	}
	
	private void checkAgentWon(){
		boolean agentWon = playerHomeGrid.allShipsSunked();
		
		if(agentWon){
			view.showUserMessage("Game Over! Agent Wins!");
			state = GameState.GAME_OVER;
		}	
	}
}
