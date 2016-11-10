package be.howest.game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.howest.gfx.DroneUpgrade;
import be.howest.gfx.Menu;
import be.howest.gfx.PowerShop;
import be.howest.gfx.Window;
import be.howest.input.InputHandler;
import be.howest.input.KeyInput;
import be.howest.input.Mouse;
import be.howest.objects.*;
import be.howest.util.GameLoop;
import be.howest.util.GameUtils;

public class Game extends Canvas implements Runnable, GameLoop {

	private static final long serialVersionUID = -7744672260366215689L;

	// Resolution 720p
	public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;
	private Graphics g;

	// Test - Frame
	public int frames = 0;

	// Game Background
	private Thread thread;
	private boolean isRunning = false;

	// Object Handler
	private Handler handler = new Handler();
	
	public enum STATE {
		MENU, PLAY, DRONE_UPGRADE, POWER_SHOP, PAUSE, GAME_OVER, CUSTOMIZATION, OPTIONS, ADMIN_PANEL, EXIT
	}

	// United states of Trump
	private DroneUpgrade droneUpgrade;
	private PowerShop powerShop;
	private Menu menu;

	// Starting state
	public STATE state = STATE.DRONE_UPGRADE;
	
	public static Map<STATE, Object> stateMap = new HashMap<STATE, Object>();


	// Used for z-index
	public static List<GameObject> backgroundObjects = new ArrayList<>();
	public static List<GameObject> enemyObjects = new ArrayList<>();
	public static List<GameObject> playerObjects = new ArrayList<>();
	public static List<GameObject> hud = new ArrayList<>();

	
	// Needed?
	private void addAllObjects() {
		playerObjects.add(new testObject(200, 200, 10, 10, ID.Player2, handler, false));
	}

	public Game() {
		addAllObjects();

		menu = new Menu(this, handler);
		powerShop = new PowerShop(this, handler);
		droneUpgrade = new DroneUpgrade(this, handler, powerShop);

		stateMap.put(STATE.MENU, menu);
		stateMap.put(STATE.DRONE_UPGRADE, droneUpgrade);
		stateMap.put(STATE.POWER_SHOP, powerShop);

		//Works for all input somehow
		this.addMouseListener(menu);				

		new Window(WIDTH, HEIGHT, "Geometry Wars Howest", this);

		if (state == STATE.PLAY) {
			// handler.addObject(backgroundObjects);
			handler.addObject(enemyObjects);
			handler.addObject(playerObjects);
			handler.addObject(hud);
			
			//??
			this.addKeyListener(new KeyInput(handler));
			this.addMouseListener(new Mouse(handler));
		}
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// FPS -- Test closed
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double nanoSeconds = 1000000000 / amountOfTicks;
		double delta = 0;
		// long timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSeconds;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}

			if (isRunning) {
				render(g);
			}
			// frames++;

			// if(System.currentTimeMillis() - timer > 1000){
			// timer += 1000;
			// System.out.println("FPS: " + frames);
			// frames = 0;
			// }
		}
		//

	}

	private void changeMouseListener(){
		if (state == STATE.PLAY) {
		} else if (state == STATE.MENU) {
			menu.tick();
		} else if (state == STATE.DRONE_UPGRADE) {
			droneUpgrade.tick();
		} else if (state == STATE.POWER_SHOP) {
			powerShop.tick();
		}
	}

	
	@Override
	public void tick() {
		InputHandler.setCurrentState(state);

		handler.tick();

		if (state == STATE.PLAY) {

		} else if (state == STATE.MENU) {
			menu.tick();
		} else if (state == STATE.DRONE_UPGRADE) {
			droneUpgrade.tick();
		} else if (state == STATE.POWER_SHOP) {
			powerShop.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if (bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}

		g = bufferStrategy.getDrawGraphics();

		g.drawImage(GameUtils.loadImage("resources\\background\\background.jpg"), 0, 0, WIDTH, HEIGHT, null);

		if (state == STATE.PLAY) {
			handler.render(g);
		} else if (state == STATE.MENU) {
			menu.render(g);
		} else if (state == STATE.DRONE_UPGRADE) {
			droneUpgrade.render(g);
		} else if (state == STATE.POWER_SHOP) {
			powerShop.render(g);
		}

		// g.dispose();
		bufferStrategy.show();
	}

	public static void main(String args[]) {
		// GameUtils.test();
		new Game();
	}
}
