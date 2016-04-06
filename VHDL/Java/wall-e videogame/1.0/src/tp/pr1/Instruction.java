package tp.pr1;

public class Instruction {
	
	private Action action;
	private Rotation rotation;
	
		/**
		 * Constructor que devuelve una Instruccion con accion y rotacion desconocidas
		 */
	public Instruction(){
		this.action = Action.UNKNOWN;
		this.rotation = Rotation.UNKNOWN;
	}
	
		/**
		 * Constructor que devuelve una Instruccion con accion conocida pero rotacion desconocida
		 * @param extAction La accion, que debe ser Help, Quit o Move
		 */
	public Instruction(Action extAction){
		this.action = extAction;
		this.rotation = Rotation.UNKNOWN;
	}
	
		/**
		 * Constructor que devuelve una Instruccion con accion y rotacion conocidas
		 * @param extAction La accion que debe ser Turn
		 * @param extRotation La rotacion que debe ser Left o Right
		 */
	public Instruction(Action extAction, Rotation extRotation){
		this.action = extAction;
		this.rotation = extRotation;
	}
	
		/**
		 * Metodo que comprueba que una instruccion sea valida.
		 * @return Booleano indicando si es valida o no
		 */
	public boolean isValid(){
			
		if(this.action.equals(Action.UNKNOWN))	//Si la accion es Desconocida
		{
			return false;
		}else if((this.action.equals(Action.TURN)) && (this.rotation.equals(Rotation.UNKNOWN)))	//Si la accion es TURN, y ademas la rotacion es desconocida
		{
			return false;
		}
		else return true;

	}

		/**
		 * Getter
		 * @return La accion del objeto
		 */
	public Action getAction(){
		
		return this.action;
	}
	
		/**
		 * Getter
		 * @return La rotacion del objeto
		 */
	public Rotation getRotation(){
		
		return this.rotation;
	}
	
		/**
		 * Setter
		 * @param rotacion Rotacion que se quiere establecer al objeto
		 */
    public void setRotation(Rotation rotacion){
    	this.rotation = rotacion;
    }

}
