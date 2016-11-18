package be.howest.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.util.GameUtils;

public class Grunt extends GameObject{

	private GameObject player;
	private BufferedImage imgGrunt = GameUtils.loadImage("/Enemy/Grunt.png");

	public Grunt(int x, int y, ID id, Handler handler) {
		super(x, y, id);
	}
	
	public Grunt(int x, int y,int height, int width, ID id, Handler handler){
		super(x,y,height,width,id,handler);
	}
	
	public Grunt(int x, int y,int height, int width, ID id, ID parentId, Handler handler){
		super(x,y,height,width,id,parentId,handler);
	}
	
	

	

	public Rectangle getBounds(){
		return new Rectangle(x,y,objectWidth-4,objectHeight-4);
	}
	
	public void tick() {
		x+= velX;
		y+= velY;
		
        for(int i=0; i<handler.getList().size();i++){
			
			if(handler.getList().get(i).getId()== ID.Player2){
				player = handler.getList().get(i);
				//System.out.println(player);
			}
		}
		
        
		int moveToX = player.getCenterX();
		int moveToY = player.getCenterY();
		
		float diffX =moveToX - x;
		float diffY =moveToY - y;
		
		
		float speed = (float) (Math.random() * (4 - 3)) + 3;
		
		
		
		double angle = Math.atan2(diffY,diffX);  
		velX = (int) (speed * Math.cos( angle ));
		velY = (int) (speed * Math.sin( angle ));
		
		
		
		//System.out.println(velX + " , " + velY);
			
		
		if(y<=0||y>=Game.HEIGHT -objectHeight){
			velY *= (int) (5 * Math.cos( angle ));
		}
		if(x<=0||x>=Game.WIDTH - objectWidth){
			velX *= (int) (5 * Math.sin( angle ));
		}
		
		
		
		
	}

	
	public void render(Graphics g) {
		g.drawImage(imgGrunt, x, y, objectWidth, objectHeight,null);
	}

	
}
