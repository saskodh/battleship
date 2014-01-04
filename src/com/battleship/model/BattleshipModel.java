package com.battleship.model;

import java.awt.Graphics;
import java.awt.Point;

import com.battleships.views.BattleshipView;

import Battleships.Agent;
import Battleships.Grid;
import Battleships.InfluenceMap;

public class BattleshipModel {
	private static final int WidthOfGrid = 10;
	private static final int HeightOfGrid = 10;
	
	private Grid playerHomeGrid;
	private Grid playerAttackGrid;
	private Grid agentHomeGrid;
	private Grid agentAttackGrid;
	private InfluenceMap influenceMap;
	
	private Agent agent;
	
	private BattleshipView view;
	
	private GameState state;
	
	public GameState getState(){
		return state;
	}
	
	public BattleshipModel(BattleshipView view){
		playerHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		playerAttackGrid = new Grid(WidthOfGrid, HeightOfGrid);
		agentHomeGrid = new Grid(WidthOfGrid, HeightOfGrid);
		agentAttackGrid = new Grid(WidthOfGrid, HeightOfGrid);
		influenceMap = new InfluenceMap();
		
		agent = new Agent();
		
		state = GameState.DEPLOY;
		
		this.view = view;
	}
	
	public boolean placeAircraft(Point position, boolean isHorizontal) {		
		
		//TODO: remove this hack
		int horizontal = 1;
		if(isHorizontal)
			horizontal = 0;
		
		boolean valid = false;
		String out ="";
		if(!playerHomeGrid.isAirPlaced()) {
			valid = playerHomeGrid.addAir(position.x, position.y, horizontal);
	
			if(valid) {	
				//TODO: replace this with a call to the view.placeAir(position.x, position.y, isHorizontal)
				view.placeAircraftCarrier(position, isHorizontal);
				view.showUserMessage("Air Placed");

				playerHomeGrid.setAirPlaced(true);
				
				//if all are placed the game can begin
				checkGameCanBegin();
			}
			else {
				//TODO: call view.userMessage(msg);
				view.showUserMessage("Aircraft Carrier Will Not Fit Here");
//				out ="not valid";
//				out = out + data.gameState.playerHomeGrid.toString();
			}	
		}
		return valid;
	}	
	
	public boolean placeBattleship(Point position, boolean isHorizontal)
	{
		//TODO: remove this hack
		int horizontal = 1;
		if(isHorizontal)
			horizontal = 0;
		
		boolean valid = false;
		
		String out ="";
		if(!playerHomeGrid.checkBattlePlaced())
		{
			valid = playerHomeGrid.addBattle(position.x, position.y, horizontal);
			if(valid)
			{
				view.placeBattleship(position, isHorizontal);
				playerHomeGrid.setBattlePlacedTrue();
				view.showUserMessage("Battleship Placed");
				
				checkGameCanBegin();
			}
			else
			{
//				out ="not valid";
//				out = out + data.gameState.playerHomeGrid.toString();
				view.showUserMessage("Battleships Will Not Fit Here");
			}	
		}
		return valid;
	}	
	
	public boolean placeDestroyer(Point position, boolean isHorizontal)
	{
		//TODO: remove this hack
		int horizontal = 1;
		if(isHorizontal)
			horizontal = 0;
		
		boolean valid = false;
		String out ="";
		if(!playerHomeGrid.checkDestPlaced()) {
			valid = playerHomeGrid.addDest(position.x, position.y, horizontal);
	
			if(valid) {	
				//TODO: replace this with a call to the view.placeAir(position.x, position.y, isHorizontal)
				view.placeDestroyer(position, isHorizontal);
				view.showUserMessage("Destroyer Placed");

				playerHomeGrid.setDestPlacedTrue();
				
				//if all are placed the game can begin
				checkGameCanBegin();
			}
			else {
				//TODO: call view.userMessage(msg);
				view.showUserMessage("Destroyer Will Not Fit Here");
//						out ="not valid";
//						out = out + data.gameState.playerHomeGrid.toString();
			}	
		}
		return valid;
	}
	
	public boolean placeSubmarine(Point position, boolean isHorizontal)
	{
		//TODO: remove this hack
		int horizontal = 1;
		if(isHorizontal)
			horizontal = 0;

		boolean valid = false;
		if(!playerHomeGrid.checkSubPlaced()) {
			valid = playerHomeGrid.addSub(position.x, position.y, horizontal);
	
			if(valid) {	
				//TODO: replace this with a call to the view.placeAir(position.x, position.y, isHorizontal)
				view.placeSubmarine(position, isHorizontal);
				view.showUserMessage("Submarine Placed");

				playerHomeGrid.setSubPlacedTrue();
				
				//if all are placed the game can begin
				checkGameCanBegin();
			}
			else {
				//TODO: call view.userMessage(msg);
				view.showUserMessage("Submarine Carrier Will Not Fit Here");
//						out ="not valid";
//						out = out + data.gameState.playerHomeGrid.toString();
			}	
		}
		return valid;
	}		
	
