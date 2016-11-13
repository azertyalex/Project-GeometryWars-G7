package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.util.GameUtils;

public class Dart extends GameObject{
	private List<GameObject> players = new ArrayList<>();
	private GameObject player;
	
	private Random random = new Random();
	
	private BufferedImage imgDart = GameUtils.loadImage("resources\\Enemy\\Spinner.png");
	
	public Dart(int x, int y,int height, int width, ID id, Handler handler){
		super(x,y,height,width,id,handler);
		setSpeed(10);

	}
	
	public Dart(int x, int y,int height, int width, ID id, ID parentId, Handler handler){
		super(x,y,height,width,id,parentId,handler);
		setSpeed(10);
	}

	
	@Override
	public Rectangle getBounds(){
		return new Rectangle(x,y,objectWidth-4,objectHeight-4);
	}
	
	@Override
	public void tick() {
		y += velY;
		x += velX;
		
		
		for(int i=0; i<handler.getList().size();i++){
			
			if(handler.getList().get(i).getParentId() == ID.Player)	{
				players.add((GameObject)handler.getList().get(i));	
			}
		}
		
		player = players.get(random.nextInt(players.size()));
        
		
		
		int moveToX = player.getCenterX();
		int moveToY = player.getCenterY();
		
		float diffX =moveToX- x;
		float diffY =moveToY- y;
		
		
		
		double angle = Math.atan2(diffY,diffX);  
		if (y<=0||y>=Game.HEIGHT-(objectHeight+32)){
		
			velY = (int) (speed * Math.sin( angle ));
			velX = (int) (speed * Math.cos( angle ));
			
			
		}
		if (x<=0||x>=Game.WIDTH-objectWidth){
			velX = (int) (speed * Math.cos( angle ));
			velY = (int) (speed * Math.sin( angle ));
			
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

	@Override
	public void render(Graphics g) {
				//g.setColor(Color.RED);
				//g.fillRect(x, y, 32, 32);
		//g.rotate(Math.toRadians(rotation), getCenterX(), getCenterY());
		g.drawImage(imgDart, x, y, objectWidth, objectHeight,null);
		
		
	}

}
