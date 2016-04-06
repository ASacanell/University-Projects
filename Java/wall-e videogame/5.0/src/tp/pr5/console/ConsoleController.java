package tp.pr5.console;

import java.util.Scanner;

import tp.pr5.Interpreter;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.RobotEngine;

public class ConsoleController extends tp.pr5.Controller{

	private Console console;

	/**
	 * Constructor of the controller. It receives the model main class.
	 * @param engine - The Robot Engine
	 */
	public ConsoleController(RobotEngine engine){
		super(engine);
		this.console = new Console();
		this.registerEngineObserver(this.console);
		this.registerNavigationObserver(this.console);
		this.registerItemContainerObserver(this.console);
	}

	public void startEngine(){
			
		Scanner scan = new Scanner(System.in);
		if(this.robot == null)
			this.robot.setOver(true);
	
		if(!this.robot.isOver()){
			
			this.robot.requestStart();
			
			do{
				try{
					
					console.showPrompt();
					String comando =  scan.nextLine();
					Instruction auxInstruction = Interpreter.generateInstruction(comando);
					this.robot.communicateRobot(auxInstruction);
					
				}catch (WrongInstructionFormatException e) {
					this.console.raiseError("WALL·E says: I don't understand you. Please Repeat.");
				}
	
			
			}while(!this.robot.isOver());
		}
		scan.close();
	}
}