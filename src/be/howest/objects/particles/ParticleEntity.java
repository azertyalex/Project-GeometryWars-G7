package be.howest.objects.particles;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.util.Random;

import be.howest.game.Game;
import be.howest.game.ID;
import be.howest.objects.GameObject;

public class ParticleEntity extends GameObject{
	private Color color;
	private Random random = new Random();
	private int randomInt = random.nextInt(20);
	

	public ParticleEntity(int x, int y,Color color, float rotation) {
		super(x, y, ID.Particle);
		health = random.nextInt(15)+5;
		this.color = color;
		float randomRotation = (float) Math.toRadians(random.nextInt(40) -20F);
		velY = (int) ((21 - randomInt) * Math.sin((rotation - 90) + randomRotation));
        velX = (int) ((21 - randomInt) * Math.cos((rotation - 90) + randomRotation));
        System.out.println(velX + " || " +velY);
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		health--;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, randomInt, randomInt);
		Graphics2D g2d = (Graphics2D) g;
	    java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(getCenterX(), getCenterY());
	    float radius = 10;
	    float[] dist = {0.0f, 0.9f};
	    Color[] colors = {this.color, new Color(0.0f, 0.0f, 0.0f, 0.0f)};
	    RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
	    g2d.setPaint(p);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f));
	    g2d.fillRect(0, 0, Game.WIDTH, Game.WIDTH);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
}
