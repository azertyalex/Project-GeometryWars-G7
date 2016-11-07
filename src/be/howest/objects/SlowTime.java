package be.howest.objects;

import be.howest.difficulty.Difficulty;

public class SlowTime extends Powers{

	public SlowTime(String name, int spawnrate){
		super(name, spawnrate);
	}
	
	@Override
	public void usePower(Player target, Difficulty difficulty){
		if(target.checkIfAlive()){
			float currentSpeed = difficulty.getSpeed();
			difficulty.setSpeed(currentSpeed - 0.50F);
		}
	}
}
