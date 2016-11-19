package be.howest.objects.powers;

import be.howest.objects.Player;

public class Barrier extends Powers {

	public Barrier(String name, int spawnrate){
		super(name, spawnrate);
	}
	
	@Override
	public void usePower(Player target){
		/*
		if(target.checkIfAlive()){
			if(target.checkForExistingBarrier() == false){
				target.setBarrier(true);
			}
		}
		*/
	}
}
