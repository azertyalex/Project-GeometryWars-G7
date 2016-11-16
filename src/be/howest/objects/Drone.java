package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.util.GameUtils;

public class Drone extends GameObject{

	private GameObject player;
	private GameObject tempObject;
	private BufferedImage imgDrone = GameUtils.loadImage("resources\\Drone\\SentryDroneWithTurret.png");
	private float rotation;
	private int timer;
	
	public Drone(int x, int y,int height, int width, ID id, Handler handler){
		super(x,y,height,width,id,handler);
	}
	
	
	public Drone(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
	}
	
	@Override
	public Rectangle getBounds(){
		return new Rectangle(x,y,objectWidth,objectHeight);
	}
	
	@Override
	public void tick() {
		x+= velX;
		y+= velY;
		
		int dis = 1;
		int newDis = dis;
		
        for(int i=0; i<handler.getList().size();i++){
			
			if(handler.getList().get(i).getId()== ID.Player2){
				player = handler.getList().get(i);
				//System.out.println(player);
				//tempObject = handler.getList().get(i);
			}
			
			if(handler.getList().get(i).getParentId() == ID.Enemy){
				if(dis == 1) newDis = (int) GameUtils.distance(this, handler.getList().get(i));
				if(dis <= newDis){
					newDis = (int) GameUtils.distance(this, handler.getList().get(i));
					tempObject = handler.getList().get(i);
					
				}
				
				dis = newDis;
			}
		}
        
        if(timer <= 0 && handler.hasEnemies()){
        	timer = 100;
        	handler.addObject(new Lazer(25,5,ID.Lazer,this,handler,getRotation()));
        }
        
        timer--;
        
        
        
		
        
		int moveToX = player.getCenterX();
		int moveToY = player.getCenterY();
		
		float diffX =moveToX - this.getCenterX() ;
		float diffY =moveToY - this.getCenterY() ;
		
		float norm = (float) Math.sqrt(diffX * diffX + diffY * diffY);
		
		
		velX = (int) ((norm)*diffX)/1000;
		velY = (int) ((norm)*diffY)/1000;
		//System.out.println(norm);
		
		rotation = getRotation();
			
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(Math.toRadians(rotation+180),this.getCenterX(),this.getCenterY());
		g2d.drawImage(imgDrone, x, y, objectWidth, objectHeight,null);

	}
	
	private float getRotation(){
		int enemyX;
		int enemyY;
		if(handler.hasEnemies()){
			enemyX = tempObject.getCenterX();
			enemyY = tempObject.getCenterY();
		}else{
			enemyX = player.getCenterX();
			enemyY = player.getCenterY();
		}

		int tempx = this.getCenterX() - enemyX;
		int tempy = this.getCenterY() - enemyY;
		
		float rotation = (float)(-Math.toDegrees(
				Math.atan2(
						tempx,
						tempy)
				)
			);
		
		
		System.out.println(rotation);
		return rotation;
	}


}
