package tp.pr4.gui;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

import tp.pr4.Constants;
import tp.pr4.Direction;
import tp.pr4.NavigationModule;
import tp.pr4.Place;
import tp.pr4.RobotEngine;

public class NavigationPanel extends JPanel{

	private PlaceCell[][] cells;
	private JTextArea text;
	private int row;
	private int col;
	private JPanel panel;
	private JLabel imageLabel;
	
	
	public NavigationPanel(){
		super();
		this.row = Constants.NUM_CELLS / 2;
		this.col = Constants.NUM_CELLS / 2;
		this.cells = new PlaceCell[Constants.NUM_CELLS][Constants.NUM_CELLS];
		this.panel = new JPanel(); 
		this.setLayout(new BorderLayout());
		panel.setLayout(new GridLayout(Constants.NUM_CELLS, Constants.NUM_CELLS));
		this.panel.setBorder(BorderFactory.createTitledBorder("City Map"));
		this.add(panel);
				
		
		//---------------Image------------------------------------------
		
		this.imageLabel = new JLabel();
		this.add(this.imageLabel, BorderLayout.WEST);
		
		updateIcon(Direction.NORTH);

		//--------------------------------------------------------------
		
		
		//------------Room description----------------------------------
		this.text = new JTextArea(4, 0);
		JScrollPane scroll = new JScrollPane(this.text);
		this.text.setEditable(false);
		scroll.setBorder(BorderFactory.createTitledBorder("Log"));
		this.add(scroll, BorderLayout.SOUTH);
		//--------------------------------------------------------------
				
		for(int i = 0; i < Constants.NUM_CELLS; i++){
			for(int j = 0; j < Constants.NUM_CELLS; j++){
				PlaceCell cell = new PlaceCell();
				this.cells[i][j] = cell;
				cell.setOwner(this);
				this.panel.add(this.cells[i][j]);
			}
		}
		
		
	}	
	public void updateIcon(Direction direction){
		
		ImageIcon icon = null;
		
		if(direction.equals(Direction.NORTH))
			{
			icon = new ImageIcon(MainWindow.class.getResource("images/walleNorth.png"));
			this.imageLabel.setIcon(icon);
			}
		if(direction.equals(Direction.WEST))
			{
			icon = new ImageIcon(MainWindow.class.getResource("images/walleWest.png"));
			this.imageLabel.setIcon(icon);
			}
		if (direction.equals(Direction.SOUTH))
			{
			icon = new ImageIcon(MainWindow.class.getResource("images/walleSouth.png"));
			this.imageLabel.setIcon(icon);
			}
		if(direction.equals(Direction.EAST))
			{
			icon = new ImageIcon(MainWindow.class.getResource("images/walleEast.png"));
			this.imageLabel.setIcon(icon);
			}
		
		
		
	}
	
	public void setText(String description) {
		this.text.setText(description);
	}	
	public PlaceCell[][] getCells() {
		return cells;
	}
	public void setCells(PlaceCell[][] cells) {
		this.cells = cells;
	}
	public JTextArea getText() {
		return text;
	}
	public void setText(JTextArea text) {
		this.text = text;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
	public void examinedPlace(Place place) {
		
		this.text.setText(place.getDescription());
		
	}
	
	public void updatePlaceInventory(Place place) {
			
			this.text.setText(place.getDescription());
	}
	
	public void changePlace(Direction direction, Place nextPlace) {
		
		this.cells[this.row][this.col].setBackground(Color.GRAY);
		
		
		
		if(direction.name().equals("NORTH")){
			this.row += -1;										
		}else if(direction.name().equals("SOUTH")){
			this.row += 1;
		}else if(direction.name().equals("EAST")){
			this.col += 1;
		}else if(direction.name().equals("WEST")){
			this.col += -1;
		}
		if(nextPlace.isSpaceship())
			{this.cells[this.row][this.col].setBackground(Color.RED);}
		else
		{this.cells[this.row][this.col].setBackground(Color.GREEN);}
		this.cells[this.row][this.col].setText(nextPlace.getName());
		this.cells[this.row][this.col].setPlace(nextPlace);
		this.text.setText(nextPlace.getDescription());
	}
}
