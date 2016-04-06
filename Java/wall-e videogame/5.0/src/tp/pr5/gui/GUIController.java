package tp.pr5.gui;

import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.*;


public class GUIController extends tp.pr5.Controller {
	
	/**
	 * Constructor that uses the model
	 * @param robot - The reference to the model
	 */
	public GUIController(RobotEngine robot){
		super(robot);
	}

	@Override
	public void startEngine() {
		this.robot.requestStart();
	}
	
	
	public void moveController(){
		this.robot.communicateRobot(new MoveInstruction());
	}
	
	public void quitController(){	
	this.robot.communicateRobot(new QuitInstruction());
		
	}
	
	public void turnLeftController(){
		this.robot.communicateRobot(new TurnInstruction(Rotation.LEFT));
	}
	public void turnRightController(){
		this.robot.communicateRobot(new TurnInstruction(Rotation.RIGHT));
	}
	
	public void pickController(String selectedItem){
		this.robot.communicateRobot(new PickInstruction(selectedItem));
	}
	
	public void dropController(String selectedItem){
		this.robot.communicateRobot(new DropInstruction(selectedItem));
	}
	
	public void operatepController(String selectedItem){
		this.robot.communicateRobot(new OperateInstruction(selectedItem));
	}
}
