package be.howest.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

import be.howest.game.ID;
import be.howest.input.*;
import be.howest.util.GameUtils;

public class testObject extends GameObject{
	private Gamepad gamePad = new Gamepad();

	private Graphics2D g2d;
	
	
	public testObject(int x,int y, ID id){
		super(x,y,id);
	}
	
	public testObject(int x,int y,int height, int width, ID id){
		super(x,y,height,width,id,null);
	}

	@Override
	public void tick() {
		
		
		x += velX;
		y += velY;
		
		gamePad.turnOnController();
		setHeight(100);
		setWidth(100);
		
		if(gamePad.getDPad() == 0.25) speed++;
		if(gamePad.getDPad() == 0.75) speed--;		
		
		velX = (int) (gamePad.getX() * speed * 1.1);
		velY = (int) (gamePad.getY() * speed * 1.1);
		
		System.out.println(gamePad.getRotationR());

		//i = GameUtils.clamp((int)(-Math.toDegrees( (Math.atan2(test.getMouseX(),test.getMouseY())+180))),0,360);
		
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		
		g2d.rotate(Math.toRadians(gamePad.getRotationR()), getCenterX(), getCenterY());
		g2d.drawImage(GameUtils.loadImage("resources\\player\\player.png"), x, y, objectWidth, objectHeight,null);

		
	}

}
