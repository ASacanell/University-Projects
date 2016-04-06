package tp.pr4;

import tp.pr4.instructions.*;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;

public class Interpreter {
	
	private static Instruction[] instructionsArray =new Instruction[]{
	
		new DropInstruction(),
		new HelpInstruction(),
		new MoveInstruction(),
		new OperateInstruction(),
		new PickInstruction(),
		new QuitInstruction(),
		new RadarInstruction(),
		new ScanInstruction(),
		new TurnInstruction(),
	};
	
	/**
	 * It returns information about all the instructions that the robot understands
	 * @return A string with the information about all the available instructions
	 */
	public static String interpreterHelp(){
		
		String aux = "";
		
		for (int n=0; n < instructionsArray.length; n++)
		{
			aux += instructionsArray[n].getHelp();
			aux += "\n";
		}
		return aux;
	}
	
	
	/**
	 * Generates a new instruction according to the user input
	 * @param line  A string with the user input
	 * @return The instruction read from the given line. If the instruction is not correct, then it throws an exception.
	 * @throws WrongInstructionFormatException
	 */
	public static Instruction generateInstruction(String line) throws WrongInstructionFormatException
	{
		line = line.replaceAll(" +", " ").trim();
		
		for (int n=0; n < instructionsArray.length; n++)
		{
			try{
				return instructionsArray[n].parse(line);
			}catch(WrongInstructionFormatException exc){}
		}
		throw new WrongInstructionFormatException();
	}
		
}