package be.howest.util;

import be.howest.util.MyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    
}
