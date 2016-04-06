package tp.pr5.gui;

import java.awt.*;


import javax.swing.*;

import tp.pr5.Constants;
import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;


public class NavigationPanel extends JPanel implements NavigationObserver{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlaceCell[][] cells;
	private JTextArea text;
	private int row;
	private int col;
	private JPanel panel;
	private JLabel imageLabel;
	private PlaceCell currentPlace;
	
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
	
	private void updateIcon(Direction direction){
		
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
	/**
	 * @return
	 * @uml.property  name="cells"
	 */
	public PlaceCell[][] getCells() {
		return cells;
	}
	/**
	 * @param cells
	 * @uml.property  name="cells"
	 */
	public void setCells(PlaceCell[][] cells) {
		this.cells = cells;
	}
	/**
	 * @return
	 * @uml.property  name="text"
	 */
	public JTextArea getText() {
		return text;
	}
	/**
	 * @param text
	 * @uml.property  name="text"
	 */
	public void setText(JTextArea text) {
		this.text = text;
	}
	/**
	 * @return
	 * @uml.property  name="row"
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row
	 * @uml.property  name="row"
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return
	 * @uml.property  name="col"
	 */
	public int getCol() {
		return col;
	}
	/**
	 * @param col
	 * @uml.property  name="col"
	 */
	public void setCol(int col) {
		this.col = col;
	}
		
	public void setInitialPlace(PlaceInfo initialPlace){

		currentPlace = this.cells[this.row][this.col];
		currentPlace.setPlace(initialPlace);
		currentPlace.enter(initialPlace);
		setText(initialPlace.getDescription());
	}
		
	private void changePlace(Direction direction, PlaceInfo nextPlace) {
		
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
		{
		this.cells[this.row][this.col].setBackground(Color.GREEN);}
		this.cells[this.row][this.col].setText(nextPlace.getName());
		this.cells[this.row][this.col].setPlace(nextPlace);
		this.text.setText(nextPlace.getDescription());
	}
	
	
	@Override
	public void headingChanged(Direction newHeading) {
		// TODO Auto-generated method stub
		updateIcon(newHeading);
		
	}
	@Override
	public void initNavigationModule(PlaceInfo extPlace, Direction heading) {
		setInitialPlace(extPlace);
		
	}
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO Auto-generated method stub
		changePlace(heading, place);
		this.text.setText(place.getDescription());
		
	}
	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void placeHasChanged(PlaceInfo place) {
		
		this.text.setText(place.getDescription());
	}
}
