package tp.pr5.console;

import java.util.ArrayList;

import tp.pr5.Constants;
import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.Place;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

public class Console implements NavigationObserver, RobotEngineObserver, InventoryObserver{

	
	public Console(){		
		//We dont have to initialize anything
	}
	
	/**
	 * Notifies that the container has changed
	 * @param inventory - New inventory
	 */
	public void inventoryChange(java.util.List<Item> inventory){
		//Not in console view
	}
	
	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 * @param collectionDescription - Information about the inventory
	 */
	public void inventoryScanned(String collectionDescription){
		if(collectionDescription == null)
		{
			System.out.println("WALL·E says: My inventory is empty");
		}else{
			System.out.println("WALL·E says: I am carrying the following items");
			System.out.println(collectionDescription);
		}	
	}
	
	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
	 * @param description - Item description
	 */
	public void itemScanned(String description){
		System.out.println(description);
	}
	
	/**
	 * Notifies that an item is empty and it will be removed from the container. An invocation to the inventoryChange method will follow.
	 * @param itemName - Name of the empty item
	 */
	public void itemEmpty(String itemName){
		System.out.println("WALL·E says: What a pity! I have no more "
				+ itemName.toLowerCase() + " in my inventory");
	}
	
	/**
	 * The robot engine informs that the robot has shut down (because it has arrived at the spaceship or it has run out of fuel)
	 * @param win - true if the robot shuts down because it has arrived at the spaceship or false if it has run out of fuel
	 */
	public void engineOff(boolean win){
		if(win){
			this.robotSays("I am at my spaceship. Bye bye");
		}else{
			this.robotSays("I run out of fuel");
		}
	}
	
	/**
	 * The robot engine informs that the communication is over.
	 */
	public void communicationCompleted(){
		this.robotSays("I have communication problems. Bye bye");
		}
	
	/**
	 * The robot engine informs that the help has been requested
	 * @param help - A string with information help
	 */
	public void communicationHelp(String help){
		System.out.println(help);
	}
	
	/**
	 * The robot engine informs that it has raised an error
	 * @param msg - Error message
	 */
	public void raiseError(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * The robot engine informs that the robot wants to say something
	 * @param message - The robot message
	 */
	public void robotSays(String message){
		System.out.println("WALL·E says: " + message);
	}
	
	/**
	 * The robot engine informs that the fuel and/or the amount of recycled material has changed
	 * @param fuel - Current amount of fuel
	 * @param recycledMaterial - Current amount of recycled material
	 */
	public void robotUpdate(int fuel,int recycledMaterial){
		
		System.out.println("      * My power is " + fuel + Constants.LINE_SEPARATOR + "      * My recycled material is " + recycledMaterial);
	}
	
	/**
	 * Notifies that the navigation module has been initialized
	 * @param initialPlace - The place where the robot starts the simulation
	 * @param heading - The initial robot heading
	 */
	public void initNavigationModule(PlaceInfo initialPlace,Direction heading){
		System.out.println(initialPlace.getName());
		System.out.println(initialPlace.getDescription());
		System.out.println("WALL·E is looking at direction " + heading);
	}
	
	/**
	 * Notifies that the robot has arrived at a place
	 * @param heading - The robot movement direction
	 * @param place - The place where the robot arrives
	 */
	public void robotArrivesAtPlace(Direction heading,PlaceInfo place){
		
		System.out.println("WALL·E says: Moving in direction " + heading);
		System.out.println(place.getName());
		System.out.println(place.getDescription());
	}
	
	/**
	 * Notifies that the user requested a RADAR instruction
	 * @param placeDescription - Information with the current place
	 */
	public void placeScanned(PlaceInfo placeDescription){
		placeDescription.printPlaceState();
	}
	
	/**
	 * public void placeHasChanged(PlaceInfo placeDescription)
	 * @param placeDescription
	 */
	public void placeHasChanged(PlaceInfo placeDescription){

	}
	
	/**
	 * Notifies that the robot heading has changed
	 * @param newHeading - New robot heading
	 */
	public void headingChanged(Direction newHeading){
		System.out.println("WALL·E is looking at direction " + newHeading);
	}

	public void startEngine(Place currentPlace, int deposito,int materialReciclado,Direction newHeading) {
		
		this.initNavigationModule(currentPlace, newHeading);
		this.robotUpdate(deposito, materialReciclado);
		
	}
	
	public void showPrompt(){
		System.out.print("WALL·E> ");
	}

	@Override
	public void inventoryChange(ArrayList<Item> inventory) {
		// TODO Auto-generated method stub
		
	}
	
}
