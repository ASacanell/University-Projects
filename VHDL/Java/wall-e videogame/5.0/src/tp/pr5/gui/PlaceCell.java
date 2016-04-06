package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr5.PlaceInfo;

public class PlaceCell extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlaceInfo place;
	private NavigationPanel owner;
	
	
	public PlaceCell(){
		
		super();
		this.place = null; // se inicializa el nombre del botón cuando se crea en go.execute
						  //y la habitacion se añade con un setter
		this.owner = null;
		
		this.setLayout(new BorderLayout());
		
		//-------------Action listeners--------------------------------
		
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PlaceCell.this.place != null){
					PlaceCell.this.owner.setText(PlaceCell.this.place.getDescription());
				}
			}
		});
		
		//-------------------------------------------------------------		
		
	}

	public String getDescription(){
		return this.place.getDescription();
	}
		
	public void enter(PlaceInfo newPlace){

		this.setBackground(Color.GREEN);
		this.setText(newPlace.getName());
		
	}
	
	public void left(){
		
		this.setBackground(Color.GRAY);
	}
	
	public void spaceShip(){			
			this.setBackground(Color.RED);
	}
	
	/**
	 * @return
	 * @uml.property  name="place"
	 */
	public PlaceInfo getPlace() {
		return place;
	}
	
	/**
	 * @param extPlace
	 * @uml.property  name="place"
	 */
	public void setPlace(PlaceInfo extPlace) {
		this.place = extPlace;
	}
	
	/**
	 * @param owner
	 * @uml.property  name="owner"
	 */
	public void setOwner(NavigationPanel owner) {
		this.owner = owner;
	}
}
