package tp.pr5;

import tp.pr5.items.InventoryObserver;

public abstract class Controller {
	
	protected RobotEngine robot;

	public Controller(RobotEngine extEngine){
		this.robot = extEngine;
	}
	
	public void registerEngineObserver(RobotEngineObserver observer){
		this.robot.addEngineObserver(observer);
	}
	
	public void registerItemContainerObserver(InventoryObserver observer){
		this.robot.addItemContainerObserver(observer);
	}
	
	public void registerNavigationObserver(NavigationObserver observer){
		this.robot.addNavigationObserver(observer);
	}
	
	public abstract void startEngine();
	
	public RobotEngine getRobotEngine() {
		return this.robot;
	}
	public void setRobotEngine(RobotEngine robot) {
		this.robot = robot;
	}
	
	
	

}
