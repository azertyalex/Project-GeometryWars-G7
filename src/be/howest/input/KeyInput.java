package be.howest.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;
import be.howest.objects.Wanderer;

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
				
				if(key == KeyEvent.VK_Z) tempObject.setVelY(-tempObject.getSpeed());
				if(key == KeyEvent.VK_S) tempObject.setVelY(tempObject.getSpeed());
				if(key == KeyEvent.VK_Q) tempObject.setVelX(-tempObject.getSpeed());
				if(key == KeyEvent.VK_D) tempObject.setVelX(tempObject.getSpeed());
			}
		}
		
		if(key == KeyEvent.VK_ENTER) handler.addObject(new Wanderer(10,10,50,50,ID.Wanderer,ID.Enemy,handler));
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
