package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

public class Fuel extends Item {

	/**
	 * @uml.property  name="power"
	 */
	private int power;
	/**
	 * @uml.property  name="times"
	 */
	private int times;

	/**
	 * Constructor
	 * 
	 * @param extId
	 *            - Item id
	 * @param extDescription
	 *            - Item description
	 * @param extPower
	 *            - The amount of power energy that this item provides the robot
	 * @param extTimes
	 *            - Number of times the robot can use the item
	 */
	public Fuel(String extId, String extDescription, int extPower, int extTimes) {
		super(extId, extDescription);
		this.power = extPower;
		this.times = extTimes;
	}

	/**
	 * Fuel can be used as many times as it was configured
	 * 
	 * @return true it the item still can be used
	 */
	public boolean canBeUsed() {
		return this.times>0;
	}

	/**
	 * Using the fuel provides energy to the robot (if it can be used)
	 * 
	 * @param r
	 *            - robot that is going to use the fuel
	 * @param p
	 *            - place where the fuel is going to be used
	 * @return true if the fuel has been used
	 */
	public boolean use(RobotEngine r, NavigationModule nav) {
		
		boolean moreUses=this.canBeUsed();
		
		if (moreUses) {
			r.addFuel(this.power);
			this.times--;
		} 
			return moreUses;
	}

	/**
	 * Generates a String with the Item description
	 * @return a String with the information
	 */
	public String toString() {

		return super.toString() + "// power = " + this.power + ", times = "
				+ this.times;
	}

}
