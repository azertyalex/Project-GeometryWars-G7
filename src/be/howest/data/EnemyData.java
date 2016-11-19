package be.howest.data;

import java.awt.image.BufferedImage;

public class EnemyData {
	private String enemyName;
	private String enemyDescription;
	private int enemyHealth;
	private int experienceGiven;
	private BufferedImage sprite;
	
	public EnemyData(String enemyName, String enemyDescription, int enemyHealth, int experienceGiven,
			BufferedImage sprite) {
		this.enemyName = enemyName;
		this.enemyDescription = enemyDescription;
		this.enemyHealth = enemyHealth;
		this.experienceGiven = experienceGiven;
		this.sprite = sprite;
	}

	public String getEnemyName() {
		return enemyName;
	}

	public String getEnemyDescription() {
		return enemyDescription;
	}

	public int getEnemyHealth() {
		return enemyHealth;
	}

	public int getExperienceGiven() {
		return experienceGiven;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setEnemyName(String enemyName) {
		this.enemyName = enemyName;
	}

	public void setEnemyDescription(String enemyDescription) {
		this.enemyDescription = enemyDescription;
	}

	public void setEnemyHealth(int enemyHealth) {
		this.enemyHealth = enemyHealth;
	}

	public void setExperienceGiven(int experienceGiven) {
		this.experienceGiven = experienceGiven;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	
}
