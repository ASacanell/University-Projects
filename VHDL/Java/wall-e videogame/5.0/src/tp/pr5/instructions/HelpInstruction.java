package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class HelpInstruction implements Instruction {

	/**
	 * @uml.property  name="engine"
	 * @uml.associationEnd  
	 */
	private RobotEngine engine;
	
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		if(cad.equalsIgnoreCase("AYUDA") || cad.equalsIgnoreCase("HELP"))
		{
			return this;
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "HELP | AYUDA";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		engine.requestHelp();		
	}


}
