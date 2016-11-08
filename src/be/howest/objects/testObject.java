package be.howest.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

import be.howest.game.ID;
import be.howest.input.*;
import be.howest.util.GameUtils;

public class testObject extends GameObject{
	private Gamepad gamePad = new Gamepad();
	private KeyboardAndMouse test = new KeyboardAndMouse();
	private Graphics2D g2d;
	int val = 1;
	int i;
	
	private int objectHeight = 90;
	private int objectWidth = 90;
	
	
	public testObject(int x,int y, ID id){
		super(x,y,id);
	}

	@Override
	public void tick() {
		
		
		x += velX;
		y += velY;
		
		gamePad.turnOnController();
		
		if(gamePad.getDPad() == 0.25) val++;
		if(gamePad.getDPad() == 0.75) val--;		
		
		velX = (int) (gamePad.getX() * val * 1.1);
		velY = (int) (gamePad.getY() * val * 1.1);
		
		
		//Controller
		//i = GameUtils.clamp((int)(-Math.toDegrees( Math.atan2(gamePad.getRX(),gamePad.getRY()))+180),0,360);
		
		test.turnOn();
		
		//System.out.println(test.getMouseX());
		
		i = GameUtils.clamp((int)(-Math.toDegrees( (Math.atan2(test.getMouseX(),test.getMouseY())+180))),0,360);
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		
		g2d.rotate(Math.toRadians(i),x+ objectWidth / 2, y+ objectHeight / 2);
		g2d.drawImage(GameUtils.loadImage("resources\\player\\player.png"), x, y, objectWidth, objectHeight,null);
		
		
		
		
		
		
	}

}
