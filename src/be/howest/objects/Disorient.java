package be.howest.objects;

public class Disorient extends Powers{

	public Disorient(String name, int spawnrate){
		super(name, spawnrate);
	}
	
	public void usePower(GameObject target){
		int normalSpeed = 1;
		int reversedSpeed = -1;
		/*
		if(target.checkIfAlive()){
			target.setSpeed(reversedSpeed);
			System.out.println(target.getSpeed());
		
			boolean loop = true;
			long startTime = System.nanoTime();
		
			while(loop){
				long now = System.nanoTime();
				long decreasedTime = now - startTime;
				if(decreasedTime >= 10000000000L){
					target.setSpeed(normalSpeed);;
					System.out.println(target.getSpeed());
					loop = false;
				}
			
			}
		}
		*/
	}
}
