package be.howest.objects;

public class Exhaust extends Powers{
	
	public Exhaust(String name, int spawnrate){
		super(name, spawnrate);
		
	}
	
	public void usePower(Player target){
		if(target.checkIfAlive()){
			float normalPlayerSpeed = target.getSpeed();
			float newPlayerSpeed = normalPlayerSpeed - 0.25F;
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
	}

}
