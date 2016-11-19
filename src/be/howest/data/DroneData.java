package be.howest.data;

import java.awt.image.BufferedImage;

public class DroneData {
	private String droneName;
	private int droneLevel;
	private String droneDescription;
	private int experienceRequired;
	private BufferedImage sprite;
	private BufferedImage powerSprite;
	
	public DroneData(String droneName, int droneLevel, String droneDescription, int experienceRequired,
			BufferedImage sprite, BufferedImage powerSprite) {
		this.droneName = droneName;
		this.droneLevel = droneLevel;
		this.droneDescription = droneDescription;
		this.experienceRequired = experienceRequired;
		this.sprite = sprite;
		this.powerSprite = powerSprite;
	}

	public String getDroneName() {
		return droneName;
	}

	public int getDroneLevel() {
		return droneLevel;
	}

	public String getDroneDescription() {
		return droneDescription;
	}

	public int getExperienceRequired() {
		return experienceRequired;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public BufferedImage getPowerSprite() {
		return powerSprite;
	}

	public void setDroneName(String droneName) {
		this.droneName = droneName;
	}

	public void setDroneLevel(int droneLevel) {
		this.droneLevel = droneLevel;
	}

	public void setDroneDescription(String droneDescription) {
		this.droneDescription = droneDescription;
	}

	public void setExperienceRequired(int experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public void setPowerSprite(BufferedImage powerSprite) {
		this.powerSprite = powerSprite;
	}
	
	
}
