package tp.pr1;

	public enum Direction 
	{
		EAST, NORTH, SOUTH, UNKNOWN, WEST;
		
		
		/**
		 * Metodo que devuelve la direccion contraria.
		 * @return La direccion contraria
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