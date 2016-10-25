package be.howest.input;

import java.util.List;

import net.java.games.input.*;

public class KeyboardAndMouse extends Inputdevice {

	
	
	private Keyboard selectedKeyboard;
	private Mouse selectedMouse;
	
	public KeyboardAndMouse(){
		Controller[] keyboard = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for(Controller controller : keyboard){
			
			
			if(controller.getType() == Controller.Type.KEYBOARD){
				selectedKeyboard = (Keyboard) controller;    
			}
			
			if(controller.getType() == Controller.Type.MOUSE){
				selectedMouse = (Mouse) controller;    
			}
			
			
		}
		
		
		//System.out.println(selectedMouse.getName() + " || " +selectedMouse.getType());
		

		
		
		
		
	}
	
	public void turnOn(){
		if(!selectedKeyboard.poll()) selectedKeyboard.poll();
		if(!selectedMouse.poll()) selectedMouse.poll();
	}
	
	public float getMouseX(){
		return selectedMouse.getComponent(Component.Identifier.Axis.X).getPollData();
	}
	
	public float getMouseY(){
		return selectedMouse.getY().getPollData();
	}
	
	
	
	
	
	
	
	
	
}
