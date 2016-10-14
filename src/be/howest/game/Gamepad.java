package be.howest.game;

import java.util.ArrayList;
import java.util.List;


import net.java.games.input.*;
import net.java.games.input.Component.Identifier.Button;

public class Gamepad {
	private List<Controller> controllers = new ArrayList<>();
	private Controller selectedController;
	
	public Gamepad(int index){
		populateListWithControllers();
		selectedController = controllers.get(index);
		
	}
	
	public void turnOnController(){
		if(!selectedController.poll()){
			selectedController.poll();
		}
	}
	
	public void turnOffController(){
		if(selectedController.poll()){
			selectedController.poll();
		}
	}
	
	public List<Component> getComponentFromController(int index){
		List<Component> components = new ArrayList<>();
		for(Component com : controllers.get(index).getComponents()){
			components.add(com);
		}
		return components;
	}
	
	private void populateListWithControllers(){
		Controller[] con = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for(Controller controller : con){
			if(controller.getType() == Controller.Type.GAMEPAD){
				controllers.add(controller);     
			}
		}
	}
	
	public List<Controller> getControllers(){ 
		return controllers;
	}
	
	public float getX(){
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.X).getPollData(), -1F, 1F, 0F);
	}
	
	public float getY(){
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.Y).getPollData(), -1F, 1F, 0F);
	}
	
	public float getRX(){
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.RX).getPollData(), -1F, 1F, 0F);
	}
	
	public float getRY(){
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.RY).getPollData(), -1F, 1F, 0F);
	}
	
	public boolean getButton(int index){
		Button component = null;
		switch (index){
			case 0:
				component = Component.Identifier.Button._0;
				break;
			case 1:
				component = Component.Identifier.Button._1;
				break;
			case 2:
				component = Component.Identifier.Button._2;
				break;
			case 3:
				component = Component.Identifier.Button._3;
				break;
			case 4:
				component = Component.Identifier.Button._4;
				break;
			case 5:
				component = Component.Identifier.Button._5;
				break;
			case 6:
				component = Component.Identifier.Button._6;
				break;
			case 7:
				component = Component.Identifier.Button._7;
				break;
			case 8:
				component = Component.Identifier.Button._8;
				break;
			case 9: 
				component = Component.Identifier.Button._9;
				break;
			default:
				return false;
		}
				
		
		if(selectedController.getComponent(component).getPollData() == 0F) return false;
		else return true;
	}
	
	
	/**
	 * 
	 * @return  UP = 0.25 || RIGHT = 0.50 || DOWN = 0.75 || LEFT = 1.0
	 */
	public float getDPad(){
		return selectedController.getComponent(Component.Identifier.Axis.POV).getPollData();
	}
	
	
	public float getZ(){
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.Z).getPollData(), -1F, 1F, 0F);
	}
	
	public float getRZ(){
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.RZ).getPollData(), -1F, 1F, 0F);
	}
	
	
	


}
