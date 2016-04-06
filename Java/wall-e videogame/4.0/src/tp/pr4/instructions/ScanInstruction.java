package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

public class ScanInstruction implements Instruction {

	private String itemId;
	private ItemContainer robotContainer;
	
	
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		String[] words = cad.split(" ");
			
		if(words[0].equalsIgnoreCase("SCAN") || words[0].equalsIgnoreCase("ESCANEAR")){
			if(words.length == 1)
			{
				this.itemId = null;
				return this;
			}
			if(words.length == 2)
			{
				this.itemId = words[1];
				return this;
			}
		}
		throw new WrongInstructionFormatException();
		
	}
	

	@Override
	public String getHelp() {
		return "SCAN | ESCANEAR [id]";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		
		this.robotContainer=robotContainer;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		
		if(itemId==null)
		{
			if(this.robotContainer.numberOfItems()==0)
				System.out.println("WALL·E says: My inventory is empty");
			else
			{
				System.out.println("WALL·E says: I am carrying the following items");
				System.out.println(this.robotContainer.toString());
			}
		}else{
			Item it = this.robotContainer.getItem(itemId);
			if(this.robotContainer.containsItem(itemId))
				{System.out.println(it.toString());}
			else throw new InstructionExecutionException("WALL·E says: I have not such object");
		}
	}
	
}