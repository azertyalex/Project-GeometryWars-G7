package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.util.GameUtils;

public class testShield extends GameObject{
	private GameObject player;
	private BufferedImage image = GameUtils.loadImage("resources/Power/Barrier.png");

	public testShield(int x, int y, int height, int width, ID id, Handler handler) {
		super(x, y, height, width, id, handler);
		
	}

	@Override
	public void tick() {
		for(int i=0; i < handler.getList().size();i++){
			GameObject tempObject = handler.getList().get(i);
			//System.out.println("In LOOP");
			if(tempObject.getParentId() == ID.Player){
				x = tempObject.getX();
				y = tempObject.getY();
				player = tempObject;
			}
			
			if (tempObject.getId() == ID.Lazer){
				System.out.println("In IF");
				if(getBounds().intersects(tempObject.getBounds())){
					//System.out.println("Collision detected");
					float tempVelX = -tempObject.velX;
					float tempVelY = -tempObject.velY;
					//System.out.println(tempVelX + "||" + tempObject.velX);
					tempObject.setVelX(tempVelX);
					tempObject.setVelY(tempVelY);
				}
			}
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(image,x-10,y-10,objectHeight + 20,objectWidth+20,null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x-10,y-10,objectHeight + 20,objectWidth+20);
	}

}
