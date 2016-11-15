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
import be.howest.gfx.Hud;
import java.util.Map;
import be.howest.gfx.DroneUpgrade;
import be.howest.gfx.EndScreen;
import be.howest.gfx.Menu;
import be.howest.gfx.PowerShop;
import be.howest.gfx.Window;
import be.howest.input.Gamepad;
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

	// Test - Frames
	public int frames = 0;

	// Game Background
	private Thread thread;
	private boolean isRunning = false;

	// Object Handler
	private Handler handler = new Handler();
	private boolean isAdded = false;

	public static boolean CONTROLLER = false;

	public enum STATE {
		MENU, PLAY, DRONE_UPGRADE, POWER_SHOP, PAUSE, GAME_OVER, VICTORY, CUSTOMIZATION, OPTIONS, ADMIN_PANEL, EXIT
	}

	// United States
	private Hud HUD;
	private DroneUpgrade droneUpgrade;
	private PowerShop powerShop;
	private Menu menu;
	private EndScreen endScreen;


	// Starting State
	public STATE state = STATE.MENU;

	public static Map<STATE, Object> stateMap = new HashMap<STATE, Object>();

	private Gamepad gamepad;
	private boolean controllerConnected = false;

	// Used for z-index
	public static List<GameObject> backgroundObjects = new ArrayList<>();
	public static List<GameObject> enemyObjects = new ArrayList<>();
	public static List<GameObject> playerObjects = new ArrayList<>();
	public static List<GameObject> hud = new ArrayList<>();

	private BufferedImage bg = GameUtils.loadImage("resources\\background\\NightSky_Pixel.png");
	private Mouse mouse;


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

	private void addAllObjects() {
		// Enemy

		enemyObjects.add(new Wanderer(500, 152, 50, 50, ID.Wanderer,ID.Enemy,handler));
		enemyObjects.add(new Dart(WIDTH, 500, 50, 50, ID.Dart,ID.Enemy, handler));
		enemyObjects.add(new Grunt(25, 42, 24, 24, ID.Grunt,ID.Enemy, handler));
		enemyObjects.add(new Grunt(562, 85, 24, 24, ID.Grunt,ID.Enemy, handler));
		enemyObjects.add(new Grunt(785, 185, 24, 24, ID.Grunt,ID.Enemy, handler));
		enemyObjects.add(new Grunt(125, 485, 24, 24, ID.Grunt,ID.Enemy, handler));
		enemyObjects.add(new Grunt(365, 253, 24, 24, ID.Grunt,ID.Enemy, handler));
		enemyObjects.add(new MineLayer(20, 30, 50, 50, ID.MineLayer,ID.Enemy, handler));

		for (int i = 0; i < 1; i++) {
			enemyObjects.add(new Grunt(125, 485, 24, 24, ID.Grunt,ID.Enemy, handler));
			enemyObjects.add(new Grunt(365, 253, 24, 24, ID.Grunt,ID.Enemy, handler));
		}

	}

	public boolean isControllerFound() {
		if (gamepad == null) {
			return false;
		} else {
			return gamepad.ControllerActive();
		}
	}

	public Game() {
		new Window(WIDTH, HEIGHT, "Geometry Wars Howest", this);

		// Entities
		addAllObjects();

		// UI
		System.out.println("CREATE");
		menu = new Menu(this, handler);
		powerShop = new PowerShop(this, handler);
		droneUpgrade = new DroneUpgrade(this, handler, powerShop);
		endScreen = new EndScreen(this,handler);
		HUD = new Hud();

		// stateMap
		stateMap.put(STATE.MENU, menu);
		stateMap.put(STATE.DRONE_UPGRADE, droneUpgrade);
		stateMap.put(STATE.POWER_SHOP, powerShop);
		stateMap.put(STATE.GAME_OVER, endScreen);
		stateMap.put(STATE.VICTORY, endScreen);


		stateMap.put(STATE.PLAY, this);

		// Works for all input
		this.addMouseListener(menu);
		this.addKeyListener(menu);
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
				try {
					tick();
					delta--;
					updates++; 
				}catch (NullPointerException e) {
					//TEMP
				}

							
			}
			render(g); 
						
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("RUN: " + " | " + updates + " ups, " + frames + " fps");
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
		
		//CLEAN UP LATER
		if (state == STATE.PLAY) {
			if (!isAdded) {
				// Player
				if (CONTROLLER) {
					playerObjects.add(
							new testObject(Game.WIDTH / 2, Game.HEIGHT / 2, 50, 50, ID.Player2, ID.Player, handler, gamepad, 3));
				} else {
					playerObjects.add(
							new testObject(Game.WIDTH / 2, Game.HEIGHT / 2, 50, 50, ID.Player2,ID.Player, handler, 3));
				}
				// Drone
				playerObjects.add(new Drone(0, 0, 40, 40, ID.Drone, handler));

				handler.addObject(enemyObjects);
				handler.addObject(playerObjects);
				handler.addObject(hud);
				
					mouse = new Mouse(handler, handler.getGameObject(ID.Player2));
					this.addKeyListener(new KeyInput(handler));
					this.addMouseListener(mouse);
					this.addMouseMotionListener(mouse);

				isAdded = true;
				
			} else {
				System.out.println(state);
				HUD.setHudHealth(playerObjects.get(0).getHealth());
				//state = (playerObjects.get(0).getHealth() > 0)? STATE.PLAY : STATE.GAME_OVER;
				/*if ((playerObjects.get(0).getHealth() <= 0)){
					state = STATE.GAME_OVER;
					resetGame();
				} else if (!handler.hasEnemies()) {
					state = STATE.VICTORY;
					resetGame();
				}else {
					state = STATE.PLAY;
				}*/
			}

		} else if (state == STATE.MENU) {
			menu.tick();
		} else if (state == STATE.DRONE_UPGRADE) {
			droneUpgrade.tick();
		} else if (state == STATE.POWER_SHOP) {
			powerShop.tick();
		} else if (state == STATE.GAME_OVER) {
			endScreen.tick("GAME OVER");
		} else if (state == STATE.VICTORY) {
			endScreen.tick("VICTORY");
		}
	}

	private void resetGame(){
		isAdded = false;
		System.out.println(handler.getList());
		
		//TEMP -- DEMO PURPOSE -- REMOVE THIS LATER
		handler.clearObject();
		playerObjects.clear();
		enemyObjects.clear();
		hud.clear();
		
		this.removeMouseListener(mouse);
		this.removeMouseMotionListener(mouse);
		
		addAllObjects();
		//TEMP -- ...
	}
	
	@Override
	public void render(Graphics g) {
		BufferStrategy bufferStrategy = this.getBufferStrategy();

		if (bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}

		g = bufferStrategy.getDrawGraphics();
		

		g.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
		//g.drawRect(0, 0, 0, 0);

		// g.setColor(Color.black);
		// g.fillRect(0, 0, WIDTH, HEIGHT);

		Font text = new Font("Arial", Font.PLAIN, 18);
		g.setFont(text);
		g.setColor(Color.white);
		g.drawString("PROTOTYPE (13 NOV.)", 5, 20);

		if (state == STATE.PLAY) {
			HUD.render(g);
			handler.render(g);
		} else if (state == STATE.MENU) {
			menu.render(g);
		} else if (state == STATE.DRONE_UPGRADE) {
			droneUpgrade.render(g);
		} else if (state == STATE.POWER_SHOP) {
			powerShop.render(g);
		} else if (state == STATE.GAME_OVER ||state == STATE.VICTORY) {
			endScreen.render(g);
		}
		bufferStrategy.show();
		g.dispose();
	}

	public static void main(String args[]) {
		new Game();
	}
}
