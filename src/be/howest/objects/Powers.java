package be.howest.objects;

import be.howest.difficulty.Difficulty;

public abstract class Powers {

	protected String name;
	protected int spawnrate;
	
	
	public Powers(String name, int spawnrate){
		this.name = name;
		this.spawnrate = spawnrate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getSpawnrate() {
		return spawnrate;
	}


	public void setSpawnrate(int spawnrate) {
		this.spawnrate = spawnrate;
	}
	
	public void usePower(Player target){
		throw new IllegalStateException();
	}
	
	public void usePower(Player target, Difficulty difficulty){
		throw new IllegalStateException();
	}
	
	
    public String toString(){
        return String.format("Name: %s, Spawn Rate: %d ", getName(), getSpawnrate());
    }
	
	
}
