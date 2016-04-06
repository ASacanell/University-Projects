package tp.pr4.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

import tp.pr4.Direction;
import tp.pr4.Interpreter;
import tp.pr4.NavigationModule;
import tp.pr4.Place;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.MoveInstruction;
import tp.pr4.instructions.OperateInstruction;
import tp.pr4.instructions.PickInstruction;
import tp.pr4.instructions.QuitInstruction;
import tp.pr4.instructions.TurnInstruction;
import tp.pr4.instructions.DropInstruction;
import tp.pr4.items.ItemContainer;

public class MainWindow {
	
	private JFrame frame;
	private JPanel actions;
	private JComboBox<String> directions;
	private JTextField idItem;
	private RobotPanel robotPanel;
	private NavigationPanel navigationPanel;
	
		
	public MainWindow(final RobotEngine robot) {
		
		
		
		this.frame = new JFrame("WALL·E The garbage collector");
		this.frame.setLayout(new BorderLayout());
		
		this.navigationPanel = new NavigationPanel();
		this.robotPanel = new RobotPanel();
		
		robot.setGUIWindow(this);
		robot.setRobotPanel(this.robotPanel);
		robot.getNavigationModule().setNavigationPanel(navigationPanel);

		
		
		//-------------Menu------------------------
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuQuit = new JMenuItem("Quit");
		menu.add(menuQuit);
		menuBar.add(menu);
		this.frame.setJMenuBar(menuBar);
		menuQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				robot.communicateRobot(new QuitInstruction());
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
				this.frame.setVisible(true);
				this.frame.setSize(1000, 600);
				this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.frame.setLocationRelativeTo(null);
		//----------------------------------------------------------
				
		//-------------Action listeners--------------------------------
				
				bt_move.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						robot.communicateRobot(new MoveInstruction());

					}
				});
				
				bt_quit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						robot.communicateRobot(new QuitInstruction());
						gameQuit();
					}
				});
				
				bt_turn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						if(directions.getSelectedItem().equals("LEFT"))
						robot.communicateRobot(new TurnInstruction(Rotation.LEFT));
						else if(directions.getSelectedItem().equals("RIGHT"))
							robot.communicateRobot(new TurnInstruction(Rotation.RIGHT));
						
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
						robot.communicateRobot(new PickInstruction(selectedItem));
						
					}
				});
								
				bt_drop.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						if(robotPanel.getTabla().getSelectedRow() >= 0)
						{
							int selectedRow = robotPanel.getTabla().getSelectedRow();
							String selectedItem = robotPanel.getTabla().getValueAt(selectedRow, 0).toString();
							robot.communicateRobot(new DropInstruction(selectedItem));
						}
						
					}
				});
				
				bt_operate.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						if(robotPanel.getTabla().getSelectedRow() >= 0)
						{
							int selectedRow = robotPanel.getTabla().getSelectedRow();
		
							String selectedItem = robotPanel.getTabla().getValueAt(selectedRow, 0).toString();
							robot.communicateRobot(new OperateInstruction(selectedItem));
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
	
	public void gameOver(){
		System.exit(0);
	}
		
	public JFrame getFrame()
	{
		return this.frame;
	}
	
	public void gameStart(Place initialPlace, int robotRecycled, int robotEnergy) {
		//------------Set the first room-------------------
		this.navigationPanel.getCells()[this.navigationPanel.getRow()][this.navigationPanel.getCol()].setPlace(initialPlace);
		this.navigationPanel.getCells()[this.navigationPanel.getRow()][this.navigationPanel.getCol()].setText(initialPlace.getName());
		this.navigationPanel.getCells()[this.navigationPanel.getRow()][this.navigationPanel.getCol()].setBackground(Color.GREEN);
		this.navigationPanel.getText().setText(initialPlace.getDescription());
		//-------------------------------------------------
		//this.robotPanel.gameStart(initialPlace, robotRecycled, robotEnergy);
	}

}