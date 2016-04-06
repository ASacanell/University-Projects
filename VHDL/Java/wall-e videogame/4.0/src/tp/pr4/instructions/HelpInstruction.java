package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

public class HelpInstruction implements Instruction {

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
