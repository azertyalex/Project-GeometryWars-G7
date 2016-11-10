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
import be.howest.input.KeyInput;
import be.howest.input.Mouse;
import be.howest.objects.testObject;
import be.howest.util.GameUtils;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.InputHandler;

public class PowerShop extends InputHandler implements UserInterface {
	public Rectangle next = new Rectangle(Game.WIDTH / 2 - 250, 800, 500, 75);
	private Game game;
	private Handler handler;
	private List<String> powerList = new ArrayList<>(Arrays.asList("Heal", "Barrier", "SlowTime"));
	private String selectedPower;

	public PowerShop(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
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
		g.drawString("Buy (" + selectedPower + ")", Game.WIDTH / 2 - 30, 850);
	}

	private void drawPowerCards(Graphics g) {
		for (int i = 0; i < powerList.size(); i++) {
			g.drawImage(GameUtils.loadImage("resources\\UI\\" + powerList.get(i) + ".png"), (i * 300) + 50, 150, null);
		}
		if (selectedPower != null) {
			System.out.println("SELECTED: " + selectedPower);
			g.drawImage(GameUtils.loadImage("resources\\UI\\" + selectedPower + "_Selected.png"),
					(powerList.indexOf(selectedPower) * 300) + 50, 150, null);
		} else {
			System.out.println("NO POWER");
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
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();

		System.out.println("POWER SHOP: " + mx);
		System.out.println("POWER SHOP: " + my);

		if (mouseOver(mx, my, (Game.WIDTH / 2) - 250, 800, 500, 75)) {
			System.out.println("PLAY GAME");
			game.state = STATE.PLAY;
			//(x,y,height,width,id,handler,controller)
			handler.addObject(new testObject(100,100,50,50,ID.Player,handler,false));
			handler.addObject(new testObject(200,200,50,50,ID.Player2,handler,false));
			//handler.addObject(new testObject(200,200,ID.Player2));
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
		} else if (mouseOver(mx, my, 70, 150, 250, 300)) {
			System.out.println("POWER5");
			selectedPower = powerList.get(4);
		}
	}

}
/*
 * private void buyPower(Power power){ if (player.getBoughtPower == null){
 * player.setBoughtPower(power); } else { System.out.println(
 * "Player already has power"); } }
 */
