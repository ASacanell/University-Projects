package tp.pr4;

public enum Direction 
{
	EAST, NORTH, SOUTH, UNKNOWN, WEST;


	/**
	 * Method that return the opposite direction
	 * @return the opposite direction
	 */
	public Direction opposite(){

		switch(this){
		case EAST: 
			return WEST;
		case NORTH: 
			return SOUTH;
		case SOUTH: 
			return NORTH;
		case WEST: 
			return EAST;
		default: 
			return UNKNOWN;
		}
	}
}