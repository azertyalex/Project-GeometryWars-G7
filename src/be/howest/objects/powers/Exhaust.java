package be.howest.objects.powers;

import be.howest.objects.GameObject;

public class Exhaust extends Powers{
	
	public Exhaust(String name, int spawnrate){
		super(name, spawnrate);
		
	}
	
	public void usePower(GameObject target){
		/*
		if(target.checkIfAlive()){
			int normalPlayerSpeed = target.getSpeed();
			int newPlayerSpeed = normalPlayerSpeed + 1; //nog veranderen
			target.setSpeed(newPlayerSpeed);
			
			
			boolean loop = true;
			long startTime = System.nanoTime();
			
			while(loop){
				long now = System.nanoTime();
				long decreasedTime = now - startTime;
				if(decreasedTime >= 5000000000L){
					target.setSpeed(normalPlayerSpeed);
					loop = false;
				}
				
			}
			
		}
		*/
	}

}
