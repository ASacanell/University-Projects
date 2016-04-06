package tp.pr2;

public class Fuel extends Item {

	private int power;
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
		if (this.times <= 0) {
			return false;
		} else {
			return true;
		}
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
	public boolean use(RobotEngine r, Place p) {
		if (this.canBeUsed()) {
			r.addFuel(this.power);
			this.times--;
			r.walleInf();
			if (this.times == 0) {
				r.elimItem(this);
				System.out.print("WALL·E says: What a pity! I have no more "
						+ this.getId().toLowerCase() + " in my inventory");
			}
			return true;
		} else {
			return false;
		}
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
