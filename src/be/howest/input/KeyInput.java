package be.howest.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for (int i = 0; i< handler.getList().size();i++){
			GameObject tempObject = handler.getList().get(i);
			
			if(tempObject.getId() == ID.Player2){
				//Keyevent for player
				
				if(key == KeyEvent.VK_Z) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_S) tempObject.setVelY(5);
				if(key == KeyEvent.VK_Q) tempObject.setVelX(-5);
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for (int i = 0; i< handler.getList().size();i++){
			GameObject tempObject = handler.getList().get(i);
			
			if(tempObject.getId() == ID.Player2){
				//Keyevent for player
				
				if(key == KeyEvent.VK_Z) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_Q) tempObject.setVelX(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
			}
		}
	}
	


}