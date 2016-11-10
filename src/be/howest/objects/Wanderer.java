package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.util.GameUtils;

public class Wanderer extends GameObject{
	
	public Wanderer(int x, int y, ID id) {
		super(x, y, id);
		velX = 5;
		velY = 5;
	}
	
	public Wanderer(int x, int y,int height, int width, ID id){
		super(x,y,height,width,id);
		
		velX = 5;
		velY = 5;
	}

	public Rectangle getBounds(){
		return new Rectangle(x,y,objectWidth-4,objectHeight-4);
	}
	
	public void tick() {
		
		x+= velX;
		y+= velY;
		
		if(y<=0||y>=Game.HEIGHT -objectHeight-32){
			velY *= -1;
		}
		if(x<=0||x>=Game.WIDTH - objectWidth){
			velX *= -1;
		}
	}

	
	public void render(Graphics g) {
		g.drawImage(GameUtils.loadImage("resources\\Enemy\\Wanderer4.png"), x, y, objectWidth, objectHeight,null);
	}

}
