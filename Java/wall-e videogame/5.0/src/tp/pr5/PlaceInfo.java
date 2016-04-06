package tp.pr5;

public interface PlaceInfo {
	
	/**
	 * Return the place name
	 * @return The place name
	 */
	String getName();
	
	/**
	 * Return the place description
	 * @return The place description
	 */
	String getDescription();

	/**
	 * Is this place the space ship?
	 * @return true if the place represents a space ship
	 */
	boolean isSpaceship();
	
	void printPlaceState();
	
}
