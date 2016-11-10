package be.howest.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;

import be.howest.game.Game;
import be.howest.game.Game.STATE;
import be.howest.objects.testObject;
import be.howest.game.Handler;
import be.howest.game.ID;

public class Menu extends MouseAdapter{
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2, 150, 100, 50);
	public Rectangle CustomizationButton = new Rectangle(Game.WIDTH / 2, 250, 100, 50);
	public Rectangle OptionButton = new Rectangle(Game.WIDTH / 2, 350, 100, 50);
	public Rectangle ExitButton = new Rectangle(Game.WIDTH / 2, 450, 100, 50);

	private Game game;
	private Handler handler;
	
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("Arial", Font.BOLD,50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("GEOMETRY WARS", 1280 / 2, 100);
		
		Font fnt1 = new Font("Arial", Font.BOLD, 30);
		g.setFont(fnt1);
		
		g2d.draw(playButton);
		g2d.draw(CustomizationButton);
		g2d.draw(OptionButton);
		g2d.draw(ExitButton);
	
		
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
		
		if (mouseOver(mx, my, (1280 / 2), 150, 100, 50)){
			game.state = STATE.PLAY;
			handler.addObject(new testObject(200,200,ID.Enemy));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
