package tp.pr4;
import java.awt.Component;
import java.util.Scanner;

import javax.swing.JOptionPane;

import tp.pr4.gui.MainWindow;
import tp.pr4.gui.NavigationPanel;
import tp.pr4.gui.RobotPanel;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

public class RobotEngine {

	private NavigationModule navMod;
	private MainWindow mainWindow;
	private boolean end;
	private int Deposito;
	private int MaterialReciclado; 
	private ItemContainer inventario;
	private RobotPanel robotPanel;
	private NavigationPanel navPanel;
	

	//Constructor
	public RobotEngine(City extCityMap, Place extInitialPlace, Direction extDirection) {
		this.Deposito = Constants.INITIAL_FUEL; 
		this.MaterialReciclado = Constants.INITIAL_RECYCLED;
		this.inventario = new ItemContainer();
		this.navMod = new NavigationModule(extCityMap, extInitialPlace);
		this.end = false;
	}

	/**
	 * Method that generates a loop that waits for the user to enter the commands
	 */
	public void startGraphicGame(){
		
		this.inventario.setRobotPanel(robotPanel);
		this.navMod.getNavigationPanel().updatePlaceInventory(this.navMod.getCurrentPlace());
		this.mainWindow.gameStart(this.navMod.getCurrentPlace(), Constants.INITIAL_RECYCLED, Constants.INITIAL_FUEL);
		
	}
	
	public void startEngine(){
		this.end = false;
		//--------------------------------------
		
		
		
		//--------------------------------------
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
			System.out.println(exc.getMessage());
			if(this.mainWindow != null)
			JOptionPane.showMessageDialog(mainWindow.getFrame(), exc.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
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
	
	public void updateRobotState(){
		this.robotPanel.updateRobotFuel(this.Deposito);
		this.robotPanel.updateRobotRecycled(this.MaterialReciclado);
		this.robotPanel.inventoryUpdate(this.inventario);
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
	 * Check if the robot died
	 */
	public void robotDied(){
		if(this.Deposito<=0){
			JOptionPane.showMessageDialog(this.mainWindow.getFrame(), "Not enough fuel");
			this.mainWindow.gameOver();
			this.end = true;
		}
	}
	
	/**
	 * Prints the information about all possible instructions
	 */
	public void requestHelp(){
		System.out.println("The valid instructions for WALL·E are: \n");
		System.out.println(Interpreter.interpreterHelp());
	}
	
	public void setGUIWindow(MainWindow extMainWindow){
		this.mainWindow = extMainWindow;
	}
	
	public void setRobotPanel(RobotPanel extRobotPanel){
		this.robotPanel = extRobotPanel;
	}
	
	public NavigationModule getNavigationModule(){
		
		return this.navMod;
	}
	
	/**
	 * Updates the inventory
	 */
	public void updateInventory(){
		this.robotPanel.inventoryUpdate(inventario);
	}

	/**
	 * Show the MessageDialog with the spaceship message
	 */
	public void gameOver() {
		JOptionPane.showMessageDialog(this.mainWindow.getFrame(), "You are at the spaceship");
		this.mainWindow.gameOver();
		
	}	
}