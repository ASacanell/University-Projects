package tp.pr2;
import java.util.Scanner;

public class RobotEngine {

	private Direction sentido;
	private Place currentPlace;
	private City cityMap;
	private int Deposito;
	private int MaterialReciclado; 
	private ItemContainer inventario;
	static final int MATERIAL_RECICLADO = 0; //Constante
	static final int DEPOSITO_COMBUSTIBLE = 50; //Constante
	

	//Constructor
	public RobotEngine(City extCityMap, Place extInitialPlace, Direction extDirection) {
		this.sentido = extDirection;
		this.currentPlace = extInitialPlace;
		this.cityMap = extCityMap;
		this.Deposito = DEPOSITO_COMBUSTIBLE; 
		this.MaterialReciclado = MATERIAL_RECICLADO;
		this.inventario = new ItemContainer();
	}

	/**
	 * Method that arrives the instruction and proceeds to execution
	 */
	private boolean processInstruction(Instruction instruction){

		boolean endGame =false;

		Action instruccion = instruction.getAction();
		Rotation rotacion = instruction.getRotation();
		String item = instruction.getId();

		if(instruction.isValid())
		{
			switch(instruccion){
			case QUIT:{
				endGame = instructionQuit();
				break;}
			case HELP:{
				instructionHelp();
				break;}
			case MOVE:{
				endGame = instructionMove();
				break;}
			case PICK:{
				instructionPick(item);
				break;}
			case OPERATE:{
				endGame = instructionOperate(item);
				break;}
			case TURN:{
				instructionTurn(rotacion);
				break;}
			case SCAN:{
				instructionScan(item);
				break;}
			}
		}else 
			System.out.println("WALL·E says: I do not understand. Please repeat");

		return endGame;
	}

	/**
	 * Method that starts the game if the inicial place is not null
	 * @param initialPlace
	 * @return true if the game started correctly
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
	 * Method that generates a loop that waits for the user to enter the commands
	 */
	public void startEngine(){
		boolean endGame = false;
		Scanner scan = new Scanner(System.in);
		placeInf();
		//System.out.println("\n");
		walleInf();
		System.out.print("WALL·E is looking at direction " + this.sentido);
		while (!endGame){
			System.out.print("\nWALL·E > ");
			String comando =  scan.nextLine();
			Instruction auxInstruction = Interpreter.generateInstruction(comando);
			endGame = processInstruction(auxInstruction);
		}
		scan.close();
	}
	
	/**
	 * Getter
	 * @return the place where the object is located
	 */
	public Place getCurrentPlace(){
		return this.currentPlace;
	}

	/**
	 * Method that changes the place where the object is located
	 * @param sentido the direction that the object is looking at
	 */
	private void changePlace(Direction sentido){

		Street streetFound = null;

		streetFound = this.cityMap.lookForStreet(this.currentPlace, this.sentido);
		if(streetFound != null)		//Si la calle existe
		{
			//Codigo
			if(streetFound.isOpen())
			{
				this.currentPlace = streetFound.nextPlace(this.currentPlace);	//Cambia el nuevo lugar actual
				this.Deposito=this.Deposito-5;
				System.out.println("WALL·E says: Moving in direction " + this.sentido);
				placeInf();
				//System.out.println("\n");
				walleInf();
				System.out.println("WALL·E is looking at direction " + this.sentido);
			}else{
				System.out.print("WALL·E says: Arrggg, there is a street but it is closed!");
			}
		}else{
			//Si no existe ninguna calle en la direccion establecida
			System.out.println("WALL·E says: There is no street in direction " + this.sentido);
		}



	}

	/**
	 * Method that sets the new direction after turning in a given rotation
	 * @param rotacion the rotation that the object is going to turn
	 * @return the new direction
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
	};

	
	/**
	 * Method that adds fuel to the robot's tank
	 * @param fuel the fuel that is going to be added
	 */
	public void addFuel(int fuel){
		this.Deposito = this.Deposito + fuel;
		if(this.Deposito<0)
			this.Deposito=0;
	};

	
	/**
	 * Method that adds recycled material to the robot's tank
	 * @param weight the recycled material that is going to be added
	 */
	public void addRecycledMaterial(int weight){
		this.MaterialReciclado = this.MaterialReciclado + weight;
	}


