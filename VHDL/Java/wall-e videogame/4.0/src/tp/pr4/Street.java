package tp.pr4;

import tp.pr4.items.CodeCard;


public class Street {

	private Place source;
	private Direction direction;
	private Place target;
	private boolean isOpenClose;
	private String code;


	/**
	 * Creates an open street and it have not any code to open or close it
	 * @param extSource Source place
	 * @param extDirection Represents how is placed the target place with respect to the source place.
	 * @param extTarget Target place
	 */
	public Street(Place extSource, Direction extDirection, Place extTarget){
		this.source = extSource;
		this.direction = extDirection;
		this.target = extTarget;
		this.code = null;
		this.isOpenClose = true;
	}

	/**
	 * Creates a street that has a code to open and close it
	 * @param extSource Source place
	 * @param extDirection Represents how is placed the target place with respect to the source place.
	 * @param extTarget Target place
	 * @param extIsOpenClose Determines is the street is opened or closed
	 * @param extCode The code that opens and closes the street
	 */
	public Street(Place extSource, Direction extDirection, Place extTarget, boolean extIsOpenClose, String extCode){
		this.source = extSource;
		this.direction = extDirection;
		this.target = extTarget;
		this.isOpenClose = extIsOpenClose;
		this.code = extCode;
	}

	/**
	 * Checks if the street comes out from a place in a given direction. Remember that streets are two-way
	 * @param place The place to check
	 * @param whichDirection  Direction used.
	 * @return Returns true if the street comes out from the input Place.
	 */
	public boolean comeOutFrom(Place place, Direction whichDirection){

		if(place.equals(this.target))
			return(whichDirection==this.direction.opposite());
		else if (place.equals(this.source))
			return(whichDirection.equals(this.direction));
		else
			return false;
	}

	/**
	 * Returns the place of the other side from the place whereAmI. This method does not consider whether the street is open or closed
	 * @param whereAmI The place where I am.
	 * @return It returns the Place at the other side of the street (even if the street is closed). Returns null if whereAmI does not belong to the street.
	 */
	public Place nextPlace(Place whereAmI){

		if(whereAmI.equals(this.source))
			return this.target;
		else if(whereAmI.equals(this.target))
			return this.source;
		else return null;

	}

	/**
	 * Tries to close a street using a code card. Codes must match in order to complete this action
	 * @param card A code card to close the street
	 * @return true if the action has been completed
	 */
	public boolean close(CodeCard card){
		boolean successful = false;
		if(card.getCode().equals(this.code)){
			this.isOpenClose = false;
			successful=true;
		}
		return successful;
	};

	/**
	 * Tries to open a street using a code card. Codes must match in order to complete this action
	 * @param card A code card to open the street
	 * @return true if the action has been completed
	 */
	public boolean open(CodeCard card){
		boolean successful = false;
		if(card.getCode().equals(this.code)){
			this.isOpenClose = true;
			successful=true;
		}
		return successful;
	};

	/**
	 * Checks if the street is open or closed
	 * @return true, if the street is open, and false when the street is closed
	 */
	public boolean isOpen(){
		return this.isOpenClose;
	};

	/**
	 * Getter
	 * @return a String with the code
	 */
	public String getCode(){
		return this.code;
	}

}

