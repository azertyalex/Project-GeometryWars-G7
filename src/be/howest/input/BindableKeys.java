package be.howest.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BindableKeys extends KeyAdapter{
	private Map<String,Integer> binds = new HashMap<>(); 
	
	public BindableKeys(){
		defaultBindings();
	}
	
	
	
	private void defaultBindings(){
		binds.put("up", KeyEvent.VK_Z);
		binds.put("down", KeyEvent.VK_S);
		binds.put("left", KeyEvent.VK_Q);
		binds.put("right", KeyEvent.VK_D);
		binds.put("backspace",KeyEvent.VK_BACK_SPACE);
	}
	
	public Map<String,Integer> getBindings(){
		return binds;
	}
	
	public int getKey(String event){
		if(binds.containsKey(event)) return binds.get(event);
		return 0;
		
	}
	
	public String getEvent(int pressedKey){
		Set<String> keyset = new HashSet<>();
		keyset = binds.keySet();
		
		if(binds.containsValue(pressedKey)){
			for (String tempKey : keyset) {
				if(binds.get(tempKey) == pressedKey){
					return tempKey;
				}
			}
			
		}
		return "";
	}
	
	
	public void bindKey(String event, int key){

		
		if(!getEvent(key).isEmpty()){
			binds.remove(getEvent(key));
		}
		
		binds.put(event, key);
			
		
	}
	

	
	

}
