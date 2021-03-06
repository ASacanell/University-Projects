package tp.pr5;

import java.util.ArrayList;

import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;


public class NavigationModule extends Observable<NavigationObserver>{

	private Direction sentido;
	private Place currentPlace;
	private City cityMap;
	
	
	
	/**
	 * Navigation module constructor. It needs the city map and the initial place
	 * @param extCity A city map
	 * @param extInitialPlace An initial place for the robot
	 */
	public NavigationModule(City extCity, Place extInitialPlace)
	{
		this.currentPlace = extInitialPlace;
		this.cityMap = extCity;
		initHeading(Direction.NORTH);
	}
	
	
	/**
	 * Checks if the robot has arrived at a spaceship
	 * @true if an only if the current place is the spaceship
	 */
	public boolean atSpaceship()
	{
		return currentPlace.isSpaceship();
	}

	
	/**
	 * Initializes the current heading according to the parameter
	 * @param heading New direction for the robot
	 */
	public void initHeading(Direction heading)
	{
		this.sentido = heading;
	}
	
	
	/**
	 * Updates the current direction of the robot according to the rotation
	 * @param rotation left or right
	 */
	public void rotate(Rotation rotation)
	{
		this.sentido = this.newDirection(rotation);
		this.notifyHeadingHasChanged(sentido);
	}

	private void notifyHeadingHasChanged(Direction newHeading) {
		for(NavigationObserver reo: observadores){
			reo.headingChanged(newHeading);
		}
		
	}


	/**
	 * The method tries to move the robot following the current direction. If the movement is not possible because there is no street, or there is a street which is closed, then it throws an exception. Otherwise the current place is updated according to the movement
	 * @throws InstructionExecutionException An exception with a message about the encountered problem
	 */
	public void move() throws InstructionExecutionException
	{
		Street streetFound = getHeadingStreet();
			
		if(streetFound != null)		//Si la calle existe
		{
			if(streetFound.isOpen())
			{
				this.currentPlace=streetFound.nextPlace(currentPlace);
				this.notifyArrivesAtPlace(this.getCurrentHeading(), this.getCurrentPlace());
			}else{
				
				throw new InstructionExecutionException("WALL·E says: Arrggg, there is a street but it is closed!");
			}
		}else{
			//Si no existe ninguna calle en la direccion establecida
			throw new InstructionExecutionException("WALL·E says: There is no street in direction " + this.sentido);
		}					
	}
	
		
	/**
	 * Returns the street opposite the robot
	 * @return The street which the robot is facing, or null, if there is not any street in this direction
	 */
	public Street getHeadingStreet(){

		return this.cityMap.lookForStreet(this.currentPlace, this.sentido);
	}


	/**
	 * Getter
	 * @return  the place where the object is located
	 * @uml.property  name="currentPlace"
	 */
	public Place getCurrentPlace(){
		return this.currentPlace;
	}

	
	/**
	 * Returns the robot heading
	 * @return The direction where the robot is facing to.
	 */
	public Direction getCurrentHeading()
	{
		return this.sentido;
	}


	/**
	 * Tries to pick an item characterized by a given identifier from the current place. If the action was completed the item is removed from the current place.
	 * @param id The identifier of the item
	 * @return The item of identifier id if it exists in the place. Otherwise the method returns null
	 */
	public Item pickItemFromCurrentPlace(String id)
	{
		notifyChangePlace();
		return this.currentPlace.pickItem(id);
	}

	
	private void notifyChangePlace() {
		for(NavigationObserver reo: observadores)
			reo.placeHasChanged(this.currentPlace);		
	}


	/**
	 * Drop an item in the current place. It assumes that there is no other item with the same name/id there. Otherwise, the behaviour is undefined.
	 * @param it item - The name of the item to be dropped.
	 */
	public void dropItemAtCurrentPlace(Item it)
	{
		this.currentPlace.dropItem(it);
		notifyChangePlace();
	}


	/**
	 * Checks if there is an item with a given id in this place
	 * @param id  Identifier of the item we are looking for
	 * @return true if and only if an item with this id is in the current place
	 */
	public boolean findItemAtCurrentPlace(String id)
	{
		return this.currentPlace.existItem(id);
	}

	
	/**
	 * Prints the information (description + inventory) of the current place
	 */
	public void scanCurrentPlace()
	{
		notifyScanPlace();
	}

	
	private void notifyScanPlace() {
		
		for (NavigationObserver nao : observadores) {
			 nao.placeScanned(this.currentPlace);
		}
		
	}


	/**
	 * Method that sets the new direction after turning in a given rotation
	 * @param rotacion the rotation that the object is going to turn
	 * @return the new direction
	 */
	private Direction newDirection(Rotation rotacion){

		Direction sentido = Direction.UNKNOWN;

		if(this.sentido.equals(Direction.NORTH))
		{
			if(rotacion.equals(Rotation.RIGHT))
			{
				sentido = Direction.EAST;
			}else
				sentido = Direction.WEST;

		}
		else if(this.sentido.equals(Direction.EAST))
		{
			if(rotacion.equals(Rotation.RIGHT))
			{
				sentido = Direction.SOUTH;
			}else
				sentido = Direction.NORTH;

		}
		else if(this.sentido.equals(Direction.SOUTH))
		{
			if(rotacion.equals(Rotation.RIGHT))
			{
				sentido = Direction.WEST;
			}else
				sentido = Direction.EAST;

		}
		else if(this.sentido.equals(Direction.WEST))
		{
			if(rotacion.equals(Rotation.RIGHT))
			{
				sentido = Direction.NORTH;
			}else
				sentido = Direction.SOUTH;				

		}

		return sentido;
	}
	

	
	public ArrayList<NavigationObserver> getObservadores(){
		return this.observadores;
	}


	public void notifyPlaceHasChanged(Place currentPlace2) {
		for(NavigationObserver reo: observadores){
			reo.placeHasChanged(currentPlace2);
		}
		
	}


	public void notifyArrivesAtPlace(Direction currentHeading,
			Place currentPlace2) {
		for(NavigationObserver reo: observadores){
			reo.robotArrivesAtPlace(currentHeading, currentPlace2);
		}
		
	}
	
	public void notifyInitNavigationModule(PlaceInfo extPlace,Direction heading)
	{
		for(NavigationObserver reo: observadores){
			
			reo.initNavigationModule(extPlace, heading);
		}
	}

}
