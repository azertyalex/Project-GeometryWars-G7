package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import be.howest.game.ID;
import be.howest.input.Gamepad;
import net.java.games.input.Rumbler;

public class Player extends GameObject{
	private int val = 1;
	private Gamepad gamePad =  new Gamepad();
	
	public Player(int x, int y, ID id){
		super(x,y,id);
		
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
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
		
		
	

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
		
	}


}