	/**
	 * Returns the street opposite the robot
	 * @return The street which the robot is facing, or null, if there is not any street in this direction
	 */
	public Street getHeadingStreet(){

		return cityMap.lookForStreet(this.currentPlace, this.sentido);
	}

	/**
	 * Method that shows Walle's information
	 */
	public void walleInf(){
		System.out.println("   * My power is " + this.Deposito);
		System.out.println("   * My recycled material is: " + this.MaterialReciclado);
	}
	
	/**
	 * Method that shows the Place information
	 */
	public void placeInf(){
		System.out.println(this.currentPlace.toString());
		if(this.currentPlace.getlistOfItems().numberOfItems()==0)
		{
			System.out.println("The place is empty. There are no objects to pick");
		}	
		else
		{
			System.out.println("The place contains these objects: ");
			System.out.println(this.currentPlace.getlistOfItems());
		}

	}

	/**
	 * Method that removes an item from the inventory
	 * @param it The item which is gonna be removed
	 */
	public void elimItem(Item it){
		this.inventario.removeItem(it);
	}
	
	/**
	 * Getter
	 * @return the fuel that Walle has
	 */
	public int getFuel(){
		return this.Deposito;
	};

	/**
	 * Getter
	 * @return the recycled material that Walle has
	 */
	public int getRecycledMaterial(){
		return this.MaterialReciclado;
	};
	
	/**
	 * Method that processes the instruction QUIT
	 * @return boolean for indicating the end of the game
	 */
	private boolean instructionQuit(){
		System.out.println("WALL·E says: I have communication problems. Bye bye");
		return true;		//Finaliza el juego
	}
	
	/**
	 * Method that processes the instruction HELP
	 */
	private void instructionHelp(){
		System.out.println(Interpreter.interpreterHelp());
	}
	
	/**
	 * Method that processes the instruction TURN
	 * @param extRotacion the rotation that the robot is gonna turn
	 */
	private void instructionTurn(Rotation extRotacion){
		this.sentido = nuevaDireccion(extRotacion);
		this.Deposito=this.Deposito-1;
		System.out.print("WALL·E is looking at direction " + this.sentido);
	}
	
	/**
	 * Method that processes the instruction MOVE
	 * @return boolean for indicating the end of the game
	 */
	private boolean instructionMove(){
		changePlace(this.sentido);
		if(this.currentPlace.isSpaceship())		//Si es SpaceShip finaliza el juego
		{
			System.out.println("WALL·E says: I am at my space ship. Bye Bye");
			return true;
		}else
			return false;
	}
	
	/**
	 * Method that processes the instruction PICK
	 * @param extItem the item that is gonna be picked
	 */
	private void instructionPick(String extItem){
		if(this.currentPlace.getlistOfItems().getItem(extItem)!=null)
		{
			if(this.inventario.addItem(this.currentPlace.pickItem(extItem))){
				System.out.print("WALL·E says: I am happy! Now I have  " + extItem);
			}else{
				System.out.print("WALL·E says: I am stupid! I had already the object " + extItem);
			}
			
		}
		else
		{
			System.out.print("WALL·E says: Ooops, this place has not the object " + extItem.toLowerCase());
		}
	}
	
	/**
	 * Method that processes the instruction OPERATE
	 * @param extItem the item that is gonna be operated
	 * @return boolean for indicating the end of the game
	 */
	private boolean instructionOperate(String extItem){
		Item it=this.inventario.getItem(extItem);
		
		if(it!=null){
			it.use(this, this.currentPlace);
			if(this.Deposito==0){
				System.out.println("\nWALL·E says: I run out of fuel. I cannot move. Shutting down...");
				return true;}
			else
				return false;//Finaliza el juego
		}else{
			System.out.println("WALL·E says:I have not such object");
			return false;
		}
	}
	
	/**
	 * Method that processes the instruction SCAN
	 * @param extItem the item that is gonna be scanned or null (if the inventory is gonna be scanned)
	 */
	private void instructionScan(String extItem){
		if(extItem==null)
		{
			if(this.inventario.numberOfItems()==0)
				System.out.print("WALL·E says: My inventory is empty");
			else
			{
				System.out.println("WALL·E says: I am carrying the following items");
				System.out.print(this.inventario.toString());
			}
		}else{
			Item it = this.inventario.getItem(extItem);
			System.out.print(it.toString());
		}
	}
}
