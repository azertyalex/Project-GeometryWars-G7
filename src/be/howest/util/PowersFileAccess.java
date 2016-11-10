package be.howest.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import be.howest.objects.Barrier;
import be.howest.objects.Blackout;
import be.howest.objects.Clone;
import be.howest.objects.Disorient;
import be.howest.objects.EMP;
import be.howest.objects.Exhaust;
import be.howest.objects.Heal;
import be.howest.objects.MultipleCannons;
import be.howest.objects.Powers;
import be.howest.objects.Revive;
import be.howest.objects.SlowTime;

public class PowersFileAccess {

    public List<Powers> load(String fileName) {
    	List<Powers> powerList = new ArrayList<>();
    	
        try {
        	Powers power = null;
            Scanner s = new Scanner( new File(fileName) );
            
            while(s.hasNext()){
            String name  = s.nextLine();
            int spawnrate = Integer.parseInt(s.nextLine());
            
            if(name.equals("Barrier")){
             	 power = new Barrier(name, spawnrate);
             	 powerList.add(power);
              } else if(name.equals("Blackout")) {
             	 power = new Blackout(name, spawnrate);
             	powerList.add(power);
              } else if(name.equals("Disorient")) {
             	 power = new Disorient(name, spawnrate);
             	powerList.add(power);
              } else if(name.equals("Exhaust")) {
             	 power = new Exhaust(name, spawnrate);
             	powerList.add(power);
              } else if(name.equals("Heal")) {
             	 power = new Heal(name, spawnrate);
             	powerList.add(power);
              } else if(name.equals("MultipleCannons")) {
             	 power = new MultipleCannons(name, spawnrate);
             	powerList.add(power);
              } else if (name.equals("Revive")) {
             	 power = new Revive(name, spawnrate);
             	powerList.add(power);
              } else if (name.equals("SlowTime")) {
             	 power = new SlowTime(name, spawnrate);
             	powerList.add(power);
              } else if (name.equals("Clone")) {
             	 power = new Clone(name, spawnrate);
             	powerList.add(power);
              } else if (name.equals("EMP")) {
             	 power = new EMP(name, spawnrate);
             	powerList.add(power);
              }
            }
            
            
            s.close();
            
            
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Cannot load file",ex);
        }
        return powerList;
    }

    public void save(List<Powers> powers, String fileName) {
        try {
            PrintStream out = new PrintStream(fileName);
            
            for (int i = 0; i < powers.size(); i++){
            	
            	out.println(powers.get(i).getName());
            	out.println(powers.get(i).getSpawnrate());
            
            }

            
            out.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Cannot save file",ex);
        }
    }
}
