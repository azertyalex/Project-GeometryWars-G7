package be.howest.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import be.howest.game.Game;
import be.howest.game.Game.STATE;
import be.howest.objects.testObject;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.Gamepad;
import be.howest.input.InputHandler;

public class EndScreen extends InputHandler implements UserInterface{
	public Rectangle ExitButton = new Rectangle(Game.WIDTH / 2- 150, 650, 300, 75);

	private Game game;
	private Handler handler;
	private String endScreenText = new String();
	
	public EndScreen(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		Font title = new Font("Arial", Font.BOLD,60);
		Font small_title = new Font("Arial", Font.BOLD, 30);


		g.setFont(title);
		g.setColor(Color.white);
		drawCenteredString(g, 100, endScreenText);
		
		g2d.fill(ExitButton);

		g.setFont(small_title);
		g.setColor(Color.black);
		drawCenteredString(g, 700, "BACK TO MENU");
		}
	
	private void drawCenteredString(Graphics g, int height, String text){
	    for (String line : text.split("\n")){
	    	int textWidth = g.getFontMetrics().stringWidth(line) / 2;
	    	int textHeight = g.getFontMetrics().getHeight();
			g.drawString(line, Game.WIDTH / 2 - textWidth, height);
			height += textHeight;
	    }
	    }
	
	public void tick(String endScreenText){
		this.endScreenText = endScreenText;
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
		int mx = e.getX();
		int my = e.getY();
		
		if (mouseOver(mx, my,Game.WIDTH / 2- 150, 650, 300, 75)){
			game.state = STATE.MENU;
		}
	}
}
