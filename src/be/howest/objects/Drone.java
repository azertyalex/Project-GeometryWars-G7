package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import be.howest.game.Handler;
import be.howest.game.ID;

public class Drone extends GameObject{

	private GameObject player;

	public Drone(int x, int y,int height, int width, ID id, Handler handler){
		super(x,y,height,width,id,handler);
	}
	
	
	public Drone(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		

		
		
		
		//velX = 5;
		//velY = 5;
	}
	

	public Rectangle getBounds(){
		return new Rectangle(x,y,16,16);
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
		
		float norm = (float) Math.sqrt(diffX * diffX + diffY * diffY);
		
		
		velX = (int) ((norm)*diffX)/1000;
		velY = (int) ((norm)*diffY)/1000;
		//System.out.println(norm);
			
		
	}

	
	public void render(Graphics g) {
				g.setColor(Color.GREEN);
				g.fillOval(x, y, 16, 16);
	}


}
