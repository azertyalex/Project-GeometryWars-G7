package be.howest.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import be.howest.objects.GameObject;
import be.howest.util.Animation;
import be.howest.util.GameUtils;
import be.howest.util.Sprite;

public class ResourceCollection {
	public static HashMap<String,HashMap> DATALIST = new HashMap<String,HashMap>();
	public static HashMap<String,EnemyData> enemyDataList;
	public static HashMap<String,DroneData> DroneDataList;
	public static HashMap<String,PowerData> powerDataList;
	public static HashMap<String,DifficultyData> DifficultyDataList;
	
	public static HashMap<String,BufferedImage> SpriteList;
	public static HashMap<String,BufferedImage[]> AnimatedSpriteList;

	private DataConnection db = DataConnection.getInstance();
	
	public static void init(){
		/* DATA */
		//Data
		DATALIST.put("Enemy", enemyDataList);
		DATALIST.put("Drone", DroneDataList);
		DATALIST.put("Power", powerDataList);
		DATALIST.put("Difficulty", DifficultyDataList);
		
		/* FILES */
		//Images
		DATALIST.put("Sprite", SpriteList);
		DATALIST.put("AnimatedSprite", AnimatedSpriteList);
		
		//Audio
	}
	
	public static void loadResources(){
		enemyDataList = db.loadTable("Enemy");
		DroneDataList = db.loadTable("Drone");
		powerDataList = db.loadTable("Power");
		DifficultyDataList = db.loadTable("Difficulty");

		SpriteList = loadSprite(new File("PATH"));
		AnimatedSpriteList = loadAnimatedSprite(new File("PATH"));
		
		
	}
	
	private static HashMap<String, BufferedImage[]> loadAnimatedSprite(File folder) {
		BufferedImage[] animatedSprite = null;
		
		int index_counter = 0;
		for (int y_counter = 0; y_counter < y_max; y_counter++){
			for (int x_counter = 0; x_counter < x_max ; x_counter++){
				animatedSprite[index_counter] = Sprite.getSprite(x_counter, y_counter);
			}
			
		}
		
		return null;
	}

	private static void loadSprite(File folder) {
		for (File fileEntry : folder.listFiles()) {
			SpriteList.put(fileEntry.getName(),GameUtils.loadImage(fileEntry.getPath()));
	    }
	}

	public static void resetResources(){
		for (String key : DATALIST.keySet()) {
		    DATALIST.get(key).clear();
		}
	}
		
	public static <T> T get(String List, String Key){
		return (T) DATALIST.get(List).get(Key);
	}
}
