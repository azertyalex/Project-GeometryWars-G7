package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.util.GameUtils;

public class Wanderer extends GameObject{
	private int spinner;
	private BufferedImage imgWanderer = GameUtils.loadImage("resources\\Enemy\\Wanderer4.png");
	private int timer;
	
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

	public Wanderer(int x, int y, int height, int width, ID id, ID parentID, Handler handler) {
		this(x,y,height,width,id);
		this.parentId = parentID;
		this.handler = handler;
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
		
		if(timer == 0){
			spinner++;
			timer = 3;
		}
		
		timer--;
		
		if(spinner == 360){
			spinner = 0;
		}
	}

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(spinner,getCenterX(),getCenterY());
		g2d.drawImage(imgWanderer, x, y, objectWidth, objectHeight,null);
	}

}
