package be.howest.objects;


import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.util.GameLoop;

public abstract class GameObject implements GameLoop{
	protected ID id;
	protected int x, y;
	protected int velX, velY;
	protected Handler handler;
	protected int speed;
	protected int objectHeight;
	protected int objectWidth;
	protected int health;
	protected boolean controller;
	

	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	
	public GameObject(int x, int y,int height, int width, ID id){
		this(x,y,id);
		this.objectHeight = height;
		this.objectWidth = width;
	}
	
	public GameObject(int x, int y,int height, int width, ID id,boolean controller){
		this(x,y,height,width,id);
		this.controller = controller;
	}
	
	public GameObject(int x, int y,int height, int width, ID id, Handler handler){
		this(x,y,height,width,id);
		this.handler = handler;
	}
	
	public GameObject(int x, int y,int height, int width, ID id, Handler handler, boolean controller){
		this(x,y,height,width,id,handler);
		this.controller = controller;
	}
	
	public void setHeight(int height){
		this.objectHeight = height;
	}
	
	public void setWidth(int width){
		this.objectWidth = width;
	}
	
	public int getCenterX(){
		return (x + objectWidth / 2);
	}
	
	public int getCenterY(){
		return (y + objectHeight / 2);
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getObjectHeight() {
		return objectHeight;
	}

	public void setObjectHeight(int objectHeight) {
		this.objectHeight = objectHeight;
	}
	
	public int getObjectWidth() {
		return objectWidth;
	}

	public void setObjectWidth(int objectWidth) {
		this.objectWidth = objectWidth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}


	public boolean isController() {
		return controller;
	}


	public void setController(boolean controller) {
		this.controller = controller;
	}
	
	
}
