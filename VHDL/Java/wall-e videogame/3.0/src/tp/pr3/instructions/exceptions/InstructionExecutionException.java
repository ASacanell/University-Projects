package tp.pr3.instructions.exceptions;

@SuppressWarnings("serial")
public class InstructionExecutionException extends java.lang.Exception {
	
	
	public InstructionExecutionException(){
		super();
	}
	
	public InstructionExecutionException(java.lang.String arg0){
		System.out.println(arg0);
	}
	
	public InstructionExecutionException(java.lang.String arg0, java.lang.Throwable arg1){
		System.out.println(arg0);
		System.out.println(arg1);
		
	}
	
	public InstructionExecutionException(java.lang.Throwable arg1){
		System.out.println(arg1);
	}
}

