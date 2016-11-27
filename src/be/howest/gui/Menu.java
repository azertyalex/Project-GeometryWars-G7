package be.howest.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import be.howest.game.Game;
import be.howest.game.Game.STATE;
import be.howest.game.Handler;
import be.howest.input.Gamepad;
import be.howest.input.InputHandler;
import be.howest.util.GraphicUtils;

public class Menu extends InputHandler implements UserInterface{
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 150, 200, 300, 75);
	public Rectangle CustomizationButton = new Rectangle(Game.WIDTH / 2- 150, 350, 300, 75);
	public Rectangle OptionButton = new Rectangle(Game.WIDTH / 2- 150, 500, 300, 75);
	public Rectangle ExitButton = new Rectangle(Game.WIDTH / 2- 150, 650, 300, 75);

	private Game game;
	public Menu(Game game, Handler handler){
		this.game = game;
	}
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g2d.fill(playButton);
		g2d.fill(CustomizationButton);
		g2d.fill(OptionButton);
		g2d.fill(ExitButton);
		
		GraphicUtils.setTitleFont();
		GraphicUtils.drawText("GEOMETRY WARS", 100, g);

		GraphicUtils.setSmallTitleFont();
		GraphicUtils.drawText("PROTOTYPE (NOV.)", 135, g);

		GraphicUtils.changeColor(Color.black);
		GraphicUtils.drawText("PLAY", 250, g);
		GraphicUtils.drawText("GAMEPAD ON/OFF", 400, g);
		GraphicUtils.drawText("EXTRA", 550, g);
		GraphicUtils.drawText("EXIT", 700, g);
		GraphicUtils.resetColor();

		GraphicUtils.setNormalFont();
		GraphicUtils.drawText("\"PLAY\" bevat de core gameplay van het spel.\n Je kunt het schip besturen en er mee schieten. De drone kan alleen de speler volgen.\n Er zullen een paar vijanden komen die je kan dood schieten.", 300, g);
		GraphicUtils.drawText("Met \"GAMEPAD ON/OFF\" kan je de input naar controller of keyboard/mouse zetten. \n Om te navigeren in de UI moet je de muis gebruiken.", 450, g);
		GraphicUtils.drawText("\"EXTRA\" bevat de clickable droneUpgrade en PowerShop.\n Deze is nog niet gekoppeld aan de core gameplay.", 600, g);
		GraphicUtils.drawText("EXIT", 750, g);

		GraphicUtils.resetFont();
		GraphicUtils.resetColor();
		
	//--TEMP
		g.setColor(Color.red);
		String input = (Game.CONTROLLER) ? (game.isControllerFound())? "Controller (CONNECTED)" :"Controller (NOT CONNECTED)" : "Keyboard/PlayerShoot";
		g.drawString("INPUT: " + input, Game.WIDTH / 2 + 160 , 395);
			
		if (Game.CONTROLLER && !game.isControllerFound()){
			g2d.fill(new Rectangle(0, 30, Game.WIDTH, 150));
			g.setColor(Color.white);
			Font title = new Font("Arial", Font.BOLD,50);
			g.setFont(title);
			drawCenteredString(g, 100, "Press \"Start\" to connect the controller.");
		}

		
		}
	
	private void drawCenteredString(Graphics g, int height, String text){
	    for (String line : text.split("\n")){
	    	int textWidth = g.getFontMetrics().stringWidth(line) / 2;
	    	int textHeight = g.getFontMetrics().getHeight();
			g.drawString(line, Game.WIDTH / 2 - textWidth, height);
			height += textHeight;
	    }
	 }
	//--
	
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
		
		//System.out.println("MENU: " +mx);
		//System.out.println("MENU: " +my);
		
		if (mouseOver(mx, my, Game.WIDTH / 2 - 150, 200, 300, 75)){
			game.setState(STATE.PLAY);
			System.out.println("PLAY");
		} else if (mouseOver(mx, my,Game.WIDTH / 2- 150, 350, 300, 75)){
			Game.CONTROLLER = !Game.CONTROLLER;
			System.out.println("GAMEPAD " + Game.CONTROLLER);

			if (Game.CONTROLLER && !game.isControllerConnected()){
				System.out.println("new controller");
				game.setGamepad(new Gamepad());
				game.setControllerConnected(true);
			}
		} else if (mouseOver(mx, my,Game.WIDTH / 2- 150, 500, 300, 75)){
			game.setState(STATE.DRONE_UPGRADE);
			System.out.println("EXTRA");
		} else if (mouseOver(mx, my,Game.WIDTH / 2- 150, 650, 300, 75)){
			game.setState(STATE.EXIT);
			System.exit(0);
			System.out.println("EXIT");
		}
	}
}
