package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;

public class Dart extends GameObject{
	

	private GameObject player;

	public Dart(int x, int y,int height, int width, ID id, Handler handler){
		super(x,y,height,width,id,handler);
		

	}
	
	
	public Dart(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		

		//velX = 5;
		//velY = 5;
	}
	

	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
	}
	
	public void tick() {
		y=y+velY;
		x=x+velX;
		
		for(int i=0; i<handler.getList().size();i++){
			
			if(handler.getList().get(i).getId()== ID.Player2){
				player = handler.getList().get(i);
				//System.out.println(player);
			}
		}
        
		
		int moveToX = player.getCenterX();
		int moveToY = player.getCenterY();
		
		float diffX =moveToX- x;
		float diffY =moveToY- y;
		
		
		
		double angle = Math.atan2(diffY,diffX);  
		if (y<=0||y>=Game.HEIGHT-64){
		
			velY = (int) (5 * Math.sin( angle ));
			velX = (int) (5 * Math.cos( angle ));
			
			
		}
		if (x<=0||x>=Game.WIDTH-32){
			velX = (int) (5 * Math.cos( angle ));
			velY = (int) (5 * Math.sin( angle ));
			
		}
		//System.out.println(norm);
		
		
		//System.out.println(velX + " , " + velY);
			
		/**
		if(y<=0||y>=Game.HEIGHT -16){
			velY *= (int) (5 * Math.cos( angle ));
		}
		if(x<=0||x>=Game.WIDTH - 16){
			velX *= (int) (5 * Math.sin( angle ));
		}
		
		**/
		
		
	}

	
	public void render(Graphics g) {
				g.setColor(Color.RED);
				g.fillRect(x, y, 32, 32);
	}

}
