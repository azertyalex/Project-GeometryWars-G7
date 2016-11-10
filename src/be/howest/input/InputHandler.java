package be.howest.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.howest.game.Game;
import be.howest.game.Game.STATE;
import be.howest.gfx.UserInterface;

public class InputHandler implements KeyListener, MouseListener {

	private static STATE currentState;

	public InputHandler(){
		
	}
	
	public STATE getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(STATE parCurrentState) {
		currentState = parCurrentState;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Do Nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Do Nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(getCurrentState() != STATE.PLAY){
			UserInterface UI = (UserInterface) Game.stateMap.get(getCurrentState());
			
			UI.mouseAction(e);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Do Nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Do Nothing
	}

}
