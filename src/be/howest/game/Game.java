package be.howest.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import be.howest.gfx.Window;
import be.howest.input.KeyInput;
import be.howest.input.Mouse;
import be.howest.objects.*;
import be.howest.util.GameLoop;
import be.howest.util.GameUtils;


public class Game extends Canvas implements Runnable, GameLoop{

	private static final long serialVersionUID = -7744672260366215689L;
	public static final int WIDTH = 720, HEIGHT = WIDTH / 12 *9;
	
	public int frames = 0;
	
	private Graphics g;
	
	private Thread thread;
	private boolean isRunning = false;
	
	private Handler handler = new Handler();
	
	private List<GameObject> backgroundObjects = new ArrayList<>();
	private List<GameObject> enemyObjects = new ArrayList<>();
	private List<GameObject> playerObjects = new ArrayList<>();
	private List<GameObject> hud = new ArrayList<>();
	
	
	
	private void addAllObjects(){
		//playerObjects.add(new Player(100,100,ID.Player2));
		playerObjects.add(new testObject(200,200,10,10,ID.Player2,handler,false));
		
		
	}
	
	public Game(){
		new Window(WIDTH,HEIGHT,"Geometry Wars Howest",this);
		
		addAllObjects();
		
		
		
		handler.addObject(backgroundObjects);
		handler.addObject(enemyObjects);
		handler.addObject(playerObjects);
		handler.addObject(hud);
		
		Mouse mouse = new Mouse(handler,handler.getGameObject(ID.Player2));
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
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
	}
	
	
	@Override
	public void tick(){
		
		handler.tick();
		
		
		
	}
	
	@Override
	public void render(Graphics g){
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null){
			this.createBufferStrategy(5);
			return;
		}
		g = bufferStrategy.getDrawGraphics();
		

		//g.setColor(Color.black);
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.drawImage(GameUtils.loadImage("resources\\background\\background.jpg"), 0,0, WIDTH, HEIGHT, null);

		handler.render(g);

		bufferStrategy.show();
		
		
	}
	
	public static void main(String args[]){
		new Game();

		
		
	}





}
