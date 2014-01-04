package com.battleships.views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


















import com.battleship.controllers.BattleshipController;

import Battleships.Grid;
import Battleships.InfluenceMap;
import Battleships.Graphics.AircraftCarrier;
import Battleships.Graphics.AircraftCarrierH;
import Battleships.Graphics.AttackPanel;
import Battleships.Graphics.Battleship;
import Battleships.Graphics.BattleshipH;
import Battleships.Graphics.Destroyer;
import Battleships.Graphics.DestroyerH;
import Battleships.Graphics.HitIcon;
import Battleships.Graphics.HomePanel;
import Battleships.Graphics.InfluenceMapGraphic;
import Battleships.Graphics.InfluencePanel;
import Battleships.Graphics.Minesweeper;
import Battleships.Graphics.MinesweeperH;
import Battleships.Graphics.MissIcon;
import Battleships.Graphics.Submarine;
import Battleships.Graphics.SubmarineH;


public class BattleshipView extends JFrame implements IBattlesshipView{
	
	private boolean isHorizontal;
	private boolean showInfluenceMap;
	private InfluenceMap influenceMap;
	
	private AttackPanel attackGridPanel;
	private HomePanel homeGridPanel;
	private InfluencePanel influenceMapPanel;
	private JTextField txtUserMessage;
	
	private BattleshipController controller;

	public BattleshipView(){
		super("Battleships");
		initView();
		
		isHorizontal = true;
		showInfluenceMap = true;		
	}
	
	public void setController(BattleshipController control){
		this.controller = control;
	}
	
	@Override
	public void showUserMessage(String message) {
		txtUserMessage.setText(message);
	}

