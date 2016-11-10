package be.howest.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;

import be.howest.objects.GameObject;
import be.howest.util.GameLoop;

public class Handler implements GameLoop{
	
	private List<GameObject> object = new LinkedList<GameObject>();

	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			AffineTransform tempGraph = ((Graphics2D) g).getTransform();
			
			GameObject tempObject = object.get(i);
			tempObject.render(g);
			
			((Graphics2D) g).setTransform(tempGraph);;
			
			
			//tempG.dispose();
			
			
		}
		g.dispose();
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
		
	}
	
	public void addObject(List<GameObject> listOfObjects){
		this.object.addAll(listOfObjects);
		
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public void removeObject(List<GameObject> listOfObjects){
		this.object.removeAll(listOfObjects);
		
	}
	
	public List<GameObject> getList(){
		return object;
	}
	
	public GameObject getGameObject(ID id){
		for(int i = 0;i<object.size();i++){
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == id){
				return tempObject;
			}
		}
		return null;
	}
}
