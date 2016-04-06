package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

public class RadarInstruction implements Instruction {

	private NavigationModule navigation;
	
	
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		if(cad.equalsIgnoreCase("RADAR"))
		{
			return new RadarInstruction();
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "RADAR";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigation = navigation;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		this.navigation.getCurrentPlace().printPlaceState();
		
	}

}
