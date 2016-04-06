package tp.pr5;

public interface RobotEngineObserver {
	
	/**
	 * The robot engine informs that it has raised an error
	 * @param msg - Error message
	 */
	void raiseError(java.lang.String msg);
	
	/**
	 * The robot engine informs that the help has been requested
	 * @param help - A string with information help
	 */
	void communicationHelp(java.lang.String help);
	
	/**
	 * The robot engine informs that the robot has shut down (because it has arrived at the spaceship or it has run out of fuel)
	 * @param atShip - true if the robot shuts down because it has arrived at the spaceship or false if it has run out of fuel
	 */
	void engineOff(boolean atShip);
	
	/**
	 * The robot engine informs that the communication is over.
	 */
	void communicationCompleted();
	
	/**
	 * The robot engine informs that the fuel and/or the amount of recycled material has changed
	 * @param fuel - Current amount of fuel
	 * @param recycledMaterial - Current amount of recycled material
	 */
	void robotUpdate(int fuel,int recycledMaterial);
	
	/**
	 * The robot engine informs that the robot wants to say something
	 * @param message - The robot message
	 */
	void robotSays(java.lang.String message);
		
}
