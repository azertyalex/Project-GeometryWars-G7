package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import be.howest.game.Game;
import be.howest.game.ID;

public class Dart extends GameObject{
	
	
	
	
	
	public Dart(int x, int y, ID id) {
		super(x, y, id);
		
		velX = 5;
		velY = 5;
	}

	public Rectangle getBounds(){
		return new Rectangle(x,y,16,16);
	}
	
	public void tick() {
		x+= velX;
		y+= velY;
		
		//float diffX = x - player.getX();
		//float diffY = y - player.getY();
		//float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY()));
		
		//velX = (int) ((-1.0/distance)*diffX);
		//velY = (int) ((-1.0/distance)*diffY);
		//velX = (int)player.getX();
		//velY = (int)player.getY();
		
		if(y<=0||y>=Game.HEIGHT -32){
			velY *= -1;
		}
		if(x<=0||x>=Game.WIDTH - 32){
			velX *= -1;
		}
		
		
	}

	
	public void render(Graphics g) {
				g.setColor(Color.GREEN);
				g.fillRect(x, y, 16, 16);
	}

}
