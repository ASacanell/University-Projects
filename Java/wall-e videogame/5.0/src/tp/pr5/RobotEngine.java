package tp.pr5;
import java.util.ArrayList;

import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;

import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

public class RobotEngine extends Observable<RobotEngineObserver>{


	private NavigationModule navMod;
	private boolean end;
	private int Deposito;
	private int MaterialReciclado; 
	private ItemContainer inventario;
	

	//Constructor
	public RobotEngine(City extCityMap, Place extInitialPlace, Direction extDirection) {
		this.Deposito = Constants.INITIAL_FUEL; 
		this.MaterialReciclado = Constants.INITIAL_MATERIAL;
		this.inventario = new ItemContainer();
		this.navMod = new NavigationModule(extCityMap, extInitialPlace);
		this.end = false;
	}
	
	/**
	 * It executes an instruction. The instruction must be configured with the context before executing it. It controls the end of the simulation. If the execution of the instruction throws an exception, then the corresponding message is printed
	 * @param auxInstruction The instruction to be executed
	 * @throws InstructionExecutionException 
	 */
	public void communicateRobot(Instruction auxInstruction){
		
		auxInstruction.configureContext(this, this.navMod, this.inventario);
		try{
			auxInstruction.execute();
		}catch(InstructionExecutionException e)
		{
			this.requestError(e.getMessage());
		}
		
		if(this.robotDied())
		{
			for(int i = 0; i < this.observadores.size();i++){
				this.observadores.get(i).engineOff(false);
			}
			setOver(true);
		}else if(this.navMod.getCurrentPlace().isSpaceship())
		{
			for(int i = 0; i < this.observadores.size();i++){
				this.observadores.get(i).engineOff(true);
			}
			setOver(true);
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
		
		this.notifyRobotUpdate();
	};

	/**
	 * Method that adds recycled material to the robot's tank
	 * @param weight the recycled material that is going to be added
	 */
	public void addRecycledMaterial(int weight){
		this.MaterialReciclado = this.MaterialReciclado + weight;
		this.notifyRobotUpdate();
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
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).communicationCompleted();
		}
	}
	
	public void requestStart(){
		this.navMod.notifyInitNavigationModule(this.navMod.getCurrentPlace(), this.navMod.getCurrentHeading());
		this.notifyRobotUpdate();
	}
		
	public boolean robotDied(){
		if(this.Deposito<=0){
			return true;
		}
		else return false;
	}
	
	/**
	 * Prints the information about all possible instructions
	 */
	public void requestHelp(){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).communicationHelp(Interpreter.interpreterHelp());
		}
	}
	
	public void requestError(String msg){
		for(int i = 0; i < this.observadores.size();i++){
			this.observadores.get(i).raiseError(msg);
		}
	}
			
	public void addNavigationObserver(NavigationObserver observer){
		this.navMod.addObserver(observer);
	}
	
	
	public void addEngineObserver(RobotEngineObserver observer){
		this.addObserver(observer);
	}
	
	public void addItemContainerObserver(InventoryObserver observer){
		this.inventario.addObserver(observer);
	}
	
	//Removes a GameObserver attached to the model
	public void removeRobotEngineObserver(RobotEngineObserver observer){
		this.observadores.remove(observer);
	}
	
	//Removes a MapObserver attached to the map contained in the model
	public void removeNavigationObserver(NavigationObserver observer){
		this.navMod.removeObserver(observer);
	}
	 
	
	//Removes a PlayerObserver attached to the player contained in the model
	public void removeInventoryObserver(InventoryObserver observer){
		this.inventario.removeObserver(observer);
	}
	
	public boolean isOver(){
		 return this.end;
	}

	public void setOver(boolean extEnd) {
		this.end = extEnd;
	}
		
	//Get the ArrayList of GameObservers.
	public ArrayList<RobotEngineObserver> getObservadores(){
		return this.observadores;
	}
	
	//Set the ArrayList of GameObservers.
	public void setObservadores(ArrayList<RobotEngineObserver> obs){
		this.observadores = obs;
	}

	public void notifyRobotUpdate() {
		for(RobotEngineObserver reo: observadores){
			reo.robotUpdate(this.Deposito, this.MaterialReciclado);
			
		}
	}

	public void notifyRobotSays(String msg) {
		for(RobotEngineObserver reo: observadores){
			reo.robotSays(msg);
		}
		
	}

}