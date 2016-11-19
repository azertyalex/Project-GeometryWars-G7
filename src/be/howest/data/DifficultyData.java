package be.howest.data;

public class DifficultyData {
	private String difficultyName;
	private String difficultyDescription;
	private int speed;
	private int damageRate;
	private int fireRate;
	private int spawnRate;
	private int healthRate;
	
	public DifficultyData(String difficultyName, String difficultyDescription, int speed, int damageRate, int fireRate,
			int spawnRate, int healthRate) {
		this.difficultyName = difficultyName;
		this.difficultyDescription = difficultyDescription;
		this.speed = speed;
		this.damageRate = damageRate;
		this.fireRate = fireRate;
		this.spawnRate = spawnRate;
		this.healthRate = healthRate;
	}

	public String getDifficultyName() {
		return difficultyName;
	}

	public String getDifficultyDescription() {
		return difficultyDescription;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDamageRate() {
		return damageRate;
	}

	public int getFireRate() {
		return fireRate;
	}

	public int getSpawnRate() {
		return spawnRate;
	}

	public int getHealthRate() {
		return healthRate;
	}

	public void setDifficultyName(String difficultyName) {
		this.difficultyName = difficultyName;
	}

	public void setDifficultyDescription(String difficultyDescription) {
		this.difficultyDescription = difficultyDescription;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setDamageRate(int damageRate) {
		this.damageRate = damageRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	public void setSpawnRate(int spawnRate) {
		this.spawnRate = spawnRate;
	}

	public void setHealthRate(int healthRate) {
		this.healthRate = healthRate;
	}
	
	
}
