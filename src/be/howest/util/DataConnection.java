package be.howest.util;

import be.howest.difficulty.Difficulty;
import be.howest.util.MyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

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
               Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
           }
		return null;
    }
    
}
