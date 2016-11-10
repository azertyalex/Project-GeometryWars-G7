package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.util.GameUtils;

public class MineLayer extends GameObject{
	
	
	private int timer = 150;

	public MineLayer(int x, int y,int height, int width ,ID id, Handler handler) {
		super(x,y,height,width,id,handler);
		velX = 4;
		velY = 1;
		
        
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
			handler.addObject(new Mine(x,y,10,10,ID.Mine,handler));
			timer = 150;
		}
		
		timer --;
        
		
        
	}

	
	public void render(Graphics g) {
		g.drawImage(GameUtils.loadImage("resources\\Enemy\\Weaver.png"), x, y, objectWidth, objectHeight,null);
	}
	
}