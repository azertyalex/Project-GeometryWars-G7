package be.howest.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface UserInterface {
	public void mouseAction(MouseEvent e);
	public void render(Graphics g);
	public void tick();
}
