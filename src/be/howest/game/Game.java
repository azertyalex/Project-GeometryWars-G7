package be.howest.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.howest.gui.DroneUpgrade;
import be.howest.gui.EndScreen;
import be.howest.gui.Hud;
import be.howest.gui.Menu;
import be.howest.gui.Play;
import be.howest.gui.PowerShop;
import be.howest.gui.UserInterface;
import be.howest.gui.Window;
import be.howest.input.Gamepad;
import be.howest.input.InputHandler;
import be.howest.input.PlayerShoot;
import be.howest.objects.*;
import be.howest.objects.enemies.Grunt;
import be.howest.objects.enemies.MineLayer;
import be.howest.objects.enemies.Wanderer;
import be.howest.objects.enemies.Dart;
import be.howest.util.GameLoop;
import be.howest.util.GameUtils;

public class Game extends Canvas implements Runnable, GameLoop {

	private static final long serialVersionUID = -7744672260366215689L;

	/* GAME INFO */
	// General
	public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9; // Resolution
																	// 720p
	private BufferedImage bg = GameUtils.loadImage("/images/Background/NightSky_Pixel.png");
	private Graphics g;

	// Game
	private Thread thread;
	private boolean isRunning = false;

	// Object Handler
	private Handler handler = new Handler();

	// Used for z-index
	public static List<GameObject> backgroundObjects = new ArrayList<>();
	public static List<GameObject> enemyObjects = new ArrayList<>();
	public static List<GameObject> playerObjects = new ArrayList<>();
	public static List<GameObject> hud = new ArrayList<>();

	// Input
	private PlayerShoot playerShoot;
	private Gamepad gamepad;
	private boolean controllerConnected = false;
	public static boolean CONTROLLER = false;

	/* STATES */
	// All States
	public enum STATE {
		MENU, PLAY, DRONE_UPGRADE, POWER_SHOP, PAUSE, GAME_OVER, VICTORY, CUSTOMIZATION, OPTIONS, ADMIN_PANEL, EXIT
	}

	// Starting State
	private STATE state = STATE.MENU;

	// United States
	private Hud HUD;
	private DroneUpgrade droneUpgrade;
	private PowerShop powerShop;
	private Menu menu;
	private EndScreen endScreen;
	private Play play;

	private static Map<STATE, UserInterface> stateMap = new HashMap<STATE, UserInterface>();


	/* GAME & LOOP */
	public static void main(String args[]) {
		new Game();

	}

	public Game() {
		// Entities
		addAllObjects();

		// UI
		menu = new Menu(this, handler);
		powerShop = new PowerShop(this, handler);
		droneUpgrade = new DroneUpgrade(this, handler, powerShop);
		endScreen = new EndScreen(this, handler);
		HUD = new Hud();
		play = new Play(this, handler, HUD);

		// stateMap
		stateMap.put(STATE.MENU, menu);
		stateMap.put(STATE.DRONE_UPGRADE, droneUpgrade);
		stateMap.put(STATE.POWER_SHOP, powerShop);
		stateMap.put(STATE.GAME_OVER, endScreen);
		stateMap.put(STATE.VICTORY, endScreen);
		stateMap.put(STATE.PLAY, play);
		// Works for all input
		
		InputHandler inputHandler = new InputHandler();
		
		this.addMouseListener(inputHandler);
		this.addKeyListener(inputHandler);

		new Window(WIDTH, HEIGHT, "Geometry Wars Howest", this);
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
				// System.out.println("RUN: " + " | " + updates + " ups, " +
				// frames + " fps");
				updates = 0;
				frames = 0;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void tick() {
		InputHandler.setCurrentState(state);

		handler.tick();

		stateMap.get(state).tick();

	}

	@Override
	public void render(Graphics g) {
		BufferStrategy bufferStrategy = this.getBufferStrategy();

		if (bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}

		g = bufferStrategy.getDrawGraphics();

		//System.out.println("In Render: {g: " + g + "}");

		g.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);

		Font text = new Font("Arial", Font.PLAIN, 18);
		g.setFont(text);
		g.setColor(Color.white);
		g.drawString("PROTOTYPE (15 NOV.)", 5, 20);

		stateMap.get(state).render(g);

		bufferStrategy.show();
		g.dispose();
	}

	/* METHODS */
	public void addAllObjects() {
		// Enemy
		enemyObjects.add(new Wanderer(500, 152, 50, 50, ID.Wanderer, ID.Enemy, handler));
		enemyObjects.add(new Dart(WIDTH, 500, 50, 50, ID.Dart, ID.Enemy, handler));
		enemyObjects.add(new Grunt(25, 42, 24, 24, ID.Grunt, ID.Enemy, handler));
		enemyObjects.add(new Grunt(562, 85, 24, 24, ID.Grunt, ID.Enemy, handler));
		enemyObjects.add(new Grunt(785, 185, 24, 24, ID.Grunt, ID.Enemy, handler));
		enemyObjects.add(new Grunt(125, 485, 24, 24, ID.Grunt, ID.Enemy, handler));
		enemyObjects.add(new Grunt(365, 253, 24, 24, ID.Grunt, ID.Enemy, handler));
		enemyObjects.add(new MineLayer(20, 30, 50, 50, ID.MineLayer, ID.Enemy, handler));

		for (int i = 0; i < 1; i++) {
			enemyObjects.add(new Grunt(125, 485, 24, 24, ID.Grunt, ID.Enemy, handler));
			enemyObjects.add(new Grunt(365, 253, 24, 24, ID.Grunt, ID.Enemy, handler));
		}

	}

	public boolean isControllerFound() {
		if (gamepad == null) {
			return false;
		} else {
			return gamepad.ControllerActive();
		}
	}

	/* GETTERS AND SETTERS */
	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public PlayerShoot getMouse() {
		return playerShoot;
	}

	public void setMouse(PlayerShoot playerShoot) {
		this.playerShoot = playerShoot;
	}

	public static UserInterface getStateFromMap(STATE state) {
		return stateMap.get(state);
	}

	public Gamepad getGamepad() {
		return gamepad;
	}

	public void setGamepad(Gamepad gamepad) {
		this.gamepad = gamepad;
		Thread t = new Thread(gamepad);
		t.start();
	}

	public boolean isControllerConnected() {
		return controllerConnected;
	}

	public void setControllerConnected(boolean controllerConnected) {
		this.controllerConnected = controllerConnected;

	}
}
