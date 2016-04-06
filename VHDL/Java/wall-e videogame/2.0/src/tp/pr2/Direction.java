package tp.pr2;

public enum Direction 
{
	EAST, NORTH, SOUTH, UNKNOWN, WEST;


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