package be.howest.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

import javax.imageio.ImageIO;

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
	
	public static BufferedImage loadImage(String path){
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(path));
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return image;
	}

	public static void test(){
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile("resources\\resourcepack.zip");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    Enumeration<? extends ZipEntry> entries = zipFile.entries();

	    while(entries.hasMoreElements()){
	        ZipEntry entry = entries.nextElement();
	        try {
				InputStream stream = zipFile.getInputStream(entry);
				System.out.println(stream.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    try {
			zipFile.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	}
}
