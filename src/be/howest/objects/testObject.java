package be.howest.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.*;
import be.howest.objects.enemies.Wanderer;
import be.howest.util.GameUtils;

public class testObject extends GameObject{
	private Gamepad gamepad;
	//private PlayerShoot mouse;
	private int timer = 20;
	private Random random = new Random();
	
	private int score;
	private Graphics2D g2d;
	
	private BufferedImage imgPlayer = GameUtils.loadImage("/images/Player_Standard/Player.png");
	
	public testObject(int x,int y, ID id){
		super(x,y,id);
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,objectWidth, objectHeight);
	}
	
	public testObject(int x,int y,int height, int width, ID id){
		super(x,y,height,width,id,null);
		
	}
	
	public testObject(int x,int y,int height, int width, ID id, Handler handler, int health){
		super(x,y,height,width,id,handler);
		setHealth(health);
		setSpeed(10);
	}
	
	public testObject(int x,int y,int height, int width, ID id, Handler handler,boolean controller, int health){
		super(x,y,height,width,id,handler,controller);
		setHealth(health);
		if(controller) gamepad = new Gamepad();
		setSpeed(10);
		
	}
	
	public testObject(int x,int y,int height, int width, ID id, Handler handler,Gamepad gamepad, int health){
		super(x,y,height,width,id,handler,true);
		setHealth(health);
		this.gamepad = gamepad;
		setSpeed(10);
	}
	
	public testObject(int x, int y,int height, int width, ID id, ID parentId, Handler handler,Gamepad gamepad, int health){
		super(x,y,height,width,id,parentId,handler,true);
		this.gamepad = gamepad;
		setHealth(health);
		setSpeed(10);
	}
	
	public testObject(int x, int y,int height, int width, ID id, ID parentId, Handler handler, int health) {
		super(x,y,height,width,id,parentId,handler,false);
		setHealth(health);
		setSpeed(10);
	}

	public boolean isControllerActive(){
		return gamepad.ControllerActive();
	}

	@Override
	public void tick() {
		x = (int) (GameUtils.clamp(x, 0, Game.WIDTH - objectWidth) + velX);
		y = (int) (GameUtils.clamp(y, 0, Game.HEIGHT - objectHeight) + velY);
		
		Collision();
		
		
		if(controller){
			gamepad.turnOnController();
			if(gamepad.getDPad() == 0.25) speed++;
			if(gamepad.getDPad() == 0.75) speed--;	
			if(gamepad.getButton(0)){
				int w = random.nextInt(Game.WIDTH - 50);
				int h = random.nextInt(Game.HEIGHT - 50);
				
				handler.addObject(new Wanderer(w,h,50,50,ID.Wanderer,ID.Enemy,handler));
			}
			if( timer == 0){
				if(gamepad.getButton(5)){
					handler.addObject(new Laser(50,10,ID.Laser,(GameObject) this,handler,gamepad.getRotationR()));
					
				}
				timer = 10;
			}
			
			velX = (int) (gamepad.getX() * speed * 1.1);
			velY = (int) (gamepad.getY() * speed * 1.1);
		}
		
		timer--;
		
	}
	
	private void Collision(){
		for(int i=0; i < handler.getList().size();i++){
			GameObject tempObject = handler.getList().get(i);
			
			if (tempObject.getParentId() == ID.Enemy){
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("Collision detected");
					handler.removeObject(tempObject);
					if(this.health>0){
						this.setHealth(health-1);
						this.setScore(score + 100);
					}
					else{
						System.out.println("Game Over");
					}
				}
			}
			
			/*
			if (tempObject.getId() == ID.Wanderer){
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("Collision detected");
					handler.removeObject(tempObject);
					if(this.health>0){
						this.setHealth(health-1);
						this.setScore(score + 10);
					}
					else{
						System.out.println("Game Over");
					}
				}
			}
			if (tempObject.getId() == ID.Grunt){
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("Collision detected");
					handler.removeObject(tempObject);
					if(this.health>0){
						this.setHealth(health-1);
					}
					else{
						System.out.println("Game Over");
					}
				}
			}
			if(tempObject.getId() == id.Mine){
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("Collision detected");
					handler.removeObject(tempObject);
					if(this.health>0){
						this.setHealth(health-1);
					}
					else{
						System.out.println("Game Over");
					}
				}
			}
			if(tempObject.getId()==id.Dart){
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("Collision detected");
					handler.removeObject(tempObject);
					if(this.health>0){
						this.setHealth(health-1);
					}
					else{
						System.out.println("Game Over");
					}
				}
			}
			if(tempObject.getId()==id.MineLayer){
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("Collision detected");
					handler.removeObject(tempObject);
					if(this.health>0){
						this.setHealth(health-1);
					}
					else{
						System.out.println("Game Over");
					}
				}
			}
			*/
			
		}
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		
		float rotation;
		if(controller){
			rotation = gamepad.getRotationR();
		}else{
			rotation = PlayerShoot.getRotation();
		}



		
		g2d.rotate(Math.toRadians(rotation), getCenterX(), getCenterY());
		g2d.drawImage(imgPlayer, x, y, objectWidth, objectHeight,null);

		
		Color c = new Color(1f,0f,0f,0f);
		
		g2d.setColor(c);
		g2d.draw(getBounds());	
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
