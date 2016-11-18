package be.howest.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.howest.game.Game;
import be.howest.game.Game.STATE;
import be.howest.util.GameUtils;
import be.howest.game.Handler;
import be.howest.input.InputHandler;

public class PowerShop extends InputHandler implements UserInterface {
	public Rectangle next = new Rectangle(Game.WIDTH / 2 - 250, 800, 500, 75);
	private Game game;
	private List<String> powerList = new ArrayList<>(Arrays.asList("Heal", "Barrier", "SlowTime", "MultipleCannon", "Clone", "Card"));
	private String selectedPower;
	private String buttonText;
	
	private boolean purchaseValid = false;

	public PowerShop(Game game, Handler handler) {
		this.game = game;
		this.buttonText = "Buy";
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// Big Title
		Font title = new Font("Arial", Font.BOLD, 50);
		g.setFont(title);
		g.setColor(Color.white);
		g.drawString("INTERMISSION", 50, 75);
		g.setColor(Color.red);
		g.drawString("POWER SHOP", 49, 125);
		drawPowerCards(g);

		// UI - button
		Font smaller_title = new Font("Arial", Font.BOLD, 30);
		g.setFont(smaller_title);
		g.setColor(Color.white);

		g.setColor(Color.red);
		g2d.fill(next);
		g.setColor(Color.black);
		g.drawString(buttonText, Game.WIDTH / 2 - 30, 850);
	}

	private void drawPowerCards(Graphics g) {
		int powIndex = powerList.indexOf(selectedPower);
		String tempPower = selectedPower;
		int MAX = 4;

		//The SHORT IF is used when it needs to calculate what row the card is in.
		//A row contains 4 Cards
		
		
		for (int i = 0; i < powerList.size(); i++) {
			g.drawImage(
					GameUtils.loadImage("/UI/Card/" + powerList.get(i) + ".png"), //Image
					(((i < MAX)? i : i - MAX) * 300) + 50, //XPos
					((i < MAX)? 1 : 3) * 160, //YPos
					null //ImageObserver
					);
		}
		if (selectedPower != null && selectedPower.equals(tempPower)) {
			g.drawImage(
					GameUtils.loadImage("/UI/Card/" + selectedPower + "_Selected.png"), //Image
					(((powIndex < MAX)? powIndex : powIndex-MAX) * 300) + 50, //XPos
					((powIndex < MAX)? 1 : 2) * 160, //YPos
					null //ImageObserver
					);
			}
			
			
	}

	public void tick() {

	}

	private boolean mouseOver(int mx, int my, double x, double y, double width, double height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	public void mouseAction(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		System.out.println("POWER SHOP: " + mx);
		System.out.println("POWER SHOP: " + my);

		if (mouseOver(mx, my, (Game.WIDTH / 2) - 250, 800, 500, 75)) {
			if(purchaseValid){
				System.out.println("PLAY GAME");
				game.setState(STATE.PLAY);
				purchaseValid = false;
			} else {
				PurchasePower(selectedPower);
			}
		}else if (mouseOver(mx, my, 70, 150, 250, 300)){
			System.out.println("POWER1");
			selectedPower = powerList.get(0);
		} else if (mouseOver(mx, my, 360, 150, 250, 300)) {
			System.out.println("POWER2");
			selectedPower = powerList.get(1);
		} else if (mouseOver(mx, my, 650, 150, 250, 300)) {
			System.out.println("POWER3");
			selectedPower = powerList.get(2);
		} else if (mouseOver(mx, my, 950, 150, 250, 300)) {
			System.out.println("POWER4");
			selectedPower = powerList.get(3);
		}else if (mouseOver(mx, my, 70, 150, 250, 300)){
			System.out.println("POWER1");
			selectedPower = powerList.get(0);
		} else if (mouseOver(mx, my, 360, 150, 250, 300)) {
			System.out.println("POWER2");
			selectedPower = powerList.get(1);
		} else if (mouseOver(mx, my, 650, 150, 250, 300)) {
			System.out.println("POWER3");
			selectedPower = powerList.get(2);
		} else if (mouseOver(mx, my, 950, 150, 250, 300)) {
			System.out.println("POWER4");
			selectedPower = powerList.get(3);
		}
		
	}
	
	private void PurchasePower(String selectedPower) {
		//int price = Game.getPower(selectedPower);
		//int XP = Game.getPlayer().getXP();
		
		//TEMP
		int price = 1500;
		int XP = 8888;
		//
		
		if (XP - price >= 0){
			purchaseValid = true;
			buttonText = "Continue";
			//Game.getPlayer().addPower(selectedPower);
		} else {
			System.out.println("You Broke, Play some more games");
		}
	}
}

