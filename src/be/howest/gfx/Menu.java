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
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.input.InputHandler;

public class Menu extends InputHandler implements UserInterface{
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 150, 150, 300, 75);
	public Rectangle CustomizationButton = new Rectangle(Game.WIDTH / 2- 150, 300, 300, 75);
	public Rectangle OptionButton = new Rectangle(Game.WIDTH / 2- 150, 450, 300, 75);
	public Rectangle ExitButton = new Rectangle(Game.WIDTH / 2- 150, 600, 300, 75);

	private Game game;
	private Handler handler;
	
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		Font fnt0 = new Font("Arial", Font.BOLD,50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("GEOMETRY WARS", Game.WIDTH / 2 - 250, 100);
		
		Font fnt1 = new Font("Arial", Font.BOLD, 30);
		g.setFont(fnt1);
		
		g2d.fill(playButton);
		g2d.fill(CustomizationButton);
		g2d.fill(OptionButton);
		g2d.fill(ExitButton);

		g.setColor(Color.black);
		
		g.drawString("PLAY", Game.WIDTH / 2, 200);
		g.drawString("CUSTOMIZE", Game.WIDTH / 2, 350);
		g.drawString("OPTIONS", Game.WIDTH / 2, 500);
		g.drawString("EXIT", Game.WIDTH / 2, 650);

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
		
		System.out.println("MENU: " +mx);
		System.out.println("MENU: " +my);
		
		if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 150, 300, 75)){
			game.state = STATE.PLAY;
			System.out.println("PLAY");
			//handler.addObject(new testObject(200,200,ID.Enemy));
		} else if (mouseOver(mx, my,Game.WIDTH / 2- 150, 300, 300, 75)){
			System.out.println("CUSTOMIZE");
		} else if (mouseOver(mx, my,Game.WIDTH / 2- 150, 450, 300, 75)){
			System.out.println("OPTIONS");
		} else if (mouseOver(mx, my,Game.WIDTH / 2- 150, 600, 300, 75)){
			System.out.println("EXIT");
		}
	}
}
