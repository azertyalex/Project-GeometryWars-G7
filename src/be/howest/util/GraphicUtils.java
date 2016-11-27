package be.howest.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import be.howest.game.Game;

public class GraphicUtils {
	private static Font title = new Font("Arial", Font.BOLD,50);
	private static Font small_title = new Font("Arial", Font.BOLD, 30);
	private static Font normal = new Font("Arial", Font.PLAIN, 18);
	private static   Font small = new Font("Arial", Font.PLAIN, 16);

	
	private static Font CURRENT_FONT = normal;
	private static Color CURRENT_COLOR = Color.white;
	
	/* DRAW TITLE */
	// Simple
	public static void drawText(String text, int height, int width, Graphics graphic){
		drawText(text, height, width, CURRENT_COLOR, graphic);
	}
	// Simple centered
	public static void drawText(String text, int height, Graphics graphic){
		drawText(text, height, CURRENT_COLOR, graphic);
	}
	// Colored
	public static void drawText(String text, int height, int width, Color color, Graphics graphic){
		graphic.setFont(CURRENT_FONT);
		graphic.setColor(color);
		graphic.drawString(text, width, height);
	}
	// Colored Centered
	public static void drawText(String text, int height, Color color, Graphics graphic){
		graphic.setFont(CURRENT_FONT);
		graphic.setColor(color);
		drawCenteredString(text, height, graphic);
	}
	
	
	/* DRAW TITLE
	// Simple
	public static void drawTitle(String text, int height, int width, Graphics graphic){
		drawTitle(text, height, width, CURRENT_COLOR, graphic);
	}
	// Simple centered
	public static void drawTitle(String text, int height, Graphics graphic){
		drawTitle(text, height, CURRENT_COLOR, graphic);
	}
	// Colored
	public static void drawTitle(String text, int height, int width, Color color, Graphics graphic){
		graphic.setFont(title);
		graphic.setColor(color);
		graphic.drawString(text, width, height);
	}
	// Colored Centered
	public static void drawTitle(String text, int height, Color color, Graphics graphic){
		graphic.setFont(title);
		graphic.setColor(color);
		drawCenteredString(text, height, graphic);
	}

	
	/* DRAW SMALL_TITLE 
	// Simple
	public static void drawSmallTitle(String text, int height, int width, Graphics graphic){
		drawSmallTitle(text, height, width, sequenceColor, graphic);
	}
	// Simple centered
	public static void drawSmallTitle(String text, int height, Graphics graphic){
		drawSmallTitle(text, height, sequenceColor, graphic);
	}
	// Colored
	public static void drawSmallTitle(String text, int height, int width, Color color, Graphics graphic){
		graphic.setFont(small_title);
		graphic.setColor(color);
		graphic.drawString(text, width, height);
	}
	// Colored Centered
	public static void drawSmallTitle(String text, int height, Color color, Graphics graphic){
		graphic.setFont(small_title);
		graphic.setColor(color);
		drawCenteredString(text, height, graphic);
	}
	
	
	/* DRAW TITLE
	// Simple
	public static void drawText(String text, int height, int width, Graphics graphic){
		drawText(text, height, width, sequenceColor, graphic);
	}
	// Simple centered
	public static void drawText(String text, int height, Graphics graphic){
		drawText(text, height, sequenceColor, graphic);
	}
	// Colored
	public static void drawText(String text, int height, int width, Color color, Graphics graphic){
		graphic.setFont(normal);
		graphic.setColor(color);
		graphic.drawString(text, width, height);
	}
	// Colored Centered
	public static void drawText(String text, int height, Color color, Graphics graphic){
		graphic.setFont(normal);
		graphic.setColor(color);
		drawCenteredString(text, height, graphic);
	}
	 */

	
	/* SEQUENCE OF SIMILAR GRAPHIC PROPERTIES */
	
	public static void setTitleFont(){
		CURRENT_FONT = title;
	}
	public static void setSmallTitleFont(){
		CURRENT_FONT = small_title;
	}
	public static void setNormalFont(){
		CURRENT_FONT = normal;
	}
	public static void setSmallFont(){
		CURRENT_FONT = small;
	}
	
	public static void resetFont(){
		setNormalFont();	
	}
	
	public static void changeColor(Color color){
		CURRENT_COLOR = color;	
	}
	
	public static void resetColor(){
		CURRENT_COLOR = Color.white;	
	}
	
	/* OTHER */
	private static void drawCenteredString(String text, int height, Graphics g){
	    for (String line : text.split("\n")){
	    	int textWidth = g.getFontMetrics().stringWidth(line) / 2;
	    	int textHeight = g.getFontMetrics().getHeight();
			g.drawString(line, Game.WIDTH / 2 - textWidth, height);
			height += textHeight;
	    }
	}
}
