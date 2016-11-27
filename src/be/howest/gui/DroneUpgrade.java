package be.howest.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import be.howest.game.Game;
import be.howest.game.Game.STATE;
import be.howest.util.GameUtils;
import be.howest.util.GraphicUtils;
import be.howest.game.Handler;
import be.howest.input.InputHandler;

public class DroneUpgrade extends InputHandler implements UserInterface{
	public Rectangle next = new Rectangle(Game.WIDTH / 2 - 250, 825, 500, 75);

	private Game game;
	private BufferedImage imgDroneInfo = GameUtils.loadImage("/images/UI/Other/Drone_Information.png");
	
	public DroneUpgrade(Game game, Handler handler, PowerShop powerShop){
		this.game = game;
		}
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		
		GraphicUtils.setTitleFont();
		GraphicUtils.changeColor(Color.white);
		GraphicUtils.drawText("INTERMISSION", 75, 50, g);
		GraphicUtils.changeColor(Color.red);
		GraphicUtils.drawText("DRONE UPGRADE", 125, 50, g);
		GraphicUtils.resetColor();

		g.drawImage(imgDroneInfo, Game.WIDTH / 2 - 250, 145, null);
		
		GraphicUtils.setSmallTitleFont();
		String droneName = (null == null) ? "Drone_Not_Found" : "Handler.getFromList(ID.Drone).getName()";
		GraphicUtils.drawText(droneName, 220, g);
		
		GraphicUtils.drawText("Level 1", 395, 425, g);
		GraphicUtils.drawText("Level 2", 545, 425, g);
		GraphicUtils.drawText("Level 3", 690, 425, g);


		GraphicUtils.setSmallFont();
		GraphicUtils.drawText("Unlocked", 460, 425, g);
		GraphicUtils.drawText("2 2550 XP", 610, 425, g);
		GraphicUtils.drawText("10 000 XP", 755, 425, g);

		//UI - BAR
		g2d.setColor(Color.GRAY);
		g2d.fill(new Rectangle(Game.WIDTH / 2 - 219, 467, 440, 10));
		g2d.fill(new Rectangle(Game.WIDTH / 2 - 219, 614, 440, 10));
		g2d.fill(new Rectangle(Game.WIDTH / 2 - 219, 761, 440, 10));
		//UI - XP
		g2d.setColor(Color.RED);
		g2d.fill(new Rectangle(Game.WIDTH / 2 - 219, 467, 440, 10));
		g2d.fill(new Rectangle(Game.WIDTH / 2 - 219, 614, 300, 10));
		g2d.fill(new Rectangle(Game.WIDTH / 2 - 219, 761, 0, 10));
		

		GraphicUtils.setNormalFont();
		GraphicUtils.drawText("getDescription1", 415, 425, g);
		GraphicUtils.drawText("getDescription2", 565, 425, g);
		GraphicUtils.drawText("getDescription3", 710, 425, g);
		
		//UI - button
		g2d.fill(next);
		GraphicUtils.setSmallTitleFont();
		GraphicUtils.changeColor(Color.black);
		GraphicUtils.drawText("Next",875,g);
		
		//BufferedImage img = GameUtils.loadImage("resources\\Player_Custom\\" + droneName + ".png");

		//g.drawImage(img,Game.WIDTH / 2 - 30, 245,90,60, null);
		}
	
	public void tick(){
		
	}
	
	private boolean mouseOver(int mx, int my, double x, double y, double width, double height){
		if (mx > x && mx < x + width){
			if (my > y && my < y + height){
				return true;
			} else return false;
		} else return  false;
	}

	public void mouseAction(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		
		System.out.println("DRONE: " + mx);
		System.out.println("DRONE: " + my);

		if (mouseOver(mx, my, (Game.WIDTH / 2) -250, 800, 500, 75)){
			System.out.println("GA NAAR POWER SHOP");
			game.setState(STATE.POWER_SHOP);
		}
	}
}
