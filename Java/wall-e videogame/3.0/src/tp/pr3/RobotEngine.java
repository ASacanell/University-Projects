package tp.pr3;
import java.util.Scanner;

import tp.pr3.instructions.Instruction;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class RobotEngine {

	private NavigationModule navMod;
	private boolean end;
	private int Deposito;
	private int MaterialReciclado; 
	private ItemContainer inventario;
	static final int MATERIAL_RECICLADO = 0; //Constante
	static final int DEPOSITO_COMBUSTIBLE = 100; //Constante
	

	//Constructor
	public RobotEngine(City extCityMap, Place extInitialPlace, Direction extDirection) {
		this.Deposito = DEPOSITO_COMBUSTIBLE; 
		this.MaterialReciclado = MATERIAL_RECICLADO;
		this.inventario = new ItemContainer();
		this.navMod = new NavigationModule(extCityMap, extInitialPlace);
		this.end = false;
	}

	/**
	 * Method that generates a loop that waits for the user to enter the commands
	 */
	public void startEngine(){
		this.end = false;
		Scanner scan = new Scanner(System.in);
		this.navMod.getCurrentPlace().printPlaceState();
		System.out.println("WALL·E is looking at direction " + this.navMod.getCurrentHeading());
		this.printRobotState();
		while (!this.end){
			System.out.print("WALL·E> ");
			String comando =  scan.nextLine();
			try {
				Instruction auxInstruction = Interpreter.generateInstruction(comando);
				this.communicateRobot(auxInstruction);
			} catch (WrongInstructionFormatException e) {
				System.out.println("WALL·E says: I don't understand you. Please Repeat.");
			}
			
		}
		scan.close();
	}
	
	/**
	 * It executes an instruction. The instruction must be configured with the context before executing it. It controls the end of the simulation. If the execution of the instruction throws an exception, then the corresponding message is printed
	 * @param auxInstruction The instruction to be executed
	 */
	public void communicateRobot(Instruction auxInstruction) {
		try {
			auxInstruction.configureContext(this, this.navMod, this.inventario);
			auxInstruction.execute();
		} catch (InstructionExecutionException exc) {
			exc.getMessage();
		}
	}

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
	 * Method that shows Walle's information
	 */
	public void printRobotState(){
		System.out.println("      * My power is " + this.Deposito);
		System.out.println("      * My reclycled material is " + this.MaterialReciclado);
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
	}

	/**
	 * Getter
	 * @return the recycled material that Walle has
	 */
	public int getRecycledMaterial(){
		return this.MaterialReciclado;
	}
	
	/**
	 * Requests the end of the simulation
	 */
	public void requestQuit(){
		this.end = true;
	}
	
	/**
	 * Prints the information about all possible instructions
	 */
	public void requestHelp(){
		System.out.println("The valid instructions for WALL·E are: \n");
		System.out.println(Interpreter.interpreterHelp());
	}
	
}