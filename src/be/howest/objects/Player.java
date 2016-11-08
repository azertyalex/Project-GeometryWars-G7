package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;

import be.howest.game.ID;
import be.howest.input.Gamepad;
import net.java.games.input.Rumbler;

public class Player extends GameObject{
	private float speed = 1;
	//private Gamepad gamePad =  new Gamepad();
	private boolean barrier = false;
	private int cannonAmount = 1;
	
	public Player(int x, int y, ID id){
		super(x,y,id);
		
		
	}
	
	public Player(int x, int y, ID id, int health){
		super(x,y,id,health);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
	
	
		/* gamePad.turnOnController();
		
		if(gamePad.getDPad() == 0.25) speed++;
		if(gamePad.getDPad() == 0.75) speed--;		
		
		velX = (int) (gamePad.getX() * speed * 1.1);
		velY = (int) (gamePad.getY() * speed * 1.1);
		*/

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
		
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

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	
	


}
