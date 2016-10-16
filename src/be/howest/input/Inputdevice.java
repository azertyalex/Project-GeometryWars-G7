package be.howest.input;

import java.util.*;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public abstract class Inputdevice {
	private List<Controller> controllers = new ArrayList<>();
	
	
	public Inputdevice(){
		
	}
	
	public void getAllInputdevices(){
		Controller[] con = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for(Controller controller : con){
			if(controller.getType() == Controller.Type.GAMEPAD){
				controllers.add(controller);     
			}
		}
	}

}
