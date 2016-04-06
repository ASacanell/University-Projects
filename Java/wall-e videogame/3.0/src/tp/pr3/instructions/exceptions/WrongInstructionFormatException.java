package tp.pr3.instructions.exceptions;

@SuppressWarnings("serial")
public class WrongInstructionFormatException extends java.lang.Exception{
	
	
	public WrongInstructionFormatException(){
		super();
	}
	
	public WrongInstructionFormatException(java.lang.String arg0){
		super(arg0);
		
	}
	
	public WrongInstructionFormatException(java.lang.String arg0, java.lang.Throwable arg1){
		super(arg0,arg1);
		
	}
	
	public WrongInstructionFormatException(java.lang.Throwable arg0){
		super(arg0);
		
	}

}