package be.howest.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.PlayerShoot;
import be.howest.objects.particles.Particle;
import be.howest.util.Animation;
import be.howest.util.GameUtils;
import be.howest.util.Sprite;
import sun.audio.AudioPlayer;

public class Laser extends GameObject{
	private Graphics2D g2d;
	private float rotation;
	private int timer = 100;
	private BufferedImage image = GameUtils.loadImage("/Other/Laser.png");
	private BufferedImage[] explosion = {Sprite.getSprite(0, 0), Sprite.getSprite(1, 0), Sprite.getSprite(2, 0),Sprite.getSprite(3, 0),Sprite.getSprite(4, 0), Sprite.getSprite(5, 0)};
	private Animation explAnimation;
	private boolean startExplosion = false;
	private int EnemyXCord = 0, EnemyYCord = 0;
	
	public Laser(int x, int y, ID id,float rotation,PlayerShoot playerShoot) {
		super(x, y, id);
		this.rotation = rotation;
		
	}
	
	public Laser(int height, int width, ID id,GameObject player, Handler handler,float rotation){
		this(player.getCenterX() - width/2 ,player.getCenterY() - height/2,height,width,id,handler,rotation);
	}
	
	private Laser(int x, int y,int height, int width, ID id, Handler handler,float rotation){
		super(x,y,height,width,id,handler);
		this.rotation = (float) Math.toRadians(rotation);
		int r = 90;
		velY = (int) ( 15*Math.sin(Math.toRadians(rotation-r)));
        velX = (int) (15*Math.cos( Math.toRadians(rotation-r)));
        
        //AudioPlayer.player.start(GameUtils.loadSound("resources/sound/laser2.wav"));
		
	}

	@Override
	public void tick() {
		x += (int) velX;
		y += (int) velY;

		//velY = -1;
		System.out.println("LASER TICK");
	    if (startExplosion){
	    	explAnimation.tick();
	    }
		if(timer == 0){
			handler.removeObject(this);
		}
		timer--;

		for(int i=0; i < handler.getList().size();i++){
			GameObject tempObject = handler.getList().get(i);
			if (tempObject.getParentId() == ID.Enemy){
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("Collision detected");
					explAnimation = new Animation(tempObject.getCenterX(), tempObject.getCenterY(), 50,50, ID.Particle, handler, explosion, 5);
					handler.addObject(explAnimation);
					explAnimation.start();
					handler.addObject(new Particle(tempObject.getCenterX(),tempObject.getCenterY(),tempObject.getColor(),this.rotation,handler)); //debris
					System.out.println("SHOT");
					startExplosion  = true;

					handler.removeObject(tempObject); //enemy
					handler.removeObject(this); //laser 
				}
			}
		}
		


		
	}

	@Override
	public void render(Graphics g) {

		g2d = (Graphics2D) g;
		//g2d.setColor(Color.red);
		
		g2d.rotate(rotation, getCenterX(),getCenterY());
		g2d.drawImage(image, x, y, objectWidth, objectHeight,null);
		//g2d.fillRect(x, y,objectWidth,objectHeight);
		
	    java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(getCenterX(), getCenterY());
	    float radius = 65;
	    float[] dist = {0.0f, 0.9f};
	    Color[] colors = {Color.red, new Color(0.0f, 0.0f, 0.0f, 0.0f)};

	    
	    RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
	    g2d.setPaint(p);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f));
	    g2d.fillRect(0, 0, Game.WIDTH, Game.WIDTH);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	    
		//explAnimation.update();
	    if (startExplosion){
	    	System.out.println("Draw Explosion");
		    g.drawImage(explAnimation.getSprite(), EnemyXCord, EnemyYCord, null);
	    }
	}

	@Override
	public Rectangle getBounds(){
		return new Rectangle(x,y,objectWidth,objectHeight);
	}

}
