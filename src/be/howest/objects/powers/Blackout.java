package be.howest.objects.powers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import be.howest.objects.GameObject;
import be.howest.util.GameUtils;

public class Blackout extends Powers{


	public Blackout(String name, int spawnrate){
		super(name, spawnrate);
	}
	
	public void usePower(GameObject target){
		/*if(target.checkIfAlive()){
			target.setUsePower(true);
		} else {
			target.setUsePower(false);
		}
		
		boolean loop = true;
		long startTime = System.nanoTime();
	
		while(loop){
			long now = System.nanoTime();
			long decreasedTime = now - startTime;
			if(decreasedTime >= 10000000000L){
				target.setUsePower(false);
				loop = false;
			}
		
		}
		*/
	}

}
