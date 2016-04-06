package tp.pr5.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.RobotEngineObserver;


public class MainWindow implements RobotEngineObserver{
	

	private JFrame frame;
	private JPanel actions;
	private JComboBox<String> directions;
	private JTextField idItem;
	private RobotPanel robotPanel;
	private NavigationPanel navigationPanel;
	private GUIController GUIController;
	private InfoPanel infoPanel;
	
		
	public MainWindow(GUIController GUICont){
		
		
		
		this.frame = new JFrame("WALL·E The garbage collector");
		this.frame.setLayout(new BorderLayout());
		
		this.navigationPanel = new NavigationPanel();
		this.robotPanel = new RobotPanel();
		this.infoPanel = new InfoPanel();
		

		this.GUIController = GUICont;
		
		//-----------------Add observers-----------------------
		//---InfoPanel -> EngineObserver, NavigationObserver and ItemCOntainerObserver---
		this.GUIController.registerEngineObserver(this.infoPanel);
		this.GUIController.registerNavigationObserver(this.infoPanel);
		this.GUIController.registerItemContainerObserver(this.infoPanel);
		//---Robot -> EngineObserver, ItemContainerObserver---
		this.GUIController.registerEngineObserver(this);
		this.GUIController.registerItemContainerObserver(this.robotPanel);
		//---Navigation -> NavigationObserver---
		this.GUIController.registerNavigationObserver(this.navigationPanel);
		//-----------------------------------------------------
		
		
		//-------------Menu------------------------
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuQuit = new JMenuItem("Quit");
		menu.add(menuQuit);
		menuBar.add(menu);
		this.frame.setJMenuBar(menuBar);
		menuQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				GUIController.quitController();
				
				gameQuit();
			}
		});
		
		//-----------------------------------------
		
		
		//-----------------Instruction buttons------------------
		
		String[] dir = {"LEFT", "RIGHT"};
		
		JButton bt_move = new JButton("MOVE");
		JButton bt_turn = new JButton("TURN");
		this.directions = new JComboBox<String>(dir);
		final JButton bt_pick = new JButton("PICK");
		bt_pick.setEnabled(false);
		this.idItem = new JTextField();
		final JButton bt_drop = new JButton("DROP");
		bt_drop.setEnabled(false);
		final JButton bt_operate = new JButton("OPERATE");
		bt_operate.setEnabled(false);
		JButton bt_quit = new JButton("QUIT");
		
		//-----------------------------------------------------
		
		
		//-----------------Create and set---------------------------------------
		
				this.actions = new JPanel();
				this.actions.setBorder(BorderFactory.createTitledBorder("Instructions"));
				this.actions.setLayout(new GridLayout(4, 2, 5, 5));// 4 filas, 2 columnas y 5 pixels de separación horizontal y vertical.
				
				JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				splitPane.setLeftComponent(this.actions);
				splitPane.setRightComponent(this.robotPanel);
				splitPane.setDividerLocation(300);
				this.frame.add(splitPane);
				
		//----------------------------------------------------------------------
				
		//-----Add action buttons-----
				
				this.actions.add(bt_move);
				this.actions.add(bt_quit);
				this.actions.add(bt_turn);
				this.actions.add(this.directions);
				this.actions.add(bt_pick);
				this.actions.add(this.idItem);
				this.actions.add(bt_drop);
				this.actions.add(bt_operate);

		//----------------------------	
				
				
		//------------------Frame-----------------------------------
				this.frame.add(splitPane, BorderLayout.NORTH);
				this.frame.add(this.navigationPanel, BorderLayout.CENTER);
				this.frame.add(this.infoPanel, BorderLayout.SOUTH);
				this.frame.setVisible(true);
				this.frame.setSize(1000, 600);
				this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.frame.setLocationRelativeTo(null);
		//----------------------------------------------------------
				
		//-------------Action listeners--------------------------------
				
				bt_move.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
					GUIController.moveController();
					
					}
				});
				
				bt_quit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						GUIController.quitController();
					}
				});
				
				bt_turn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						if(directions.getSelectedItem().equals("LEFT"))
							
							GUIController.turnLeftController();

						else if(directions.getSelectedItem().equals("RIGHT"))
							
							GUIController.turnRightController();
						
					}
				});
				
				idItem.addKeyListener(new KeyAdapter(){
					public void keyReleased(KeyEvent e){
						if(idItem.getText().equals(""))
						{bt_pick.setEnabled(false);}
						else
						{bt_pick.setEnabled(true);}
						
					}
						
				});
				
				bt_pick.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						String selectedItem = idItem.getText();

						GUIController.pickController(selectedItem);

						
					}
				});
								
				bt_drop.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						if(robotPanel.getTabla().getSelectedRow() >= 0)
						{
							int selectedRow = robotPanel.getTabla().getSelectedRow();
							String selectedItem = robotPanel.getTabla().getValueAt(selectedRow, 0).toString();
							
							GUIController.dropController(selectedItem);
							
						}
						
					}
				});
				
				bt_operate.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						if(robotPanel.getTabla().getSelectedRow() >= 0)
						{
							int selectedRow = robotPanel.getTabla().getSelectedRow();
		
							String selectedItem = robotPanel.getTabla().getValueAt(selectedRow, 0).toString();
							
							GUIController.operatepController(selectedItem);
							
						}
						
					}
				});
				
				robotPanel.getTabla().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				robotPanel.getTabla().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			            bt_drop.setEnabled(!lsm.isSelectionEmpty());
			            bt_operate.setEnabled(!lsm.isSelectionEmpty());
						
					}
				});
			
		//-------------------------------------------------------------
		
				
				
		
		}
	
	public void gameQuit() {
		int answer = JOptionPane.showConfirmDialog(this.frame, "Are you sure ?", "Quit", JOptionPane.YES_NO_OPTION);
		if(answer == 0){
			System.exit(0);
		}
	}
	
	public void startEngine(Place initialPlace, int robotEnergy, int robotRecycled, Direction newHeading) {
		
		int row = this.navigationPanel.getRow();
		int col = this.navigationPanel.getCol();
		
		this.robotUpdate(robotEnergy, robotRecycled);
		
		//------------Set the first room-------------------
		this.navigationPanel.getCells()[row][col].setPlace(initialPlace);
		this.navigationPanel.getCells()[row][col].setText(initialPlace.getName());
		this.navigationPanel.getCells()[row][col].setBackground(Color.GREEN);
		this.navigationPanel.getText().setText(initialPlace.getDescription());
		//-------------------------------------------------

	}

	@Override
	public void raiseError(String msg) {
		JOptionPane.showMessageDialog(this.frame, msg);
		
	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engineOff(boolean atShip) {
		if(atShip){
			JOptionPane.showMessageDialog(this.frame, "YOU WIN!  =)");
			System.exit(0);
		}
		else {
			JOptionPane.showMessageDialog(this.frame, "YOU LOSE  =(");
			System.exit(0);
		}
		
	}

	@Override
	public void communicationCompleted() {
		
		int answer = JOptionPane.showConfirmDialog(this.frame, "Are you sure ?", "Quit", JOptionPane.YES_NO_OPTION);
		if(answer == 0){
			this.engineOff(false);
			System.exit(0);
		}
		
	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		this.robotPanel.updateRobotHealth(fuel);
		this.robotPanel.updateRobotScore(recycledMaterial);
		
	}

}