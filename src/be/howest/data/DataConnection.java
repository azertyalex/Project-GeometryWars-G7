package be.howest.data;
import be.howest.objects.GameObject;
import be.howest.objects.powers.*;
import be.howest.util.MyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
public class DataConnection {
    private static DataConnection instance;
    private static final String USER = "geometry";
    private static final String PASS = "warsG7";
    private static final String URL = "jdbc:mysql://178.117.108.9:3306/geometry";
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    private Connection connection;
    
    
    private DataConnection(){
        this.initDriver();
        this.initConnection();
    }
    
    
    public static DataConnection getInstance(){
        if(instance == null) instance =  new DataConnection();
        return instance;
    }
    
    private void initDriver(){
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new MyException("Driver not found",ex);
        }
    }
    
    private void initConnection(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new MyException("Connection with database failed!",ex);
        }
        
    }
    
    public Difficulty getDifficulty()
    {
         try {
            
            String sql = "select name, spawnRate, healthRate, damageRate, fireRateAmplifier, speed from Difficulty";
               
            Statement stmt = connection.createStatement();
               
            ResultSet rs = stmt.executeQuery(sql);
               
            while (rs.next())
            {
             String name = rs.getString("name");
             float spawnRate = rs.getFloat("spawnRate");
             float healthRate = rs.getFloat("healthRate");
             float damageRate = rs.getFloat("damageRate");
             float fireRateAmplifier = rs.getFloat("fireRateAmplifier");
             float speed = rs.getFloat("speed");
             
                   
             
             return new Difficulty(name, spawnRate, healthRate, damageRate, fireRateAmplifier, speed);
             
            }
               
               rs.close();
               stmt.close();
               
           } catch (SQLException ex) {
               throw new MyException("Cannot find difficulties.", ex);
           }
        return null;
    }
    
    public List<Powers> getPowers()
    {
        List<Powers> powerList = new ArrayList<>();
         try {
            
            String sql = "select name, spawnRate from Power";
               
            Statement stmt = connection.createStatement();
               
            ResultSet rs = stmt.executeQuery(sql);
               
            while (rs.next())
            {
             String name = rs.getString("name");
             int spawnRate = rs.getInt("spawnRate");
             
             if(name.equals("Barrier")){
                 Powers power = new Barrier(name, spawnRate);
                 powerList.add(power);
             } else if(name.equals("Blackout")) {
                 Powers power = new Blackout(name, spawnRate);
                 powerList.add(power);
             } else if(name.equals("Disorient")) {
                 Powers power = new Disorient(name, spawnRate);
                 powerList.add(power);
             } else if(name.equals("Exhaust")) {
                 Powers power = new Exhaust(name, spawnRate);
                 powerList.add(power);
             } else if(name.equals("Heal")) {
                 Powers power = new Heal(name, spawnRate);
                 powerList.add(power);
             } else if(name.equals("MultipleCannons")) {
                 Powers power = new MultipleCannons(name, spawnRate);
                 powerList.add(power);
             } else if (name.equals("Revive")) {
                 Powers power = new Revive(name, spawnRate);
                 powerList.add(power);
             } else if (name.equals("SlowTime")) {
                 Powers power = new SlowTime(name, spawnRate);
                 powerList.add(power);
             } else if (name.equals("Clone")) {
                 Powers power = new Clone(name, spawnRate);
                 powerList.add(power);
             } else if (name.equals("EMP")) {
                 Powers power = new EMP(name, spawnRate);
                 powerList.add(power);
             }
             
             
             
            }
               
               rs.close();
               stmt.close();
               
           } catch (SQLException ex) {
        	   throw new MyException("Cannot find Powers.", ex);
           }
        return powerList;
    }
    
    public List<GameObject> getEnemies()
    {
        /*List<GameObject> enemyList = new ArrayList<>();
         try {
            
            String sql = "select name, health, damage, fireRate from Enemy";
               
            Statement stmt = connection.createStatement();
               
            ResultSet rs = stmt.executeQuery(sql);
               
            while (rs.next())
            {
             String name = rs.getString("name");
             int health = rs.getInt("health");
             int damage = rs.getInt("damage");
             int fireRate = rs.getInt("fireRate");
             
             if(name.equals("Grunt")){
                 GameObject enemy = new Grunt(name,health, damage, fireRate);
                 enemyList.add(enemy);
             } else if(name.equals("Dart")) {
                 GameObject enemy = new Dart(name,health, damage, fireRate);
                 enemyList.add(enemy);
             } else if(name.equals("Wanderer")){
                 GameObject enemy = new Wanderer(name,health, damage, fireRate);
                 enemyList.add(enemy);
             } else if(name.equals("MineLayer")){
                 GameObject enemy = new MineLayer(name,health, damage, fireRate);
                 enemyList.add(enemy);
             }
             
            }
               
               rs.close();
               stmt.close();
               
           } catch (SQLException ex) {
               Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
           }
        return enemyList;*/
             return null;
    }
    
    public List<GameObject> getDrones()
    {
        /*List<GameObject> droneList = new ArrayList<>();
         try {
            
            String sql = "select name from Drone JOIN Drone_Info";
            
               //TODO
         
               
             
               
           } catch (SQLException ex) {
               Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
           }
        return droneList;*/
        return null;
    }
    
}