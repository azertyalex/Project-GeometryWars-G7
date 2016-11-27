package be.howest.input;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import be.howest.game.Handler;
import be.howest.objects.GameObject;
import be.howest.util.GameLoop;

public class BetterPlayerMovement extends KeyAdapter{
	private GameObject player;
	private int up,down,left,right = 0;
	private Handler handler;
	private BindableKeys keys;
	private boolean testToggle = true;
	
	public BetterPlayerMovement(){
		keys = new BindableKeys();
		
	}
	
	public BetterPlayerMovement(GameObject player, Handler handler){
		this.player = player;
		this.handler = handler;
		
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		

		
		if(key == keys.getKey("up")){
			if(up < 100) up++;
			System.out.println(up);
		}
		
		
		
		
		
		
		
		//for testing
		if(key == KeyEvent.VK_ENTER){
			System.out.println("Toggled " + testToggle);
			if(testToggle){
				testToggle = false;
				keys.bindKey("up", KeyEvent.VK_UP);
				keys.bindKey("down", KeyEvent.VK_DOWN);
				keys.bindKey("left", KeyEvent.VK_LEFT);
				keys.bindKey("right", KeyEvent.VK_RIGHT);
				
			}else{
				testToggle = true;
				keys.bindKey("up", KeyEvent.VK_Z);
				keys.bindKey("down", KeyEvent.VK_S);
				keys.bindKey("left", KeyEvent.VK_Q);
				keys.bindKey("right", KeyEvent.VK_D);
			}
		}
		
		
		
		
		
		
		/*
		if(key == KeyEvent.VK_Z) tempObject.setVelY(-tempObject.getSpeed());
		if(key == KeyEvent.VK_S) tempObject.setVelY(tempObject.getSpeed());
		if(key == KeyEvent.VK_Q) tempObject.setVelX(-tempObject.getSpeed());
		if(key == KeyEvent.VK_D) tempObject.setVelX(tempObject.getSpeed());
		*/
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		
		if(key == keys.getKey("up")){
			if(up > 0) up--;
			System.out.println(up);
			
		}
		
		

	}

	
	

	
	

	

}
