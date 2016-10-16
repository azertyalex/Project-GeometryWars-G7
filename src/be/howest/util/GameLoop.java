package be.howest.util;

import java.awt.Graphics;


public interface GameLoop {
	public void tick();
	public void render(Graphics g);
}
