package com.patikadev.Helper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connect=null;

    public Connection connectDB(){
        try {
            this.connect= DriverManager.getConnection(Config.DB_URL,Config.DB_USER,Config.DB_PASSSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.connect;
    }

    public static Connection getInstance(){
        DBConnector db= new DBConnector();
        return db.connectDB();

    }
}
