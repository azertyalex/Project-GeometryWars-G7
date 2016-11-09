package be.howest.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;
import be.howest.objects.Lazer;
import be.howest.util.GameUtils;

public class Mouse extends MouseAdapter{
	private int x,y;
	public int mouseX;
	public int mouseY;
	private Handler handler;
	private GameObject gameObject;
	private static float rotation;

	
	public Mouse(Handler handler){
		this.handler = handler;
		if(gameObject != null){
			gameObject = handler.getGameObject(ID.Player);
		}
		
		
	}

	public Mouse(Handler handler, GameObject gameObject) {
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
		
		
		
		return  (float)(-Math.toDegrees(
											Math.atan2(
													x,
													y)
											)
										+0);
	}
	
	public static float getRotation(){
		
		return rotation;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		handler.addObject(new Lazer(gameObject.getCenterX(),gameObject.getY() + gameObject.getObjectHeight()/2,10,10,ID.Enemy,gameObject,handler,rotation,this));
		System.out.println(rotation);
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
		rotation = getRotation(e);
	}
	


	
	
	
}
