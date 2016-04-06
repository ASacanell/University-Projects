package tp.pr4.instructions;

import javax.swing.JOptionPane;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

public class MoveInstruction implements Instruction {
	
	private RobotEngine engine;
	private NavigationModule navigation;

	public Instruction parse(String cad) throws WrongInstructionFormatException{
		
		if(cad.equalsIgnoreCase("MOVE") || cad.equalsIgnoreCase("MOVER"))
		{
			return new MoveInstruction();
		}
		throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "MOVE | MOVER";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.engine = engine;
		this.navigation = navigation;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		
		this.navigation.move();
		this.engine.addFuel(-5);
		this.engine.printRobotState();
		if(this.navigation.getNavigationPanel() != null)
		{
			this.navigation.getNavigationPanel().updatePlaceInventory(this.navigation.getCurrentPlace());
		}
		if(this.navigation.getCurrentPlace().isSpaceship())
		{
			this.engine.requestQuit();
			System.out.println("WALL·E says: I am at my spaceship. Bye bye");
			if(this.navigation.getNavigationPanel()!=null)
				engine.gameOver();
		}
		this.engine.robotDied();
	}

}
