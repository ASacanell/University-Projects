package tp.pr4.instructions;

import javax.swing.JOptionPane;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

public class DropInstruction implements Instruction {

	private NavigationModule navigation;
	private String itemId;
	private ItemContainer robotContainer;
	private RobotEngine engine;

	public DropInstruction(){};
	
	public DropInstruction(String extItemId){
		
		this.itemId = extItemId;
		
	};
	
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		String[] words = cad.split(" ");
		
		if(words.length == 2)
		{
			if(words[0].equalsIgnoreCase("DROP") || words[0].equalsIgnoreCase("SOLTAR"))
			{
			this.itemId = words[1];
			return this;
			}
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "DROP | SOLTAR <id>";
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

	
		boolean checkInventory = this.robotContainer.containsItem((this.itemId));
		boolean checkPlace = this.navigation.findItemAtCurrentPlace(itemId);
		Boolean it = this.robotContainer.containsItem(this.itemId);
		
		if( !checkInventory || checkPlace|| !it){	
			throw new InstructionExecutionException("You do not have any "+this.itemId+".");
		}
		else{
			Item aux = this.robotContainer.pickItem(this.itemId);
			this.navigation.dropItemAtCurrentPlace(aux);
			System.out.println("Great! I have dropped " + this.itemId);
			if(this.navigation.getNavigationPanel()!=null)
			{
			this.navigation.getNavigationPanel().updatePlaceInventory(this.navigation.getCurrentPlace());
			this.engine.updateRobotState();
			}
		}	
	}
}

		
	

