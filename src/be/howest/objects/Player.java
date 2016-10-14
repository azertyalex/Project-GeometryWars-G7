package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;

import be.howest.game.Gamepad;
import be.howest.game.ID;
import be.howest.interfaces.GameObject;

public class Player extends GameObject{
	
	public Player(int x, int y, ID id){
		super(x,y,id);
		
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		Gamepad gp = new Gamepad(0);
		gp.turnOnController();
		
		int val = 10;
		
		velX = (int) (gp.getX() * val);
		velY = (int) (gp.getY() * val);
		System.out.println(gp.getZ() + " || " + gp.getRZ());

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
		
	}
}
