package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

public abstract class Item implements Comparable<Item>{

	/**
	 * @uml.property  name="id"
	 */
	private String id;
	/**
	 * @uml.property  name="description"
	 */
	private String description;

	/**
	 * Builds an item from a given identifier and description
	 * @param extId - Item identifier
	 * @param extDescription - Item description
	 */
	public Item( String extId, String extDescription){
		this.id=extId;
		this.description=extDescription;
	};

	/**
	 * if the item can be used. Subclasses must override this method
	 * @return true if the item can be used
	 */
	public abstract boolean canBeUsed();

	/**
	 * Abstract use Method 
	 * @param r The robot that uses the item
	 * @param p The Place where the item is used
	 * @return true if the action was completed. Otherwise, it returns false
	 */
	public abstract boolean use(RobotEngine r, NavigationModule nav);

	/**
	 * Return the item identifier
	 * @return  The item identifier
	 * @uml.property  name="id"
	 */
	public String getId(){

		return this.id;
	};

	/**
	 * Generates a String with the Item description
	 */
	public String toString(){

		return "WALL·E says: " + this.id + ": " + this.description;
	};
	
	/**
	 * 
	 */
    public int compareTo(Item other) {
        return this.id.compareToIgnoreCase(other.id);
    }

	/**
	 * @return
	 * @uml.property  name="description"
	 */
	public String getDescription() {
		return this.description;
	}
}
