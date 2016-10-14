package be.howest.game;

public class GameUtils {
	
	public static <T extends Comparable<T>> T clamp(T val, T min, T max) {
	    if (val.compareTo(min) < 0) return min;
	    else if (val.compareTo(max) > 0) return max;
	    else return val;
	}
	
	public static <T extends Comparable<T>> T clamp(T val, T min, T max, T standard) {
	    if (val.compareTo(min) < 0 || val.compareTo(max) > 0) return standard;
	    else return val;
	}

}
