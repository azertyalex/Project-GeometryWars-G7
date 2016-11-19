package be.howest.objects.powers;

//import be.howest.difficulty.Difficulty;

public class SlowTime extends Powers{

	public SlowTime(String name, int spawnrate){
		super(name, spawnrate);
	}
	/*
	@Override
	public void usePower(Player target, Difficulty difficulty){
		if(target.checkIfAlive()){
			float normalSpeed = difficulty.getSpeed();
			float newSpeed = normalSpeed - 0.50F;
			difficulty.setSpeed(newSpeed);
			System.out.println(difficulty.getSpeed());
			
			
			boolean loop = true;
			long startTime = System.nanoTime();
			
			while(loop){
				long now = System.nanoTime();
				long decreasedTime = now - startTime;
				if(decreasedTime >= 5000000000L){
					difficulty.setSpeed(normalSpeed);
					loop = false;
				}
				
			}
		}
	}*/
}
