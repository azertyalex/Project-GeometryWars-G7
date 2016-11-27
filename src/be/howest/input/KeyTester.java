package be.howest.input;

import java.awt.Canvas;
import java.awt.Graphics;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.gui.Window;
import be.howest.util.GameException;
import be.howest.util.GameLoop;

public class KeyTester extends Canvas implements Runnable, GameLoop{
	private boolean isRunning;
	private Graphics g;
	private Thread thread;
	private Handler handler = new Handler();
	
	public KeyTester(){
		new Window(50*16,50*9,"Testing windows", this);
		this.addKeyListener(new BetterPlayerMovement());
		this.start();
	}
	
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			isRunning = false;
		} catch (Exception ex) {
			throw new GameException("Failed to stop the game!",ex);
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
				updates++;
			}

			render(g);
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				updates = 0;
				frames = 0;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				throw new GameException("Thread failed to sleep!",ex);
			}

		}
		
	}


	@Override
	public void tick() {
		handler.tick();
		
	}


	@Override
	public void render(Graphics g) {
		
		
	}
	
	public static void main(String args[]){
		new KeyTester();
	}

}
