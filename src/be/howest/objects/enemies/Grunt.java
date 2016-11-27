package be.howest.objects.enemies;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;
import be.howest.util.GameUtils;

public class Grunt extends GameObject{

	private GameObject player;
	private BufferedImage imgGrunt = GameUtils.loadImage("/images/Enemy/Grunt.png");

	public Grunt(int x, int y, ID id, Handler handler) {
		super(x, y, id);
	}
	
	public Grunt(int x, int y,int height, int width, ID id, Handler handler){
		super(x,y,height,width,id,handler);
	}
	
	public Grunt(int x, int y,int height, int width, ID id, ID parentId, Handler handler){
		super(x,y,height,width,id,parentId,handler);
		setColor(Color.cyan);
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
		Graphics2D g2d = (Graphics2D) g;

		 java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(getCenterX(), getCenterY());
		    float radius = 20;
		    float[] dist = {0.0f, 0.7f};
		    Color[] colors = {Color.cyan, new Color(0.0f, 0.0f, 0.0f, 0.0f)};
		    RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
		    g2d.setPaint(p);
		    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		    g2d.fillRect(0, 0, Game.WIDTH, Game.WIDTH);
		    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	
}
