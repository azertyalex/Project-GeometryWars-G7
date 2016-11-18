package be.howest.objects.particles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;

public class Particle extends GameObject{
	private Random random = new Random();
	private List<GameObject> particles = new ArrayList<>();
	
	
	public Particle(int x, int y, Color color,float rotation,Handler handler){
		super(x,y,ID.Particle);
		this.health = random.nextInt(25) + 5;
		this.handler = handler;
		for(int i = 0;i < health;i++){
			particles.add(new ParticleEntity(x,y,color,rotation));
		}
	}


	@Override
	public void tick() {
		if(isDead()) handler.removeObject(this);
		for(int i = 0;i < particles.size();i++){
			if(((ParticleEntity) particles.get(i)).isDead()){
				particles.remove(i);		
				health--;
			}else{
				particles.get(i).tick();
			}
		}
		
	}


	@Override
	public void render(Graphics g) {
		for(int i = 0;i < particles.size();i++){
			particles.get(i).render(g);	
		}
	}


	@Override
	public Rectangle getBounds() {
		return null;
	}
	
	
		


}
