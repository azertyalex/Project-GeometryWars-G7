package be.howest.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import be.howest.gfx.DroneUpgrade;
import be.howest.gfx.Menu;
import be.howest.gfx.PowerShop;
import be.howest.gfx.Window;
import be.howest.objects.*;
import be.howest.util.GameLoop;
import be.howest.util.GameUtils;


public class Game extends Canvas implements Runnable, GameLoop{

	//720p res
	private static final long serialVersionUID = -7744672260366215689L;
	public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 *9;	
	public int frames = 0;
	
	private Graphics g;
	
	private Thread thread;
	private boolean isRunning = false;
	
	private Handler handler = new Handler();
	
	public enum STATE{
		MENU,
		PLAY,
		DRONE_UPGRADE,
		POWER_SHOP,
		PAUSE,
		CUSTOMIZATION,
		OPTIONS
	}
	
	private DroneUpgrade droneUpgrade;
	private PowerShop powerShop;
	
	public STATE state = STATE.DRONE_UPGRADE;
	
	public static List<GameObject> backgroundObjects = new ArrayList<>();
	public static List<GameObject> enemyObjects = new ArrayList<>();
	public static List<GameObject> playerObjects = new ArrayList<>();
	public static List<GameObject> hud = new ArrayList<>();
	
	
	
	private void addAllObjects(){
		playerObjects.add(new testObject(200,200,ID.Enemy));
		//playerObjects.add(new Player(200,200,ID.Player));
		
	}
	
	public Game(){
		addAllObjects();

		handler = new Handler();
		powerShop = new PowerShop(this, handler);
		droneUpgrade = new DroneUpgrade(this, handler, powerShop);
		
		//this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(droneUpgrade);

		
		new Window(WIDTH,HEIGHT,"Geometry Wars Howest",this);
		
		
		if (state == STATE.PLAY){
			handler.addObject(backgroundObjects);
			handler.addObject(enemyObjects);
			handler.addObject(playerObjects);
			handler.addObject(hud);
		}
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			isRunning = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	@Override
	public void run() {
		//FPS
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (isRunning){
			long now = System.nanoTime();
			delta += (now -lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;				
			}
			
			if(isRunning){
				render(g);
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}	
		//
		
	}
	
	
	@Override
	public void tick(){
			handler.tick();
		
		if(state == STATE.PLAY){
			
		} else if (state == STATE.DRONE_UPGRADE){
			droneUpgrade.tick();
		} else if (state == STATE.POWER_SHOP){
			powerShop.tick();
		}
		
	}
	
	
	@Override
	public void render(Graphics g){
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null){
			this.createBufferStrategy(3);
			return;
		}
		g = bufferStrategy.getDrawGraphics();
		
		g.drawImage(GameUtils.loadImage("resources\\background\\background.jpg"), 0,0, WIDTH, HEIGHT, null);
		
		
		
		if (state == STATE.PLAY){
			handler.render(g);
		}else if (state == STATE.DRONE_UPGRADE){
			droneUpgrade.render(g);
		}else if (state == STATE.POWER_SHOP){
			powerShop.render(g);
		}
		
		g.dispose();
		bufferStrategy.show();
		
		
	}
	
	public static void main(String args[]){
		//GameUtils.test();
		new Game();

		
	}





}
