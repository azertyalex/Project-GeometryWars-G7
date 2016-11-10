package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import be.howest.game.Handler;
import be.howest.game.ID;

public class Mine extends GameObject{

	private int timer = 800;
	
	public Mine(int x, int y,int height, int width, ID id, Handler handler) {
		super(x, y, height, width, id, handler);
		
	}

	
	public void tick() {
		if(timer == 0){
			handler.removeObject(this);
			timer = 800;
		}
		timer--;
	}

	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 10, 10);
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle(x,y,10,10);
	}

	
}
