package be.howest.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;
import be.howest.util.GameUtils;


public class Hud {

	
	
	private int health;
	private int score;
	
	
	public Hud(){
		
	}
	
	public void tick(){
		
		
	}
	
	public void render(Graphics g){
		g.drawImage(GameUtils.loadImage("resources\\Other\\Health.png"), 28, 20,65,50,null);
		g.setColor(Color.WHITE);
		Font myFont = new Font ("STARWARS", 1, 50);
		g.setFont (myFont);
		g.drawString("x"+health, 90, 60);
		
		g.drawString(""+score, 35, 120);
		
	}
	
	public void setHudHealth(int health){
		this.health = health;
	}
	
	public void setHudScore(int score){
		this.score = score;
	}
	
}
