package be.howest.gui;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import be.howest.game.Game;
import be.howest.input.KeyTester;

public class Window extends Canvas{

	private static final long serialVersionUID = 9029825079889077714L;
	
	public Window(int width, int height, String title,Game game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
		
		game.start();
		game.requestFocus();
	}

	//TODO remove this after testing!
	public Window(int width, int height, String title, KeyTester keyTester) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(keyTester);
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
		
		keyTester.start();
		keyTester.requestFocus();
	}



}
