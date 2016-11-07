package be.howest.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;
import be.howest.util.GameUtils;

public class Mouse extends MouseAdapter{
	
	private Handler handler;
	private float rotation;
	
	public Mouse(Handler handler){
		this.handler = handler;
	}

	@Override
	public void mouseMoved(MouseEvent e){
		rotation = getRotation(e);
		System.out.println(rotation);
		
		
	}
	
	public float getRotation(){
		//System.out.println(rotation);
		return rotation;
	}
	
	private float getRotation(MouseEvent e){
		int x = 0;
		int y = 0;
		
		for(int i= 0 ;i< handler.getList().size();i++){
			GameObject tempObject = handler.getList().get(i);
			
			
			if (tempObject.getId() == ID.Player){
				
				x = tempObject.getCenterX();
				y = tempObject.getCenterY();
				
				
				
				i = handler.getList().size() + 1;
			}
		}
		
		x = x - e.getX();
		y = y - e.getY();
		
		System.out.println(GameUtils.clamp((float)(Math.toDegrees(
				-Math.atan2(
						x,
						y)
				)
			+180),
0F,360F));
		
		return GameUtils.clamp((float)(Math.toDegrees(
				-Math.atan2(
						x,
						y)
				)
			+180),
0F,360F);
		
		

		
	}
}
