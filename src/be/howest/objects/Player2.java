package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.javafx.util.Utils;

import be.howest.game.GameUtils;
import be.howest.game.Gamepad;
import be.howest.game.ID;
import be.howest.interfaces.GameObject;
import net.java.games.input.Component;

public class Player2 extends GameObject{
	
	public Player2(int x, int y, ID id){
		super(x,y,id);
		
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		Gamepad gp = new Gamepad(0);
		gp.getControllers();
		gp.getComponentFromController(0);
		
		int val = 2;
		
		for(Component con: gp.getComponentFromController(0)){
			if(con.getIdentifier() == Component.Identifier.Axis.RX){
				velX = (int) (GameUtils.clamp(con.getPollData(), -1F, 1F,0F) * val); ;
			}
			
			if(con.getIdentifier() == Component.Identifier.Axis.RY){
				velY= (int) (GameUtils.clamp(con.getPollData(), -1F, 1F,0F) * val); ;
			}
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 32, 32);
		
	}
}
