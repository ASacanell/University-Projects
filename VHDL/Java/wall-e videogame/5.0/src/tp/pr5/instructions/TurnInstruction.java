package tp.pr5.instructions;

import tp.pr5.Constants;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class TurnInstruction implements Instruction {

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
	/**
	 * @uml.property  name="currentRotation"
	 * @uml.associationEnd  
	 */
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
			this.engine.addFuel(Constants.LOST_LIVE);
	}
}
