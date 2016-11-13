package be.howest.input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.howest.util.GameUtils;
import net.java.games.input.*;
import net.java.games.input.Component.Identifier.Button;

public class Gamepad implements Runnable {
	private List<Controller> controllers = new ArrayList<>();
	private Controller selectedController;
	boolean foundController;

	public boolean ControllerActive() {
		return foundController;
	}

	// For testing purposes
	@SuppressWarnings("unused")
	private Gamepad(int index) {
		populateListWithControllers();
		selectedController = controllers.get(index);

	}

	public Gamepad(){
		populateListWithControllers();
		foundController = false;
	}

	public Controller getSelectedController() {
		return selectedController;
	}

	public void turnOnController() {
		if (!selectedController.poll()) {
			selectedController.poll();
		}
	}

	public void turnOffController() {
		if (selectedController.poll()) {
			selectedController.poll();
		}
	}

	// For testing
	public List<Component> getComponentFromController(int index) {
		List<Component> components = new ArrayList<>();
		for (Component com : controllers.get(index).getComponents()) {
			components.add(com);
			System.out.println(com.getIdentifier().getName());
		}
		return components;
	}

	private void populateListWithControllers() {
		Controller[] con = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller controller : con) {
			if (controller.getType() == Controller.Type.GAMEPAD) {
				controllers.add(controller);
			}
		}
	}

	public List<Controller> getControllers() {
		return controllers;
	}

	public float getX() {
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.X).getPollData(), -1F, 1F, 0F);
	}

	public float getY() {
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.Y).getPollData(), -1F, 1F, 0F);
	}

	public float getRX() {
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.RX).getPollData(), -1F, 1F,
				0F);
	}

	public float getRY() {
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.RY).getPollData(), -1F, 1F,
				0F);
	}

	public boolean getButton(int index) {
		Button component = null;
		switch (index) {
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

		if (selectedController.getComponent(component).getPollData() == 0F)
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @return UP = 0.25 || RIGHT = 0.50 || DOWN = 0.75 || LEFT = 1.0 || UP +
	 *         RIGHT = 0.365 || RIGHT + DOWN = 0.625 || DOWN + LEFT = 0.865 ||
	 *         LEFT + UP = 0.125
	 */
	public float getDPad() {
		return selectedController.getComponent(Component.Identifier.Axis.POV).getPollData();
	}

	public float getZ() {
		return GameUtils.clamp(selectedController.getComponent(Component.Identifier.Axis.Z).getPollData(), -1F, 1F, 0F);
	}

	public float getRotationL() {
		float x = deadZoneX(getX());
		float y = deadZoneY(getY());

		return GameUtils.clamp((float) (Math.toDegrees(-Math.atan2(x, y)) + 180), 0F, 360F);

	}

	public float getRotationR() {
		float rx = deadZoneX(getRX());
		float ry = deadZoneY(getRY());

		return GameUtils.clamp((float) (Math.toDegrees(-Math.atan2(rx, ry)) + 180), 0F, 360F);

	}

	private float deadZoneXValue;

	private float deadZoneX(float value) {
		if (-0.05F < value && value < 0.05F)
			return deadZoneXValue;
		else {
			deadZoneXValue = value;
			return value;
		}
	}

	private float deadZoneYValue;

	private float deadZoneY(float value) {
		if (-0.05F < value && value < 0.05F)
			return deadZoneYValue;
		else {
			deadZoneYValue = value;
			return value;
		}
	}

	@Override
	public void run() {
		System.out.println("RUN IN GAMEPAD");
		while ((!foundController)) {
			System.out.println("LOOP");
			for (Controller controller : controllers) {
				controller.poll();
				// Button 7 is the "Start" button on the controller!
				if (controller.getComponent(Component.Identifier.Button._7).getPollData() == 1.0F) {
					foundController = true;
					selectedController = controller;

				}
				controller.poll();
			}
		}
		
	}
}