package be.howest.objects;

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
	
	
	
}
