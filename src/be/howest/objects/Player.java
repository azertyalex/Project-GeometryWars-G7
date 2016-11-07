package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;

import be.howest.game.ID;
import be.howest.input.Gamepad;
import net.java.games.input.Rumbler;

public class Player extends GameObject{
	private int val = 1;
	private Gamepad gamePad =  new Gamepad();
	private boolean barrier = false;
	private int cannonAmount = 1;
	
	public Player(int x, int y, ID id){
		super(x,y,id);
		
		
	}
	
	public Player(int x, int y, ID id, int lives){
		super(x,y,id,lives);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
	
	
		gamePad.turnOnController();
		
		if(gamePad.getDPad() == 0.25) val++;
		if(gamePad.getDPad() == 0.75) val--;		
		
		velX = (int) (gamePad.getX() * val * 1.1);
		velY = (int) (gamePad.getY() * val * 1.1);

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
	
	


}
