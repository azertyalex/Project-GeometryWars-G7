package be.howest.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.util.GameUtils;

public class MineLayer extends GameObject{
	
	
	private int timer = 150;
	private BufferedImage imgMineLayer = GameUtils.loadImage("/Enemy/Weaver.png");
	
	public MineLayer(int x, int y,int height, int width ,ID id, Handler handler) {
		super(x,y,height,width,id,handler);
		velX = 4;
		velY = 1;  
	}
	
	public MineLayer(int x, int y,int height, int width, ID id, ID parentId, Handler handler){
		this(x,y,height,width,id,handler);
		this.parentId = parentId;
		setColor(Color.green);
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
		g.drawImage(imgMineLayer, x, y, objectWidth, objectHeight,null);
	}
	
}
