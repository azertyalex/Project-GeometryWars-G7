package be.howest.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import be.howest.game.Handler;


public class Hud {

	
	
	private int health;
	
	Handler handler;
	
	public Hud(){
		
	}
	
	public void tick(){
		
		
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.WHITE);
		Font myFont = new Font ("STARWARS", 1, 50);
		g.setFont (myFont);
		g.drawString("" + health, 30, 50);
	}
	
	public void setHudHealth(int health){
		this.health = health;
	}
	
}
