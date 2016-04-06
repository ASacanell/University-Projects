package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

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
