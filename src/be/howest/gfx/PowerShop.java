package be.howest.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;

import be.howest.game.Game;
import be.howest.game.Game.STATE;
import be.howest.objects.testObject;
import be.howest.util.GameUtils;
import be.howest.game.Handler;
import be.howest.game.ID;

public class PowerShop extends MouseAdapter{
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

		
		//UI - button
		Font smaller_title = new Font("Arial", Font.BOLD, 30);

		g2d.fill(next);
		g.setColor(Color.black);
		g.setFont(smaller_title);
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		
		System.out.println(mx);
		System.out.println(my);
		
		//(Game.WIDTH / 2 - 250, 800, 500, 75);
		if (mouseOver(mx, my, (Game.WIDTH / 2) -250, 800, 500, 75)){
			System.out.println("PLAY GAME");
			game.state = STATE.PLAY;
			handler.addObject(new testObject(200,200,ID.Player2));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
