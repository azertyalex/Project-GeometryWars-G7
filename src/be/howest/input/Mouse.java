package be.howest.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;
import be.howest.util.GameUtils;

public class Mouse extends MouseAdapter{
	private int x,y;
	private Handler handler;
	private GameObject gameObject;
	private static float rotation;

	
	public Mouse(Handler handler){
		this.handler = handler;
		if(gameObject != null){
			gameObject = handler.getGameObject(ID.Player);
		}
		
		System.out.println(gameObject.getCenterX());
	}

	public Mouse(Handler handler, GameObject gameObject) {
		this.handler = handler;
		this.gameObject = gameObject;
	}

	@Override
	public void mouseMoved(MouseEvent e){
		//System.out.println(e.getX());


		
		
		System.out.println(gameObject.getCenterX());
		x = gameObject.getCenterX() - e.getX();
		y = gameObject.getCenterY() - e.getY();
		
		
		
		rotation =  (float)(-Math.toDegrees(
											Math.atan2(
													x,
													y)
											)
										+0);
		
	}
	
	public static float getRotation(){
		return rotation;
	}
	


	
	
	
}
