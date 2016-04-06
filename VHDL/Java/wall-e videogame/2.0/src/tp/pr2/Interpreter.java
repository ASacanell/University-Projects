package tp.pr2;

public class Interpreter {

	/**
	 * Generates a new instruction according to the user input
	 * @param line A string with the user input
	 * @return The instruction read from the given line. If the instruction is not correct, then it returns a not valid instruction, i.e., an empty instruction (invoking to the default constructor of class Instruction
	 */
	public static Instruction generateInstruction(String line){

		line = line.replaceAll(" +", " ").trim();
		String[] words = line.split(" ");

		if(words.length == 1){ // Help, Quit, Move, Scan.
			if(words[0].equalsIgnoreCase("help")){
				return helpCommand();
			} else if(words[0].equalsIgnoreCase("quit")){
				return quitCommand();
			}else if(words[0].equalsIgnoreCase("move")){
				return moveCommand();
			}else if(words[0].equalsIgnoreCase("scan")){
				return scanCommandInventory();
			}
		}else if(words.length >= 2){ // Turn + [LEFT | RIGHT], Pick + Item, Operate + Item, Scan + Item 
			if(words[0].equalsIgnoreCase("turn")){
				return turnCommand(words);
			}else if(words[0].equalsIgnoreCase("scan")){
				return scanCommandItem(words);
			}else if(words[0].equalsIgnoreCase("pick")){
				return pickCommand(words);
			}else if(words[0].equalsIgnoreCase("operate")){
				return operateCommand(words);
			}
		}
		return new Instruction();
	}

	/**
	 * It returns information about all the instructions that the robot understands
	 * @return A string with the information about all the available instructions
	 */
	public static String interpreterHelp(){

		return ("The valid instructions for WALL-E are:\n" + "     MOVE\n" + "     TURN <LEFT | RIGHT>\n" + "     PICK <id>\n" + "     SCAN [ <id> ]\n" + "     OPERATE <id>\n" + "     HELP\n" + "     QUIT");
	}

	/**
	 * Method that returns the HELP instruction
	 * @return a HELP instruction 
	 */
	private static Instruction helpCommand()
	{
		return new Instruction(Action.HELP);
	}
	
	/**
	 * Method that returns the QUIT instruction
	 * @return a QUIT instruction
	 */
	private static Instruction quitCommand()
	{
		return new Instruction(Action.QUIT);
	}
	
	/**
	 * Method that returns the MOVE instruction
	 * @return a MOVE instruction
	 */
	private static Instruction moveCommand()
	{
		return new Instruction(Action.MOVE);
	}

	/**
	 * Method that returns the SCAN instruction for an inventory
	 * @return a SCAN instruction for an inventory
	 */
	private static Instruction scanCommandInventory(){
			return new Instruction(Action.SCAN);
	}
		
	/**
	 * Method that returns the TURN instruction
	 * @param words Array where the rotation is stored
	 * @return a TURN instruction
	 */
	private static Instruction turnCommand(String[] words){
		if(words[1].equalsIgnoreCase(Rotation.LEFT.name())){
			return new Instruction(Action.TURN, Rotation.LEFT);

		}else if(words[1].equalsIgnoreCase(Rotation.RIGHT.name())){
			return new Instruction(Action.TURN, Rotation.RIGHT);

		}else
			return new Instruction(Action.TURN, Rotation.UNKNOWN);
	}
	
	/**
	 * Method that returns the PICK instruction
	 * @param words Array where the item is stored
	 * @return a PICK instruction
	 */
	private static Instruction pickCommand(String[] words){
		String item = words[1];
		return new Instruction(Action.PICK,item);
	}
	
	/**
	 * Method that returns the OPERATE instruction
	 * @param words Array where the item is stored
	 * @return an OPERATE instruction
	 */
	private static Instruction operateCommand(String[] words){
		String item = words[1];
		return new Instruction(Action.OPERATE,item);
	}
	
	/**
	 * Method that returns the SCAN instruction for an item
	 * @param words Array where the item is stored
	 * @return a SCAN instruction for an item
	 */
	private static Instruction scanCommandItem(String[] words){
		String item = words[1];
		return new Instruction(Action.SCAN,item);
	}
}
