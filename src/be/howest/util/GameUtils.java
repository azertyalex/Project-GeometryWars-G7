package be.howest.util;

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
	
	public static <T extends Comparable<T> > T max(T t1, T t2){
        if(t1.compareTo(t2) > 0){
            return t1;
            
        }else{
            return t2;
        }
    }

}
