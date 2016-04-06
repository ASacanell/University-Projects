package tp.pr2;

public class Instruction {

	private Action action;
	private Rotation rotation;
	private String idItem;

	/**
	 * Default constructor. It creates an UNKNOWN action and rotation. By
	 * default, the identifier is the empty string
	 */
	public Instruction() {
		this.action = Action.UNKNOWN;
		this.rotation = Rotation.UNKNOWN;
		this.idItem = null;
	}

	/**
	 * Creates an instruction only with the action (the rotation is UNKNOWN) The
	 * identifier is the empty string
	 * 
	 * @param extAction
	 *            Action Instruction
	 */
	public Instruction(Action extAction) {
		this.action = extAction;
		this.rotation = Rotation.UNKNOWN;
		this.idItem = null;
	}

	/**
	 * Creates an instruction with the action and the rotation parameter. The
	 * identifier is the empty string
	 * 
	 * @param extAction
	 *            Action instruction
	 * @param extRotation
	 *            Direction of the rotation
	 */
	public Instruction(Action extAction, Rotation extRotation) {
		this.action = extAction;
		this.rotation = extRotation;
		this.idItem = null;
	}

	/**
	 * Creates an instruction with the action and the identifier for the
	 * instruction. It corresponds to instructions OPERATE, SCAN and PICK. The
	 * rotation is UNKNOWN
	 * 
	 * @param extAction
	 *            Action instruction
	 * @param extItem
	 *            Identifier for the instruction
	 */
	public Instruction(Action extAction, String extItem) {
		this.action = extAction;
		this.idItem = extItem;
		this.rotation = Rotation.UNKNOWN;
	}

	/**
	 * Checks if the instruction is a valid instruction
	 * 
	 * @return false if the action is UNKNOWN, the action is TURN but the
	 *         rotation is UNKNOWN or if an item identifier is mandatory for the
	 *         given action but it does not contain an item identifier. Return
	 *         true otherwise
	 */
	public boolean isValid() {

		if (this.action.equals(Action.UNKNOWN)) // Si la accion es Desconocida
		{
			return false;
		} else if ((this.action.equals(Action.TURN))
				&& (this.rotation.equals(Rotation.UNKNOWN))) // Si la accion es
																// TURN, y
																// ademas la
																// rotacion es
																// desconocida
		{
			return false;
		} else if ((this.action.equals(Action.PICK)) && (this.idItem == null)) {
			return false;
		} else if ((this.action.equals(Action.OPERATE))
				&& (this.idItem == null)) {
			return false;
		} else
			return true;

	}

	/**
	 * Getter
	 * 
	 * @return the action of the instruction
	 */
	public Action getAction() {

		return this.action;
	}

	/**
	 * Getter
	 * 
	 * @return the rotation of the instruction
	 */
	public Rotation getRotation() {

		return this.rotation;
	}

	/**
	 * Method that sets the rotation
	 * 
	 * @param rotacion
	 *            the rotation
	 */
	public void setRotation(Rotation rotacion) {
		this.rotation = rotacion;
	}

	/**
	 * Gets the item identifier stored in the instruction
	 * 
	 * @return The identifier of the instruction associated to the instruction
	 */
	public String getId() {
		return this.idItem;
	};
}
