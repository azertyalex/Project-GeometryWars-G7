package be.howest.data;

public class Difficulty {

	private String name;
	private float spawnRate;
	private float healthRate;
	private float damageRate;
	private float fireRateAmplifier;
	private float speed;
	
	public Difficulty(String name, float spawnRate, float healthRate, float damageRate, float fireRateAmplifier, float speed){
		this.name = name;
		this.spawnRate = spawnRate;
		this.healthRate = healthRate;
		this.damageRate = damageRate;
		this.fireRateAmplifier = fireRateAmplifier;
		this.speed = speed;
	}
	
	

	public float getSpeed() {
		return speed;
	}



	public void setSpeed(float speed) {
		this.speed = speed;
	}



	@Override
	public String toString() {
		return "Naam: " + this.name + " spawnRate: " + this.spawnRate + " healthRate: " + this.healthRate + " damageRate: " + this.damageRate + " fireRateAmplifier: " + this.fireRateAmplifier + " speed: " + this.speed;
	}
	
	
	
	
	
	
}
