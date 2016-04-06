package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

public class ScanInstruction implements Instruction {

	/**
	 * @uml.property  name="itemId"
	 */
	private String itemId;
	/**
	 * @uml.property  name="robotContainer"
	 * @uml.associationEnd  
	 */
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
			{
				/*
				 * Avisar a los observadores
				 */
				this.robotContainer.notifyInventoryScanned(null);
			}	
			else
			{
				/*
				 * Avisar a los observadores
				 */
				this.robotContainer.notifyInventoryScanned(this.robotContainer.toString());

			}
		}else{
			Item it = this.robotContainer.getItem(itemId);
			
			if(this.robotContainer.containsItem(itemId))
			{
				/*
				 * Avisar a los observadores
				 */
				this.robotContainer.notifyItemScanned(it.toString());
					
			}
			else 
			{
				throw new InstructionExecutionException("You do not have any " + itemId + ".");	
			}
		}
	}
	
}