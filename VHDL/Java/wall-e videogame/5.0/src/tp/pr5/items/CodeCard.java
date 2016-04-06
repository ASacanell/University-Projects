package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Street;

public class CodeCard extends Item {

	/**
	 * @uml.property  name="code"
	 */
	private String code;

	/**
	 * Constructor
	 * @param extId card name
	 * @param extDescription card description
	 * @param extCode code stored in the code card
	 */
	public CodeCard(String extId, String extDescription, String extCode) {
		super(extId, extDescription);
		this.code = extCode;
	}

	/**
	 * A code card always can be used. Since the robot has the code card it never loses it
	 * @return true because it always can be used
	 */
	public boolean canBeUsed() {
		return true;
	}

	/**
	 * The method to use a code card. If the robot is in a place which contains
	 * a street in the direction he is looking at, then the street is opened or
	 * closed if the street code and the card code match.
	 * 
	 * @param r the robot engine employed to use the card.
	 * @param p the place where the card is going to be used
	 * @return true If the codecard can complete the action of opening or closing a street. Otherwise it returns false.
	 */
	public boolean use(RobotEngine r, NavigationModule nav) {
		Street streetFound = nav.getHeadingStreet();
		boolean successful = false;

		if (streetFound != null) {
			if (streetFound.isOpen()) {
				successful = streetFound.close(this);
			} else if (!streetFound.isOpen()) {
				successful = streetFound.open(this);
			}
		}
		return successful;
	}

	/**
	 * Method that returns an array with the code of the card
	 * @return  the code of the card
	 * @uml.property  name="code"
	 */
	public String getCode() {
		return this.code;
	}

}
