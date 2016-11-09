package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import be.howest.game.ID;
import be.howest.input.Gamepad;
import be.howest.util.GameUtils;
import net.java.games.input.Rumbler;

public class Player extends GameObject{

	//private Gamepad gamePad =  new Gamepad();
	private boolean barrier = false;
	private boolean usePower = false;
	private int cannonAmount = 1;
	private Graphics2D g2d;
	int i;
	
	private int objectHeight = 90;
	private int objectWidth = 90;

	
	public Player(int x, int y, ID id){
		super(x,y,id);
		
		
	}
	
	public Player(int x, int y, ID id, int health){
		super(x,y,id);
		setHealth(health);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(x, y, 32, 32);
		
		g2d.rotate(Math.toRadians(i),x+ objectWidth / 2, y+ objectHeight / 2);
		g2d.drawImage(GameUtils.loadImage("resources\\player\\GuardianDrone.png"), x - (objectWidth / 2), y - (objectHeight / 2), objectWidth, objectHeight,null);
		
		if (this.isUsePower()){
			g2d = (Graphics2D) g;
			
			g2d.setColor(new Color(255,255,255));
			g2d.drawImage(GameUtils.loadImage("resources\\background\\Blackout.png"), x - (1920/2), y - (1080/2), 1920,1080,null);
			//g2d.rotate(Math.toRadians(i),x+ objectWidth / 2, y+ objectHeight / 2);
			//g2d.drawImage(GameUtils.loadImage("resources\\player\\GuardianDrone.png"), x - (objectWidth / 2), y - (objectHeight / 2), objectWidth, objectHeight,null);
			System.out.println("afbeelding toegevoegd");
		}
		
	}
	
	
	
	public boolean checkForExistingBarrier(){
		if(this.isBarrier()){
			return true;
		} else {
			return false;
		}
	}

	public boolean isBarrier() {
		return barrier;
	}

	public void setBarrier(boolean barrier) {
		this.barrier = barrier;
	}

	public int getCannonAmount() {
		return cannonAmount;
	}

	public void setCannonAmount(int cannonAmount) {
		this.cannonAmount = cannonAmount;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public boolean isUsePower() {
		return usePower;
	}

	public void setUsePower(boolean usePower) {
		this.usePower = usePower;
	}
	
	


}
