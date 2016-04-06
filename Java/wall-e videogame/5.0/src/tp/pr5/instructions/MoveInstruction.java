package tp.pr5.instructions;

import tp.pr5.Constants;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class MoveInstruction implements Instruction {
	
	/**
	 * @uml.property  name="engine"
	 * @uml.associationEnd  
	 */
	private RobotEngine engine;
	/**
	 * @uml.property  name="navigation"
	 * @uml.associationEnd  
	 */
	private NavigationModule navigation;

	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		if(cad.equalsIgnoreCase("MOVE") || cad.equalsIgnoreCase("MOVER"))
		{
			return new MoveInstruction();
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "MOVE | MOVER";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.navigation = navigation;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		
		this.navigation.move();
	
			/*
			 * Avisar a los observadores
			 */
		this.engine.addFuel(Constants.LOST_LIVE);
	
	}

}
