package be.howest.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import be.howest.game.Handler;

public class Mouse extends MouseAdapter{
	
	private Handler handler;
	
	public Mouse(Handler handler){
		this.handler = handler;
	}

	@Override
	public void mouseMoved(MouseEvent e){
		
	}
}
