package be.howest.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import be.howest.game.Game;
import be.howest.game.Game.STATE;
import be.howest.util.GameUtils;
import be.howest.game.Handler;
import be.howest.input.InputHandler;

public class DroneUpgrade extends InputHandler implements UserInterface{
	public Rectangle next = new Rectangle(Game.WIDTH / 2 - 250, 800, 500, 75);

	private Game game;
	private Handler handler;
	private PowerShop powerShop;
	
	
	public DroneUpgrade(Game game, Handler handler, PowerShop powerShop){
		this.game = game;
		this.handler = handler;
		this.powerShop = powerShop;
		}
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		
		//Big Title
		Font title = new Font("Arial", Font.BOLD,50);
		g.setFont(title);
		g.setColor(Color.white);
		g.drawString("INTERMISSION", 50, 75);
		g.setColor(Color.red);
		g.drawString("DRONE UPGRADE", 49, 125);

		//UI - Drone upgrade
		g.drawImage(GameUtils.loadImage("resources\\UI\\UI_Drone.png"), Game.WIDTH / 2 - 250, 175, null);
		
		//UI - Title
		Font smaller_title = new Font("Arial", Font.BOLD, 30);
		g.setFont(smaller_title);
		g.setColor(Color.white);
		g.drawString("Level 1", 400, 360);
		g.drawString("Level 2", 400, 510);
		g.drawString("Level 3", 400, 660);
		
		////String droneName = (Handler.getFromList(ID.Drone).getName() == null) ? "null" : Handler.getFromList(ID.Drone).getName();
		String droneName = (null == null) ? "Drone_Not_Found" : "Handler.getFromList(ID.Drone).getName()";

		g.drawString(droneName, 500, 210);

		//UI - Unlocked
		Font unlockedText = new Font("Arial", Font.LAYOUT_RIGHT_TO_LEFT, 30);
		g.setFont(smaller_title);
		g.setColor(Color.white);
		g.drawString("drone.levelUnlocked(1)", 550, 460);
		g.drawString("drone.levelUnlocked(2)", 550, 610);
		g.drawString("drone.levelUnlocked(3)", 550, 760);

		
		
		//UI - Text
		Font text = new Font("Arial",Font.PLAIN, 20);
		g.setFont(text);
		g.setColor(Color.white);
		//UI - Text - Level 1
		g.drawString("drone.getDescription(1)", 400, 380);
		//UI - Text - Level 2
		g.drawString("drone.getDescription(2)", 400, 530);
		//UI - Text - Level 3
		g.drawString("drone.getDescription(3)", 400, 680);

		
		
		//UI - button
		g2d.fill(next);
		g.setColor(Color.black);
		g.setFont(smaller_title);
		g.drawString("Next", Game.WIDTH / 2 - 30, 850);

		
		BufferedImage img = GameUtils.loadImage("resources\\player\\" + droneName + ".png");

		g.drawImage(img,Game.WIDTH / 2 - 30, 235,90,60, null);
		
		
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
			game.state = STATE.POWER_SHOP;
		}
	}
}