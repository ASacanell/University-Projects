package tp.pr1;

public class Street {

	private Place source;
    private Direction direction;
    private Place target;
	
    
    	/**
    	 * Constructor
    	 * @param extSource Un extremo de la calle
    	 * @param extDirection Direccion desde el extremo 'Source' de la calle
    	 * @param extTarget El otro extremo de la calle
    	 */
    public Street(Place extSource, Direction extDirection, Place extTarget){
    	this.source = extSource;
    	this.direction = extDirection;
    	this.target = extTarget;
    }
    
    	/**
    	 * Comprueba si existe una calle desde un lugar con una direccion
    	 * @param place Lugar donde se encuentra el robot
    	 * @param whichDirection Direccion con la que se encuentra el robot
    	 * @return Booleano indicando si existe la calle
    	 */
    public boolean comeOutFrom(Place place, Direction whichDirection){
		
    	if(place.equals(this.target))
    		return(whichDirection==this.direction.opposite());
    	else if (place.equals(this.source))
    		return(whichDirection.equals(this.direction));
    	else
    		return false;
    }
 
    	/**
    	 * Metodo que devuelve el lugar existente en el otro extremo de una calle
    	 * @param whereAmI Lugar en el que se encuentra el robot
    	 * @return Devuelve el Place existente al otro extremo de la calle
    	 */
    public Place nextPlace(Place whereAmI){
    	
    	if(whereAmI.equals(this.source))
    		return this.target;
    	else if(whereAmI.equals(this.target))
    		return this.source;
    	else return null;
 
    }
}
