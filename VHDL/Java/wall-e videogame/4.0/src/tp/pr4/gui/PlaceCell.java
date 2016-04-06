package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr4.Place;

public class PlaceCell extends JButton{

	private Place place;
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
	
		
	public void enter(Place newPlace){

		this.setBackground(Color.GREEN);
		this.setName(newPlace.getName());
		
	}
	
	public void left(){
		
		this.setBackground(Color.GRAY);
	}
	
	public void spaceShip(){			
			this.setBackground(Color.RED);
	}
	
	public Place getPlace() {
		return place;
	}
	
	public void setPlace(Place extPlace) {
		this.place = extPlace;
	}
	
	public void setOwner(NavigationPanel owner) {
		this.owner = owner;
	}
}
