package be.howest.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;
import be.howest.objects.Laser;
import be.howest.util.GameUtils;
import be.howest.util.MyException;


public class PlayerShoot extends MouseAdapter{
	private int x,y;
	public int mouseX;
	public int mouseY;
	private Handler handler;
	private GameObject gameObject;
	private static float rotation;
	private boolean isPressed;

	
	public PlayerShoot(Handler handler){
		this.handler = handler;
		if(gameObject != null){
			gameObject = handler.getGameObject(ID.Player);
		}
		
		
	}
	

	public PlayerShoot(Handler handler, GameObject gameObject) {
		this.handler = handler;
		this.gameObject = gameObject;
	}

	@Override
	public void mouseMoved(MouseEvent e){
		rotation = getRotation(e);
		
	}
	private float getRotation(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();

		x = gameObject.getCenterX() - e.getX();
		y = gameObject.getCenterY() - e.getY();
		
		float rotation = (float)(-Math.toDegrees(
				Math.atan2(
						x,
						y)
				)
			+0);
		
		
		
		return rotation;
	}
	
	private void test(boolean pressed){

		//handler.addObject(new Laser(50,10,ID.Laser,(GameObject) this,handler,gamepad.getRotationR()));
		if(pressed) handler.addObject(new Laser(50,10,ID.Laser,gameObject,handler,rotation));
	}
	
	public static float getRotation(){
		
		return rotation;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		rotation = getRotation(e);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		rotation = getRotation(e);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		rotation = getRotation(e);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		isPressed = true;
		rotation = getRotation(e);
		
	    handler.addObjectInFront(new Laser(50,10,ID.Laser,gameObject,handler,rotation));

		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isPressed= false;
		rotation = getRotation(e);
		test(isPressed);
		
		
	}
	


	
	
	
}
