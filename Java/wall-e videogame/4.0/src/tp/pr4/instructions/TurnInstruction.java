package tp.pr4.instructions;

import tp.pr4.Direction;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

public class TurnInstruction implements Instruction {

	private RobotEngine engine;
	private NavigationModule navigation;
	private Rotation currentRotation;
	
	public TurnInstruction(Rotation extRotation){
		this.currentRotation = extRotation;
	}
	
	public TurnInstruction(){};
	
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		String[] words = cad.split(" ");
		
		if(words.length == 2)
		{
			if(words[0].equalsIgnoreCase("TURN") || words[0].equalsIgnoreCase("GIRAR"))
			{
				if(words[1].equalsIgnoreCase(Rotation.LEFT.name())){
					currentRotation = Rotation.LEFT;
					return this;
				}else if(words[1].equalsIgnoreCase(Rotation.RIGHT.name())){
					currentRotation = Rotation.RIGHT;
					return this;
				}
				
			}
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "TURN | GIRAR <LEFT|RIGHT>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.navigation = navigation;
	}

	@Override
	public void execute() throws InstructionExecutionException {
			this.navigation.rotate(this.currentRotation);
			this.engine.addFuel(-5);
			System.out.println("WALL·E is looking at direction " + this.navigation.getCurrentHeading());
			this.engine.printRobotState();
			if(this.navigation.getNavigationPanel() != null){
			this.navigation.getNavigationPanel().updateIcon(this.navigation.getCurrentHeading());
			this.engine.updateRobotState();}
			this.engine.robotDied();
	}
}
