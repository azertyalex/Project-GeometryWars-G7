package be.howest.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.*;
import be.howest.util.GameUtils;

public class testObject extends GameObject{
	private Gamepad gamepad;
	//private Mouse mouse;
	private int timer = 20;
	
	
	private Graphics2D g2d;
	
	
	public testObject(int x,int y, ID id){
		super(x,y,id);
	}
	
	public testObject(int x,int y,int height, int width, ID id){
		super(x,y,height,width,id,null);
	}
	
	public testObject(int x,int y,int height, int width, ID id, Handler handler){
		super(x,y,height,width,id,handler);
	}
	
	public testObject(int x,int y,int height, int width, ID id, Handler handler,boolean controller){
		super(x,y,height,width,id,handler,controller);
		if(controller) gamepad = new Gamepad();
		
	}

	@Override
	public void tick() {
		
		
		x = (int) (GameUtils.clamp(x, 0, Game.WIDTH - objectWidth) + velX);
		y = (int) (GameUtils.clamp(y, 0, Game.HEIGHT - objectHeight) + velY);
		
		
		setHeight(50);
		setWidth(50);
		
		if(controller){
			
			gamepad.turnOnController();
			if(gamepad.getDPad() == 0.25) speed++;
			if(gamepad.getDPad() == 0.75) speed--;	
			if( timer == 0){
				if(gamepad.getButton(5)){
					handler.addObject(new Lazer(50,10,ID.Enemy,(GameObject) this,handler,gamepad.getRotationR()));
					
				}
				timer = 10;
			}
			
			velX = (int) (gamepad.getX() * speed * 1.1);
			velY = (int) (gamepad.getY() * speed * 1.1);
		}
		
		timer--;
		
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		
		float rotation;
		if(controller){
			
			
			rotation = gamepad.getRotationR();
		}else{
			rotation = Mouse.getRotation();
		}
		
		g2d.finalize();
		
		g2d.rotate(Math.toRadians(rotation), getCenterX(), getCenterY());
		g2d.drawImage(GameUtils.loadImage("resources\\player\\player.png"), x, y, objectWidth, objectHeight,null);
		
		//g2d.dispose();
		

		
	}

}
