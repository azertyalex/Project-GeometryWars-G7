package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.PlayerShoot;
import be.howest.objects.particles.Particle;
import be.howest.util.GameUtils;
import sun.audio.AudioPlayer;

public class Laser extends GameObject{
	private Graphics2D g2d;
	private float rotation;
	private int timer = 100;
	private BufferedImage image = GameUtils.loadImage("/Other/Laser.png");
	
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
		
		if(timer == 0){
			handler.removeObject(this);
		}
		timer--;
		
		for(int i=0; i < handler.getList().size();i++){
			GameObject tempObject = handler.getList().get(i);
			if (tempObject.getParentId() == ID.Enemy){
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("Collision detected");
					handler.addObject(new Particle(tempObject.getCenterX(),tempObject.getCenterY(),tempObject.getColor(),this.rotation,handler));
					handler.removeObject(tempObject);
					handler.removeObject(this);
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
		
		
	}

	@Override
	public Rectangle getBounds(){
		return new Rectangle(x,y,objectWidth,objectHeight);
	}

}
