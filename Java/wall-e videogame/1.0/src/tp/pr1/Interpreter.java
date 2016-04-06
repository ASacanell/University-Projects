package tp.pr1;

public class Interpreter {
	
		/**
		 * Metodo que interpreta el comando que se debe procesar
		 * @param line String con el comando escrito por el usuario en consola
		 * @return Instruccion ya inicializada
		 */
	public Instruction generateInstruction(String line){
				
	line = line.replaceAll(" +", " ").trim();		//Elimina espacios al principio, al final, y cambia una serie de espacios seguidos por solo uno
	String[] words = line.split(" ");			//Divide el String line en " ", añadiendo cada tramo a un Array de Strings llamado words.
		
	if(words.length == 1){ // Help, Quit, Move.
		if(words[0].equalsIgnoreCase("help")){
			return new Instruction(Action.HELP);
			
		} else if(words[0].equalsIgnoreCase("quit")){
			return new Instruction(Action.QUIT);
			
		}else if(words[0].equalsIgnoreCase("move")){
			return new Instruction(Action.MOVE);
			
		}
	}else if(words.length == 2){ // Turn + [LEFT | RIGHT]. 
		if(words[0].equalsIgnoreCase("turn")){
			if(words[1].equalsIgnoreCase(Rotation.LEFT.name())){
				return new Instruction(Action.TURN, Rotation.LEFT);
				
			}else if(words[1].equalsIgnoreCase(Rotation.RIGHT.name())){
				return new Instruction(Action.TURN, Rotation.RIGHT);
				
			}else
				return new Instruction(Action.TURN, Rotation.UNKNOWN);
		}
	}
	
	return new Instruction();
	
	}
		
		/**
		 * Metodo para invocar la ayuda a Wall·E
		 * @return	String con el contenido de ayuda de Wall-e(comandos de los que dispone)
		 */
	public String interpreterHelp(){
				
		return ("The valid instructions for WALL·E are:\n" + "  MOVE\n" + "  TURN <LEFT | RIGHT>\n" + "  HELP\n" + "  QUIT");
	}
}
