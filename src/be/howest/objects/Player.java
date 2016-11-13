package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.Gamepad;

public class Player extends GameObject{
	private int val = 1;
	private Gamepad gamePad =  new Gamepad();
	
	
	public Player(int x, int y, ID id){
		super(x,y,id);
	}
	
	public Player(int x, int y,int height, int width, ID id, ID parentId, Handler handler){
		super(x,y,height,width,id,parentId,handler);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
	}


	@Override
	public void tick() {
		x += velX;
		y += velY;
	

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
		
	}


}
