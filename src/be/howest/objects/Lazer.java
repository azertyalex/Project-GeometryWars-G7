package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import be.howest.game.Handler;
import be.howest.game.ID;

public class Lazer extends GameObject{
	private Graphics2D g2d;
	private float rotation;

	public Lazer(int x, int y, ID id,float rotation) {
		super(x, y, id);
		this.rotation = rotation;
		
	}
	
	public Lazer(int x, int y,int height, int width, ID id, Handler handler,float rotation){
		super(x,y,height,width,id,handler);
		this.rotation = rotation;
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		
		
		g2d.setColor(Color.red);
		g2d.drawRect(x, y,objectWidth,objectHeight);
		
		g2d.rotate(rotation, getCenterX(),getCenterY());
	}

}
