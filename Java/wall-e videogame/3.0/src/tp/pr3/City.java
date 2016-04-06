package tp.pr3;

public class City {

	private Street[] cityMap;

	/**
	 * Constructor
	 */
	public City() {
	}

	/**
	 * Creates a city using an array of streets
	 * @param extCityMap the streets of the map
	 */
	public City(Street[] extCityMap) {
		this.cityMap = extCityMap;
	}

	/**
	 * Method that returns the lenght of the array
	 * @return lenght of the array
	 */
	public int sizeCity() {
		return this.cityMap.length;
	}

	/**
	 * Looks for the street that starts from the given place in the given
	 * direction.
	 * @param currentPlace The place where to look for the street
	 * @param currentHeading The direction to look for the street
	 * @return the street that stars from the given place in the given direction. It returns null if there is not any street in this direction from the given place
	 */
	public Street lookForStreet(Place currentPlace, Direction currentHeading) {
		
		int length = 0;
		boolean existStreet = false;
		Street streetFound = null;

		if (this.cityMap != null) {
			while (!existStreet && (length < this.sizeCity()) && this.cityMap[length] != null)
			{
				if (this.cityMap[length].comeOutFrom(currentPlace, currentHeading)) 
				{
					streetFound = this.cityMap[length];
					existStreet = true;

				}
				length++;
			}
		}
		return streetFound;
			/*boolean found=false;
			Street auxStreet=null;
			Direction auxDir = currentHeading.opposite();
			for (int i = 0; i < this.cityMap.length && !found; i++) {
				if (currentPlace == this.cityMap[i].getSource() && currentHeading == this.cityMap[i].getDirection() || currentPlace==this.CityMap[i].getTarget() && auxDir == this.CityMap[i].getDirection()){
					found=true;
					auxStreet =this.cityMap[i];
				}
			}
			
			return auxStreet;*/
		
	}

	/**
	 * Adds an street to the city
	 * @param street The street to be added
	 */
	public void addStreet(Street street)
	{
		int length = this.sizeCity() + 1;
		this.cityMap[length] = street;
	}

}
