package tp.pr2;

public class CodeCard extends Item {

	private String code;

	/**
	 * Constructor
	 * @param extId card name
	 * @param extDescription card description
	 * @param extCode code stored in the code card
	 */
	public CodeCard(String extId, String extDescription, String extCode) {
		super(extId, extDescription);
		this.code = extCode;
	}

	/**
	 * A code card always can be used. Since the robot has the code card it never loses it
	 * @return true because it always can be used
	 */
	public boolean canBeUsed() {
		return true;
	}

	/**
	 * The method to use a code card. If the robot is in a place which contains
	 * a street in the direction he is looking at, then the street is opened or
	 * closed if the street code and the card code match.
	 * 
	 * @param r the robot engine employed to use the card.
	 * @param p the place where the card is going to be used
	 * @return true If the codecard can complete the action of opening or closing a street. Otherwise it returns false.
	 */
	public boolean use(RobotEngine r, Place p) {
		Street streetFound = r.getHeadingStreet();
		boolean successful = false;

		if (streetFound != null) {
			if (streetFound.isOpen()) {
				successful = streetFound.close(this);
			} else if (!streetFound.isOpen()) {
				successful = streetFound.open(this);
			}
			if (!successful)
				System.out
						.println("WALL·E says: I have problems using the object "
								+ this.getId().toLowerCase());
		} else {
			System.out
					.println("WALL·E says: I have problems using the object "
							+ this.getId().toLowerCase());
			successful = false;
		}

		return successful;
	}

	/**
	 * Method that returns an array with the code of the card
	 * @return the code of the card
	 */
	public String getCode() {
		return this.code;
	}

}
