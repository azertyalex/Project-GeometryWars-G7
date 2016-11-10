package be.howest.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import be.howest.game.Game;
import be.howest.game.Game.STATE;
import be.howest.objects.testObject;
import be.howest.util.GameUtils;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.InputHandler;

public class PowerShop extends InputHandler implements UserInterface{
	public Rectangle next = new Rectangle(Game.WIDTH / 2 - 250, 800, 500, 75);

	private Game game;
	private Handler handler;
	
	public PowerShop(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
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
		g.drawString("POWER SHOP", 49, 125);

		g.drawImage(GameUtils.loadImage("C:\\Users\\Quinten\\Documents\\GitHub\\Project-GeometryWars-G7\\resources\\UI\\PowerShop.png"), 50, 135, null);
		g.drawImage(GameUtils.loadImage("C:\\Users\\Quinten\\Documents\\GitHub\\Project-GeometryWars-G7\\resources\\Power\\Heal.png"), 120, 150, 150, 120, null);

		
		//UI - button
		Font smaller_title = new Font("Arial", Font.BOLD, 30);
		g.setFont(smaller_title);
		g.setColor(Color.white);
		
		g.drawString("Heart", 80, 300);
		
		g.setColor(Color.red);
		g2d.fill(next);
		g.setColor(Color.black);
		g.drawString("Continue", Game.WIDTH / 2 - 30, 850);
		
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

	@Override
	public void mouseAction(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		
		System.out.println("POWER SHOP: " + mx);
		System.out.println("POWER SHOP: " + my);
		
		//(Game.WIDTH / 2 - 250, 800, 500, 75);
		if (mouseOver(mx, my, (Game.WIDTH / 2) -250, 800, 500, 75)){
			System.out.println("PLAY GAME");
			game.state = STATE.PLAY;
			//handler.addObject(new testObject(200,200,ID.Player2));
		}else if (mouseOver(mx, my, 70, 150, 250, 300)){
			System.out.println("POWER1");
			//buyPower(Game.getPower("heal"));
		}else if (mouseOver(mx, my, 360, 150, 250, 300)){
			System.out.println("POWER2");
			//buyPower(Game.getPower("slow_time"));
		}else if (mouseOver(mx, my, 650, 150, 250, 300)){
			System.out.println("POWER3");
			//buyPower(Game.getPower("clone"));
		}else if (mouseOver(mx, my, 950, 150, 250, 300)){
			System.out.println("POWER4");
			//buyPower(Game.getPower("multiple_cannons"));
		}else if (mouseOver(mx, my, 70, 150, 250, 300)){
			System.out.println("POWER5");
			//buyPower(Game.getPower("barrier"));
		}		
	}
	/*
	private void buyPower(Power power){
		if (player.getBoughtPower == null){
			player.setBoughtPower(power);
		} else {
			System.out.println("Player already has power");
		}
	}*/
}
