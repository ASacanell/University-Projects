package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.items.ItemContainer;
import tp.pr5.instructions.exceptions.*;

/**
 * @author  usuario
 */
public interface Instruction {
	
	/**
	 * 
	 * @param cad Text String
	 * @param execContext Executing Game
	 * @return Instruction Reference pointing to an instance of a Instruction subclass, if it is corresponding to the String cad
	 * @throws WrongInstructionFormatException When the String cad does not fit the Instruction syntax.
	 */
	Instruction parse(String cad) throws WrongInstructionFormatException;
	
	
	/**
	 * Returns a description of the Instruction syntax. The string does not end with the line separator. It is up to the caller adding it before printing.
	 * @return  The Instruction's syntax.
	 * @uml.property  name="help"
	 */
	String getHelp();
	
	/**
	 * Set the execution context. The method receives the entire engine (engine, navigation and the robot container) even though the actual implementation of execute() may not require it.
	 * @param engine The robot engine
	 * @param navigation The information about the game, i.e., the places, current direction and current heading to navigate
	 * @param robotContainer The inventory of the robot
	 */
	void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);
	
	
	/**
	 * Executes the Instruction It must be implemented in every non abstract subclass.
	 * @throws InstructionExecutionException if there exist any execution error.
	 */
	public void execute() throws InstructionExecutionException;
	
}