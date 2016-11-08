package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import be.howest.game.Game;
import be.howest.game.ID;

public class Wanderer extends GameObject{
	
	public Wanderer(int x, int y, ID id) {
		super(x, y, id);
		velX = 5;
		velY = 5;
	}

	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
	}
	
	public void tick() {
		x+= velX;
		y+= velY;
		
		if(y<=0||y>=Game.HEIGHT -64){
			velY *= -1;
		}
		if(x<=0||x>=Game.WIDTH - 32){
			velX *= -1;
		}
	}

	
	public void render(Graphics g) {
				g.setColor(Color.MAGENTA);
				g.fillRect(x, y, 32, 32);
	}

}
