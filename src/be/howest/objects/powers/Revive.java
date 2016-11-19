package be.howest.objects.powers;

import be.howest.objects.Player;

public class Revive extends Powers {

	public Revive(String name, int spawnrate){
		super(name, spawnrate);
	}
	
	@Override
	public void usePower(Player target){
		int currentHealth = target.getHealth();
		/*if(target.checkIfAlive() == false){
			target.setHealth(currentHealth + 1);
			
		}*/
	}
}
