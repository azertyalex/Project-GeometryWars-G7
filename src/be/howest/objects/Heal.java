package be.howest.objects;

public class Heal extends Powers {

	public Heal(String name, int spawnrate){
		super(name,spawnrate);
	}
	
	public void usePower(GameObject target){
		int currentHealth = target.getHealth();
		if(target.checkIfAlive(target)){
			if(target.getHealth() < 3){
				target.setHealth(currentHealth + 1);
			}
		}
	}
}
