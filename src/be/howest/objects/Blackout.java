package be.howest.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import be.howest.util.GameUtils;

public class Blackout extends Powers{


	public Blackout(String name, int spawnrate){
		super(name, spawnrate);
	}
	
	public void usePower(testObject target){
		if(target.checkIfAlive()){
			target.setUsePower(true);
		} else {
			target.setUsePower(false);
		}
	}

}
