package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;

public class Garbage extends Item {

	private int recycledMaterial;
	private int uses;

	/**
	 * Garbage constructor
	 * @param extId Item id
	 * @param extDescription Item description
	 * @param extRecycledMaterial The amount of recycled material that the item generates
	 */
	public Garbage(String extId, String extDescription, int extRecycledMaterial) {
		super(extId, extDescription);
		this.recycledMaterial = extRecycledMaterial;
		this.uses = 1;
	};

	/**
	 * Garbage can be used only once
	 * @return true if the item has not been used yet
	 */
	public boolean canBeUsed() {
		if (uses >= 1) {
			uses--;
			return true;
		} else
			return false;
	};

	/**
	 * The garbage generates recycled material for the robot that uses it
	 * @param r the robot that uses the item
	 * @param p The place where the item is used
	 * @return true if the garbage was transformed in recycled material
	 */
	public boolean use(RobotEngine r, NavigationModule nav) {
		if (this.canBeUsed()) {
			r.addRecycledMaterial(this.recycledMaterial);
			r.printRobotState();
			r.elimItem(this);
			System.out.println("WALL·E says: What a pity! I have no more "
					+ this.getId().toLowerCase() + " in my inventory");
			return true;
		} else {
			return false;
		}

	};

	/**
	 * Generates a String with the Item description
	 * @return a String with the information
	 */
	public String toString() {
		return super.toString() + "// recycled material = "
				+ this.recycledMaterial;
	};
}
