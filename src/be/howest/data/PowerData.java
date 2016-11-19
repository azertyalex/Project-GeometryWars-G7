package be.howest.data;

import java.awt.image.BufferedImage;

public class PowerData {
	private String powerName;
	private String powerDescription;
	private BufferedImage sprite;
	private int cost;
	
	public PowerData(String powerName, String powerDescription, BufferedImage sprite, int cost) {
		this.powerName = powerName;
		this.powerDescription = powerDescription;
		this.sprite = sprite;
		this.cost = cost;
	}

	public String getPowerName() {
		return powerName;
	}

	public String getPowerDescription() {
		return powerDescription;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public int getCost() {
		return cost;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public void setPowerDescription(String powerDescription) {
		this.powerDescription = powerDescription;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
}
