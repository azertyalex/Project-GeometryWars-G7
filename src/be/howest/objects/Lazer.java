package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.Mouse;
import be.howest.util.GameUtils;
import be.howest.util.MyException;
import sun.audio.AudioPlayer;

public class Lazer extends GameObject{
	private Graphics2D g2d;
	private float rotation;
	private Mouse mouse;
	private GameObject player;
	private int timer = 100;
	

	public Lazer(int x, int y, ID id,float rotation,Mouse mouse) {
		super(x, y, id);
		this.rotation = rotation;
		this.mouse = mouse;
		
	}
	
	public Lazer(int height, int width, ID id,GameObject player, Handler handler,float rotation){
		this(player.getCenterX() - width ,player.getCenterY() - height/2,height,width,id,player,handler,rotation);
	}
	private Lazer(int x, int y,int height, int width, ID id,GameObject player, Handler handler,float rotation){
		super(x,y,height,width,id,handler);
		this.rotation = (float) Math.toRadians(rotation);
		this.player = player;
		int r = 90;
		velY = (int) ( 15*Math.sin(Math.toRadians(rotation-r)));
        velX = (int) (15*Math.cos( Math.toRadians(rotation-r)));
		try {
			InputStream lazerSound = new FileInputStream("resources\\sound\\laser2.wav");
			AudioPlayer.player.start(lazerSound);
			
		} catch (FileNotFoundException ex) {
			throw new MyException("Failed to load sound",ex);
		} 
		
	}
	
	public Lazer(int x, int y,int height, int width, ID id,GameObject player, Handler handler,float rotation,Mouse mouse){
		this(x,y,height,width,id,player,handler,rotation);
		this.mouse= mouse;
		
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
		


		
	}

	@Override
	public void render(Graphics g) {

		g2d = (Graphics2D) g;
		
		
		
		
		g2d.setColor(Color.red);
		
		g2d.rotate(rotation, getCenterX(),getCenterY());
		//g2d.drawImage(GameUtils.loadImage("resources\\player\\trump.png"), x, y, objectWidth, objectHeight,null);
		g2d.fillRect(x, y,objectWidth,objectHeight);
		
		
	}

}
