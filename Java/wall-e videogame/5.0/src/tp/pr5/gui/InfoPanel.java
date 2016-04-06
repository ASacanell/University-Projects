package tp.pr5.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

public class InfoPanel extends javax.swing.JPanel implements RobotEngineObserver, NavigationObserver, InventoryObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	
	public InfoPanel(){
		this.label = new JLabel();
		this.add(this.label, BorderLayout.CENTER);
	}
	/**
	 * Notifies that the robot heading has changed
	 * @param newHeading - New robot heading
	 */
	public void headingChanged(Direction newHeading){
		//this.label.setText("Robot now looking at " + newHeading);
	}
	
	/**
	 * Notifies that the navigation module has been initialized
	 * @param initialPlace - The place where the robot starts the simulation
	 * @param heading - The initial robot heading
	 */
	public void initNavigationModule(PlaceInfo initialPlace,Direction heading){
		//Not in GUI view
	}
	
	/**
	 * The robot engine informs that it has raised an error
	 * @param msg - Error message
	 */
	public void raiseError(java.lang.String msg){
		//Not in GUI view
	}
	
	/**
	 * The robot engine informs that the help has been requested
	 * @param help - A string with information help
	 */
	public void communicationHelp(java.lang.String help){
		//Not in GUI view
	}
	
	/**
	 * The robot engine informs that the robot has shut down (because it has arrived at the spaceship or it has run out of fuel)
	 * @param atShip - true if the robot shuts down because it has arrived at the spaceship or false if it has run out of fuel
	 */
	public void engineOff(boolean atShip){
		//Not in GUI view
	}
	
	/**
	 * The robot engine informs that the communication is over.
	 */
	public void communicationCompleted(){
		//Not in GUI view
	}
	
	/**
	 * Notifies that the robot has arrived at a place
	 * @param heading - The robot movement direction
	 * @param place - The place where the robot arrives
	 */
	public void robotArrivesAtPlace(Direction heading,PlaceInfo place){
		this.label.setText("Robot is now at " + place);
	}
	
	/**
	 * The robot engine informs that the fuel and/or the amount of recycled material has changed
	 * @param fuel - Current amount of fuel
	 * @param recycledMaterial - Current amount of recycled material
	 */
	public void robotUpdate(int fuel,int recycledMaterial){
		this.label.setText("Robot attributes has been updated: (" + fuel + ", " + recycledMaterial + ")");
	}
	
	/**
	 * Notifies that the user requested a RADAR instruction
	 * @param placeDescription - Information with the current place
	 */
	public void placeScanned(PlaceInfo placeDescription){
		//Not in GUI view
	}
		
	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 * @param collectionDescription - Information about the inventory
	 */
	public void inventoryScanned(java.lang.String collectionDescription){
		//Not in GUI view
	}
	
	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
	 * @param description - Item description
	 */
	public void itemScanned(java.lang.String description){
		//Not in GUI view
	}
	
	/**
	 * Notifies that an item is empty and it will be removed from the container. An invocation to the inventoryChange method will follow.
	 * @param itemName - Name of the empty item
	 */
	public void itemEmpty(java.lang.String itemName){
		//Not in GUI view
	}
	
	/**
	 * The robot engine informs that the robot wants to say something
	 * @param message - The robot message
	 */
	public void robotSays(java.lang.String message){
		this.label.setText("WALL·E says: " + message);
	}
	
	/**
	 * Notifies that the place where the robot stays has changed (because the robot picked or dropped an item)
	 * @param placeDescription
	 */
	public void placeHasChanged(PlaceInfo placeDescription){
		//this.label.setText("The place inventory has changed");
	}

	@Override
	public void inventoryChange(ArrayList<Item> inventory) {
		//this.label.setText("The robot inventory has changed");
		
	}
}
