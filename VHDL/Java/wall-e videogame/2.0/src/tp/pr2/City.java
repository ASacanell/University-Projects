package tp.pr2;

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

		if (this.cityMap != null) {
			while (!existStreet && (length < this.sizeCity())) // Mientras no se
				// encuentre una
				// calle, o no
				// se recorra
				// todo el mapa
			{
				if (this.cityMap[length].comeOutFrom(currentPlace,
						currentHeading)) // Si la calle existe
				{
					Street streetFound = this.cityMap[length];
					existStreet = true;
					return streetFound;

				}
				length++;
			}
		}
		return null;
	}
}
