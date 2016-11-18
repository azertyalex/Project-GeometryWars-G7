package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.Mouse;
import be.howest.util.GameUtils;
import sun.audio.AudioPlayer;

public class Lazer extends GameObject{
	private Graphics2D g2d;
	private float rotation;
	private int timer = 100;
	
	public Lazer(int x, int y, ID id,float rotation,Mouse mouse) {
		super(x, y, id);
		this.rotation = rotation;
		
	}
	
	public Lazer(int height, int width, ID id,GameObject player, Handler handler,float rotation){
		this(player.getCenterX() - width ,player.getCenterY() - height/2,height,width,id,player,handler,rotation);
	}
	private Lazer(int x, int y,int height, int width, ID id,GameObject player, Handler handler,float rotation){
		super(x,y,height,width,id,handler);
		this.rotation = (float) Math.toRadians(rotation);
		int r = 90;
		velY = (int) ( 15*Math.sin(Math.toRadians(rotation-r)));
        velX = (int) (15*Math.cos( Math.toRadians(rotation-r)));
        
        //AudioPlayer.player.start(GameUtils.loadSound("resources\\sound\\laser2.wav"));
		
	}
	
	public Lazer(int x, int y,int height, int width, ID id,GameObject player, Handler handler,float rotation,Mouse mouse){
		this(x,y,height,width,id,player,handler,rotation);
		float tempX = mouse.mouseX -player.getCenterX();
		if(tempX < 0 ) tempX = -tempX;
		float tempY = mouse.mouseY - player.getCenterY();
		if(tempY < 0 ) tempY = -tempY;
		float temp = GameUtils.min(tempX, tempY);
		temp = GameUtils.clamp(temp, 5F, 10F,10F);
		
		if (temp < 0 ) temp = -temp;
		velX = (mouse.mouseX -player.getCenterX())/temp;
		velY = (mouse.mouseY -player.getCenterY())/temp;
		
 
		
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
					handler.removeObject(tempObject);
					handler.removeObject(this);
				}
			}
		}
		


		
	}

	@Override
	public void render(Graphics g) {

		g2d = (Graphics2D) g;
		
		
		
		
		g2d.setColor(Color.red);
		
		g2d.rotate(rotation, getCenterX(),getCenterY());
		//g2d.drawImage(GameUtils.loadImage("resources\\player\\trump.png"), x, y, objectWidth, objectHeight,null);
		g2d.fillRect(x, y,objectWidth,objectHeight);
		
		
	}

	@Override
	public Rectangle getBounds(){
		return new Rectangle(x,y,objectWidth,objectHeight);
	}

}