	@Override
	public void updatePlayerAttackGrid(Grid grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayerHomeGrid(Grid grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInfluenceMap(InfluenceMap map) {
		influenceMap = map;
		paintInfluenceMap();
	}
	
	public void showInfluenceMap(){
		showInfluenceMap = true;
		
		paintInfluenceMap();
		
		txtUserMessage.setText("Influence Map shown");
	}
	
	private void paintInfluenceMap(){
		if(influenceMap == null && !showInfluenceMap)
			return;		
		
		Graphics g = influenceMapPanel.getGraphics();	
		
		for (int i = 0; i < 10; i++) //change these to ROWS to use the default
		{
			for (int j = 0; j < 10; j++)//change this to CoLumns for default
			{
				int col = influenceMap.getVal(i,j);
				
				if(showInfluenceMap) {
					InfluenceMapGraphic.paint(g,(j*20),(i*20), col);
				}
			}
		}
	}
	
	private void initView(){	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout(2,1));
		this.setResizable(false);		

		//attack panel
		Container APanel = new Container();
		APanel.setLayout(new GridLayout());
		
		//home panel
		Container HPanel = new Container();
		HPanel.setLayout(new GridLayout());
	
		//influence map
		Container IMPanel = new Container();
		IMPanel.setLayout(new GridLayout());
		
		//center panel where grids are
		Container CenterPanel = new Container();
		CenterPanel.setLayout(new GridLayout(1,3));
		//create grids and maps
		
		//attack panel add listener
		attackGridPanel = new AttackPanel();
		attackGridPanel.addMouseListener(new AttackMousePressListener(this));
		
		
		homeGridPanel = new HomePanel();
		homeGridPanel.addMouseListener(new HomeMousePressListener(this));
		
		influenceMapPanel = new InfluencePanel();
		
		APanel.add(attackGridPanel);
		CenterPanel.add(APanel);
		
		HPanel.add(homeGridPanel);
		CenterPanel.add(HPanel);
		
		IMPanel.add(influenceMapPanel);
		CenterPanel.add(IMPanel);
		
	
		/*
		Creates the southPanel. This panel holds the ship status
		and rotate button
		*/
		Container southPanel = new Container();
		southPanel.setLayout(new GridLayout(1,2));
		southPanel.setSize(400,400);
		/*
		Creates the shipPanel which contains the status of ships
		*/
		
		Container shipPanel = new Container();
		shipPanel.setLayout(new GridLayout(4,2));
		//adds the Ship Panel to the south panel
		southPanel.add(shipPanel);
		
		/*
			Creates the topShipPanel. This is where the
			placeholders for the top row of ships are
		*/
		
		Container topShipPanel = new Container();
		topShipPanel.setLayout(new FlowLayout());
		//add topShipPanel to shipPanel
		shipPanel.add(topShipPanel);
		
		/*
			Creates the topShipLabelPanel. This is where the
			labels for the top row of ships are
		*/
		
		Container topShipLabelPanel = new Container();
		topShipLabelPanel.setLayout(new FlowLayout());
		//add topShipPanel to shipPanel
		shipPanel.add(topShipLabelPanel);	
		
		/*
			Creates the bottomShipPanel. This is where the
			placeholders for the top row of ships are
		*/
		
		Container bottomShipPanel = new Container();
		bottomShipPanel.setLayout(new FlowLayout());
		//add bottomShipPanel to shipPanel
		shipPanel.add(bottomShipPanel);
		
		/*
			Creates the bottomShipLabelPanel. This is where the
			labels for the bottom row of ships are
		*/
		
		Container bottomShipLabelPanel = new Container();
		bottomShipLabelPanel.setLayout(new FlowLayout());
		//add bottomShipPanel to shipPanel
		shipPanel.add(bottomShipLabelPanel);
				
				
		//add mineweeper label to the topShipLabelPanel
	//	JLabel minesweeperLabel = new JLabel("Minesweeper");
		//topShipLabelPanel.add(minesweeperLabel);
		
		//add submarine label to the topShipLabelPanel
	//	JLabel submarineLabel = new JLabel("Submarine");
		//topShipLabelPanel.add(submarineLabel);
		
		//add destroyer label to the topShipLabelPanel
		//JLabel destroyerLabel = new JLabel("Destroyer");
		//topShipLabelPanel.add(destroyerLabel);
		
		//add Battleship label to the bottomShipLabelPanel
	//	JLabel battlershipLabel = new JLabel("Battleship");
	//	bottomShipLabelPanel.add(battlershipLabel);
		
		//add Aircraft Carrier label to the bottomShipLabelPanel
	//	JLabel aircraftCarrierLabel = new JLabel("Aircraft Carrier");
	//	bottomShipLabelPanel.add(aircraftCarrierLabel);
		
		JButton NewButton = new JButton("New Game");
		topShipPanel.add(NewButton);
	//	NewButton.addMouseListener(new NewButtonAction(this));
		
		JButton hideButton = new JButton("Hide Influence Map");
		topShipPanel.add(hideButton);
		hideButton.addMouseListener(new HideButtonAction(this));
		
		JButton destButton = new JButton("Rotate");
		topShipPanel.add(destButton);
		
		JButton rotateButton = new JButton("Rotate Ship");
		rotateButton.addMouseListener(new RotateButtonAction(this));
		bottomShipPanel.add(rotateButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addMouseListener(new QuitButtonAction());
		bottomShipPanel.add(quitButton);
		
		/*
			Creates the container to go in the right hand side of the southPanel.
			This is where the rotate button will be.
		*/
		
		Container rotatePanel = new Container();
		rotatePanel.setLayout(new BorderLayout());
		//add rotatePanel to southPanel
		southPanel.add(rotatePanel);
		
		
		
		JButton viewMap = new JButton("View Influence Map");
		viewMap.addMouseListener(new ShowButtonAction(this));
		rotatePanel.add(viewMap, BorderLayout.NORTH);
		
		
		txtUserMessage = new JTextField("lookat me!");
		txtUserMessage.setText("Welcome To Battleships. Place ships on the middle grid");
		txtUserMessage.setEditable(false);
		rotatePanel.add(txtUserMessage);
		
		contentPane.add(CenterPanel,BorderLayout.CENTER);
		contentPane.add(southPanel,BorderLayout.SOUTH);
		
		this.pack();
		this.setSize(640,400);
		this.setVisible(true);
	}

	public void toggleShipOrientation(){
		isHorizontal = !isHorizontal;
	}

	
	public void hideInfluenceMap()	{
		showInfluenceMap = false;
		
		Graphics g = influenceMapPanel.getGraphics();	
		
		for (int i = 0; i < 10; i++) //change these to ROWS to use the default
		{
			for (int j = 0; j < 10; j++)//change this to CoLumns for default
			{
				int col = 0;
				InfluenceMapGraphic.paint(g,(j*20),(i*20), col);
			}
		}
		
		txtUserMessage.setText("Influence Map Hidden");
	}	
	
	public void placeBattleship(Point position, boolean isHorizontal){
		Graphics hp = homeGridPanel.getGraphics();
		if(isHorizontal){
			BattleshipH.paint(hp,(position.y*20),(position.x*20));			
		}else {
			Battleship.paint(hp,(position.y*20),(position.x*20));
		}
	}
	
	public void placeAircraftCarrier(Point position, boolean isHorizontal){
		Graphics hp = homeGridPanel.getGraphics();
		if(isHorizontal){
			AircraftCarrierH.paint(hp,(position.y*20),(position.x*20));			
		}else {
			AircraftCarrier.paint(hp,(position.y*20),(position.x*20));
		}
	}
	
	public void placeDestroyer(Point position, boolean isHorizontal){
		Graphics hp = homeGridPanel.getGraphics();
		if(isHorizontal){
			DestroyerH.paint(hp,(position.y*20),(position.x*20));			
		}else {
			Destroyer.paint(hp,(position.y*20),(position.x*20));
		}
	}
	
	public void placeMinesweeper(Point position, boolean isHorizontal){
		Graphics hp = homeGridPanel.getGraphics();
		if(isHorizontal){
			MinesweeperH.paint(hp,(position.y*20),(position.x*20));			
		}else {
			Minesweeper.paint(hp,(position.y*20),(position.x*20));
		}
	}
	
	public void placeSubmarine(Point position, boolean isHorizontal){
		Graphics hp = homeGridPanel.getGraphics();
		if(isHorizontal){
			SubmarineH.paint(hp,(position.y*20),(position.x*20));			
		}else {
			Submarine.paint(hp,(position.y*20),(position.x*20));
		}
	}

	public void placeAttackMove(Point position, boolean isHit){
		Graphics ap = attackGridPanel.getGraphics();	
		if(isHit){
			HitIcon.paint(ap, position.y*20, position.x*20);			
		}else {
			MissIcon.paint(ap, position.y*20, position.x*20);
		}
	}
	
	public void placeHomeMove(Point position, boolean isHit){
		Graphics hp = homeGridPanel.getGraphics();	
		if(isHit){
			HitIcon.paint(hp, position.y*20, position.x*20);			
		}else {
			MissIcon.paint(hp, position.y*20, position.x*20);
		}
	}

	//methods for action listeners
	public void attackGridClick(Point p){
		
		int gridJ= resolveAxisCoOrdinate(p.x);
		int gridI= resolveAxisCoOrdinate(p.y);
		
        controller.attackGridTurn(new Point(gridI, gridJ));
	}
	
	public void homeGridClick(Point p){	
		int gridJ= resolveAxisCoOrdinate(p.x);
		int gridI= resolveAxisCoOrdinate(p.y);
		
		controller.homeGridTurn(new Point(gridI, gridJ), isHorizontal);
		
		System.out.println("Element corresponds to " + gridI + gridJ);
		//repaint();
	}
	
	public void newGame(){
		initView();
		controller.newGame();
	}
	
	private int resolveAxisCoOrdinate(int x) {
		if (x < 20)
			return 0;
		else if (x <40)
			return 1;
		else if (x <60)
			return 2;
		else if (x <80)
			return 3;
		else if (x <100)
			return 4;
		else if (x <120)
			return 5;
		else if (x <140)
			return 6;
		else if (x <160)
			return 7;
		else if (x <180)
			return 8;
		else if (x <200)
			return 9;
		return -1;
	}			
}

class AttackMousePressListener extends MouseAdapter {
	
	private BattleshipView view;
	
	public AttackMousePressListener(BattleshipView gui2)	{
		view=gui2;
	}	  
	
	public void mousePressed(MouseEvent event)	{		
		view.attackGridClick(new Point(event.getX(), event.getY()));		
	}			
}

class HomeMousePressListener extends MouseAdapter {
	
	private BattleshipView view;
	
	public HomeMousePressListener(BattleshipView gui2)	{
		view=gui2;
	}	
	
	public void mousePressed(MouseEvent event)	{
		
		view.homeGridClick(new Point(event.getX(), event.getY()));
	}
}
	
class RotateButtonAction extends MouseAdapter {
	
	private BattleshipView gui;
	
	public RotateButtonAction(BattleshipView gui2) {		
		gui=gui2;
	}	
	
	public void mousePressed(MouseEvent event) {
		//System.out.println("Horiz = " +gui.rotate());
		gui.toggleShipOrientation();
	}

}

class HideButtonAction extends MouseAdapter {
	
	private BattleshipView gui;
	
	public HideButtonAction(BattleshipView gui2) {
		
		gui=gui2;
	}	
	
	public void mousePressed(MouseEvent event) {
		gui.hideInfluenceMap();
	}
}

class ShowButtonAction extends MouseAdapter {
	
	private BattleshipView gui;
	
	public ShowButtonAction(BattleshipView gui2) {
		
		gui=gui2;
	}	
	
	public void mousePressed(MouseEvent event) {
		gui.showInfluenceMap();
	}
}

class NewButtonAction extends MouseAdapter {
	
	private BattleshipView gui;
	
	public NewButtonAction(BattleshipView gui2) {		
		gui=gui2;
	}	
	
	public void mousePressed(MouseEvent event) {
		gui.newGame();
		
	}
}

class QuitButtonAction extends MouseAdapter {
	
	public QuitButtonAction() {}	
	
	public void mouseClicked(MouseEvent event) {
		System.exit(1);
	}
}