	public boolean placeMinesweeper(Point position, boolean isHorizontal)
	{
		//TODO: remove this hack
		int horizontal = 1;
		if(isHorizontal)
			horizontal = 0;
		
		boolean valid = false;
		if(!playerHomeGrid.checkMinePlaced()) {
			valid = playerHomeGrid.addMine(position.x, position.y, horizontal);
	
			if(valid) {	
				//TODO: replace this with a call to the view.placeAir(position.x, position.y, isHorizontal)
				view.placeMinesweeper(position, isHorizontal);
				view.showUserMessage("Minesweeper Placed");

				playerHomeGrid.setMinePlacedTrue();				
				
				//if all are placed the game can begin
				checkGameCanBegin();
			}
			else {
				//TODO: call view.userMessage(msg);
				view.showUserMessage("Minesweeper Will Not Fit Here");
//						out ="not valid";
//						out = out + data.gameState.playerHomeGrid.toString();
			}	
		}
		
		return valid;
	}	

	
	public void acceptPlayerShot(Point position)
	{
		int sqr = playerAttackGrid.getGridVal(position.x, position.y);
		String out ="";

		//if 0 then it isn't clicked yet
		if (sqr == 0)
		{
			boolean isHit = agentHomeGrid.shot(position.x, position.y);			
	
			if(isHit)	{
				playerAttackGrid.update(position.x, position.y, 9);
				view.showUserMessage("HIT! Have Another Turn!");				
				
				checkPlayerWon();
			} else {
				agentHomeGrid.update(position.x, position.y, 1);
				playerAttackGrid.set(position.x, position.y, 1);
				view.showUserMessage("Miss. Agent's Turn");
				//TODO: set Agent turn
				out="Miss!";
				
				state = GameState.AGENT_TURN;
			}
			
			view.placeAttackMove(position, isHit);
		}
	
		//setShipSunkStates();
		
		//TODO: print state
		out = out + "CompHome " +agentHomeGrid.toString();
		out = out + "player Attack = \n" + playerAttackGrid.toString();
		
		System.out.println(state);
		
		if(state == GameState.AGENT_TURN && agent != null){
			autoAgentTurn();
		}
	}
	
	public void acceptAgentShot(Point position){
		int sqrVal = playerHomeGrid.getGridVal(position.x, position.y);
		
		if(sqrVal < 0 || sqrVal==1)
		{
			System.out.println("Shot already taken! Have another go"); 
		}
			
		System.out.println(playerHomeGrid.shot(position.x, position.y));
		if(sqrVal == 0)
		{
			agentAttackGrid.update(position.x, position.y, 1);
			influenceMap.miss(position.x, position.y);
			
			view.placeHomeMove(position, false);
			view.showUserMessage("Agent Has Missed. Player's Turn");
			
			state = GameState.PLAYER_TURN;
			view.showUserMessage("Player turn, take a shot");
		}
		
		if(sqrVal > 1)
		{
			agentAttackGrid.update(position.x, position.y, 8);
			influenceMap.hit(position.x, position.y);
			
			view.placeHomeMove(position, true);
			view.showUserMessage("Agent has hit");
			
			checkAgentWon();
		}
		
		view.updateInfluenceMap(influenceMap);
		
		if(state == GameState.AGENT_TURN && agent != null){
			autoAgentTurn();
		}
		
		System.out.println("compAtt");						
		System.out.println(agentAttackGrid.toString());
		System.out.println(state);
	}

	private void autoAgentTurn(){
		agent.nextShot(influenceMap, agentAttackGrid);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		acceptAgentShot(new Point(agent.getI(), agent.getJ()));
		
		System.out.println("shot at " + agent.getI() + " " +agent.getJ());
		System.out.println(agentAttackGrid.toString());
	}
	
	private void checkGameCanBegin(){
		if( playerHomeGrid.allShipsPlaced() ){
			agentHomeGrid = agent.placeShips();
			
			state = GameState.PLAYER_TURN;
			view.showUserMessage("Player turn, take a shot");
		}
	}
	
	private void checkPlayerWon(){
		//check agent ship states
		boolean playerWon = agentHomeGrid.checkAirSunk() &&
				agentHomeGrid.checkBattleSunk() &&
				agentHomeGrid.checkDestSunk() &&
				agentHomeGrid.checkMineSunk() &&
				agentHomeGrid.checkSubSunk();
		
		if(playerWon){
			view.showUserMessage("Game Over! You Win!");
			state = GameState.GAME_OVER;
		}		
	}
	
	private void checkAgentWon(){
		//check player ship states
		boolean agentWon = playerHomeGrid.checkAirSunk() &&
				playerHomeGrid.checkBattleSunk() &&
				playerHomeGrid.checkDestSunk() &&
				playerHomeGrid.checkMineSunk() &&
				playerHomeGrid.checkSubSunk();
		
		if(agentWon){
			view.showUserMessage("Game Over! Agent Wins!");
			state = GameState.GAME_OVER;
		}	
	}
}
