package be.howest.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import be.howest.game.Handler;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		
	}

}
