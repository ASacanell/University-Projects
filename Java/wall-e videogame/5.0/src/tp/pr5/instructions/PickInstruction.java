package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

public class PickInstruction implements Instruction {


	private NavigationModule navigation;
	private String itemId;
	private ItemContainer robotContainer;
	private RobotEngine engine;

	
	public PickInstruction(){};
	
	public PickInstruction(String extItemId){
		this.itemId = extItemId;
	}
	
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		String[] words = cad.split(" ");
		
		if(words.length == 2)
		{
			if(words[0].equalsIgnoreCase("COGER") || words[0].equalsIgnoreCase("PICK"))
			{
			this.itemId = words[1];
			return this;
			}
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "PICK | COGER <id>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		
		if(this.navigation.getCurrentPlace().existItem(itemId))
		{
			if(this.robotContainer.containsItem(itemId))
			{
				throw new InstructionExecutionException("WALL·E says: I am stupid! I had already the object " + itemId);
			}
			else
			{
				if(this.robotContainer.addItem(this.navigation.getCurrentPlace().pickItem(itemId)))
				{
					/*
					 * Avisar a los observadores
					 */
				
					this.engine.notifyRobotSays("I am happy! Now I have " + itemId);					
					this.robotContainer.notifyInventoryUpdate(robotContainer);
					this.navigation.notifyPlaceHasChanged(this.navigation.getCurrentPlace());					
    				
				}
				
			}	
		}
		else{ 
			throw new InstructionExecutionException("WALL·E says: Ooops, this place has not the object " + itemId.toLowerCase());
		}
	}
}

