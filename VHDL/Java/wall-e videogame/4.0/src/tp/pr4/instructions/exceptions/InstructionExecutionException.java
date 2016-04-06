package tp.pr4.instructions.exceptions;

@SuppressWarnings("serial")
public class InstructionExecutionException extends java.lang.Exception {
	
	
	public InstructionExecutionException(){
	}
	
	public InstructionExecutionException(java.lang.String arg0){
		super(arg0);
	}
	
	public InstructionExecutionException(java.lang.String arg0, java.lang.Throwable arg1){
		super(arg0, arg1);
		
	}
	
	public InstructionExecutionException(java.lang.Throwable arg1){
		super(arg1);
	}
}

