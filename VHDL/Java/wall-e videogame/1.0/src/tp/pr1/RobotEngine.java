package tp.pr1;
import java.util.Scanner;

public class RobotEngine {
	
	private Direction sentido;
	private Place currentPlace;
	private Interpreter inter;
	private Street[] cityMap;
	
		/**
		 * Constructor
		 * @param extInitialPlace Lugar en el que se inicia el robot
		 * @param extDirection Direccion con la que se encuentra el robot
		 * @param extCityMap Array con los lugares y las calles inicializado
		 */
	public RobotEngine(Place extInitialPlace, Direction extDirection, Street[] extCityMap) {
		this.sentido = extDirection;
		this.currentPlace = extInitialPlace;
		this.inter = new Interpreter();
		this.cityMap = extCityMap;
	}
		
		/**
		 * Metodo al que llega la instruccion, y procede a su ejecucion
		 * @param instruction Instruccion para ejecutar
		 * @return Booleano indicando si es un fin de juego (SpaceShip o Quit)
		 */
	private boolean processInstruction(Instruction instruction){
		
	boolean endGame = false;
	
	Action instruccion = instruction.getAction();
	Rotation rotacion = instruction.getRotation();

	if(instruction.isValid())
	{
		if(instruccion.equals(Action.QUIT))	//Si la accion es Quit
		{
			System.out.println("WALL·E says: I have communication problems. Bye bye");
			endGame = true;		//Finaliza el juego
		}
		else if(instruccion.equals(Action.HELP))	//Si la accion es Help
		{
			System.out.println(this.inter.interpreterHelp());
		}
		else if(instruccion.equals(Action.TURN))	//Si la accion es Turn
		{
			this.sentido = nuevaDireccion(rotacion);
			System.out.println("WALL·E is looking at direction " + this.sentido);
		}
		else if(instruccion.equals(Action.MOVE))	//Si la accion es Move
		{
			changePlace(this.sentido);
			if(this.currentPlace.isSpaceship())		//Si es SpaceShip finaliza el juego
			{
				System.out.println("WALL·E says: I am at my spaceship. Shutting down... Bye bye");
				endGame = true;
			}
		}
	}
	else System.out.println("WALL·E says: I do not understand. Please repeat");
	
	return endGame;
}

		/**
		 * Comprueba que el lugar inicial no sea nulo
		 * @param initialPlace Lugar de inicio del juego
		 * @return Booleano indicando si el lugar inicial no es nulo
		 */
	public boolean initGame(Place initialPlace){
		
		if(initialPlace == null)
    		return false;
		else{
			this.currentPlace = initialPlace;
			return true;
		}
	}
	
		/**
		 * Metodo que genera un bucle que espera a que el usuario introduzca los comandos
		 */
	public void startEngine(){
		boolean endGame = false;
    	Scanner scan = new Scanner(System.in);
    	System.out.println(this.currentPlace.toString());
    	System.out.println("WALL·E is looking at direction " + this.sentido);
    	while (!endGame){
    		
        	System.out.print("\nWALL·E > ");
			String comando =  scan.nextLine();
			Instruction auxInstruction = inter.generateInstruction(comando);
			endGame = processInstruction(auxInstruction);
    	}
    	scan.close();
	}
	
		/**
		 * Getter
		 * @return Lugar donde se encuentra el objeto
		 */
    public Place getCurrentPlace(){
    	return this.currentPlace;
    }

		/**
		 * Metodo que cambia al objeto a otro lugar del mapa, comprobando antes si existe la calle correspondiente.
		 */
    private void changePlace(Direction sentido){
    	
    	boolean existStreet = false;
    	int longitud = 0;
    	
    	while(!existStreet && longitud<this.cityMap.length)		//Mientras no se encuentre una calle, o no se recorra todo el mapa
    	{
    		if(this.cityMap[longitud].comeOutFrom(this.currentPlace, this.sentido))		//Si la calle existe
    		{
    			this.currentPlace = this.cityMap[longitud].nextPlace(this.currentPlace);	//Cambia el nuevo lugar actual
    			System.out.println("WALL·E says: Moving in direction " + this.sentido);
    			System.out.println(this.currentPlace.toString());
    			System.out.println("WALL·E is looking at direction " + this.sentido);
    			existStreet = true;		//Finaliza el bucle
    		}
    	longitud++;
    	}
    	if(!existStreet)	//Si no existe ninguna calle en la direccion establecida
    		System.out.println("WALL·E says: There is no street in direction " + this.sentido);
    	
    }
    
    	/**
    	 * Metodo que establece la direccion nueva despues de aplicar una rotacion
    	 * @param rotacion Rotacion de la instruccion
    	 * @return La nueva Nueva direccion una vez aplicada la direccion
    	 */
    private Direction nuevaDireccion(Rotation rotacion){
    	
    	Direction sentido = Direction.UNKNOWN;
    	
		if(this.sentido.equals(Direction.NORTH))
		{
			if(rotacion.equals(Rotation.RIGHT))
			{
				sentido = Direction.EAST;
			}else
				sentido = Direction.WEST;
				
		}
		else if(this.sentido.equals(Direction.EAST))
		{
			if(rotacion.equals(Rotation.RIGHT))
			{
				sentido = Direction.SOUTH;
			}else
				sentido = Direction.NORTH;

		}
		else if(this.sentido.equals(Direction.SOUTH))
		{
			if(rotacion.equals(Rotation.RIGHT))
			{
				sentido = Direction.WEST;
			}else
				sentido = Direction.EAST;

		}
		else if(this.sentido.equals(Direction.WEST))
		{
			if(rotacion.equals(Rotation.RIGHT))
			{
				sentido = Direction.NORTH;
			}else
				sentido = Direction.SOUTH;				

		}
		
		return sentido;
    }
   
}
