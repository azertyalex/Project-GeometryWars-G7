package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.howest.game.ID;

public class Particle extends GameObject{
	private Random random = new Random();
	private int amountOfParticles = random.nextInt(20-5) -5;
	private List<ParticleEntity> particles = new ArrayList<>();
	
	
	public Particle(int x, int y, Color color,float rotation){
		super(x,y,ID.Particle);
		for(int i = 0;i < amountOfParticles;i++){
			particles.add(new ParticleEntity(x,y,color,rotation));
			
		}
		handler.addObject((GameObject) particles);
		
		
	}


	@Override
	public void tick() {
		if(particles.isEmpty()) handler.removeObject(this);
		for(int i = 0;i < particles.size();i++){
			if(particles.get(i).isDead()){
				ParticleEntity tempP = particles.get(i);
				particles.remove(i);	
				handler.removeObject(tempP);	
			}			
		}
		
	}


	@Override
	public void render(Graphics g) {
	
	}


	@Override
	public Rectangle getBounds() {
		return null;
	}
	
	private class ParticleEntity extends GameObject{
		private Color color;
		private Random random = new Random();
		private int randomInt = random.nextInt(25);
		private int life = random.nextInt(10);

		public ParticleEntity(int x, int y,Color color, float rotation) {
			super(x, y, ID.Particle);
			this.color = color;
			float randomRotation = random.nextInt(80) -40F;
			velY = (int) (26 - randomInt *Math.sin(Math.toRadians(rotation-90+randomRotation)));
	        velX = (int) (26 - randomInt *Math.cos( Math.toRadians(rotation-90+randomRotation)));
			
		}
		
		public boolean isDead(){
			if(life == 0) return true;
			return false;
		}

		@Override
		public void tick() {
			x += velX;
			y += velY;
			life--;
			
		}

		@Override
		public void render(Graphics g) {
			g.setColor(color);
			g.fillRect(x, y, randomInt, randomInt);
			
			
		}

		@Override
		public Rectangle getBounds() {
			return null;
		}
		
	}

}
