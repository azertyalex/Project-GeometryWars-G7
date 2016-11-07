package be.howest.objects;

public class Heal extends Powers {

	public Heal(String name, int spawnrate){
		super(name,spawnrate);
	}
	
	@Override
	public void usePower(Player target){
		int currentHealth = target.getHealth();
		if(target.checkIfAlive()){
			if(target.getHealth() < 3){
				target.setHealth(currentHealth + 1);
			}
		}
	}
}
