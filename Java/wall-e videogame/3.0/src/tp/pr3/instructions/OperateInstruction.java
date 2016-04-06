package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class OperateInstruction implements Instruction {
	
	private RobotEngine engine;
	private String itemId;
	private ItemContainer robotContainer;
	private NavigationModule navigation;
	
	
	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		String[] words = cad.split(" ");
		
		if(words.length == 2)
		{
			if(words[0].equalsIgnoreCase("OPERATE") || words[0].equalsIgnoreCase("OPERAR"))
			{
			this.itemId = words[1];
			return this;
			}
		}
		throw new WrongInstructionFormatException();
	}
	

	@Override
	public String getHelp() {
		return "OPERATE | OPERAR <id>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.robotContainer = robotContainer;
		this.navigation = navigation;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		
		Item it = this.robotContainer.getItem(this.itemId);
		
		if(it==null)
		{throw new InstructionExecutionException("WALL·E says: I have problems using the object " + this.itemId.toLowerCase());}
		else
		{
			if(it.use(engine, navigation))
			{
				this.robotContainer.useItem(it);
			}
			else
			{throw new InstructionExecutionException("WALL·E says: I have problems using the object " + this.itemId.toLowerCase());}
		}
	/*		
			if(!it.use(engine, navigation))
			{throw new InstructionExecutionException("WALL·E says: I have problems using the object " + this.itemId.toLowerCase());}
		}
		else
			
		if(!it.use(engine, navigation))
		{throw new InstructionExecutionException("WALL·E says: I have problems using the object " + this.itemId.toLowerCase());}
	*/	
/*
		if(it!=null){
			if(!it.use(engine, navigation))
			{throw new InstructionExecutionException("WALL·E says: I have problems using the object " + this.itemId.toLowerCase());}
		}else
			{throw new InstructionExecutionException("WALL·E says: I have problems using the object " + this.itemId.toLowerCase());}

		this.robotContainer.useItem(it);
		*/
	}
}

