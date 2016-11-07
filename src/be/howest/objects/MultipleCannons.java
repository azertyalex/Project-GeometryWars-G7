package be.howest.objects;

public class MultipleCannons extends Powers{

	public MultipleCannons(String name, int spawnrate){
		super(name, spawnrate);
	}
	
	@Override
	public void usePower(Player target){
		int currentCannons = target.getCannonAmount();
		if(target.checkIfAlive()){
			target.setCannonAmount(currentCannons + 1);
		}
	}
}
