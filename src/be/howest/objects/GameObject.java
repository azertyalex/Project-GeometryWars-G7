package be.howest.objects;


import be.howest.game.ID;
import be.howest.input.Inputdevice;
import be.howest.util.GameLoop;

public abstract class GameObject implements GameLoop{
	protected int x, y;
	protected ID id;
	protected int velX, velY;
	protected Inputdevice input;
	
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public GameObject(int x, int y, ID id, Inputdevice input){
		this.x = x;
		this.y = y;
		this.id = id;
		this.input = input;
	}
	
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setId(ID id){
		this.id = id;
	}
	
	public ID getId(){
		return id;
	}
	
	public void setVelX(int velX){
		this.velX = velX;
	}
	
	public void setVelY(int velY){
		this.velY = velY;
	}
	
	public int getVelX(){
		return velX;
	}
	
	public int getVelY(){
		return velY;
	}
	
	
}
