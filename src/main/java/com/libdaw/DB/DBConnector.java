package com.libdaw.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    private static DBConnector instance = null;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/libdaw?autoReconnect=true&useSSL=false";
    private Connection connection;

    public DBConnector() {
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, prop);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("ERROR: "+ e.getStackTrace());
        }
    }

    public static DBConnector GetInstance() {
        if (instance == null) {
            instance = new DBConnector();
            System.out.println("Instancia creada...");
            System.out.println(instance.connection != null);
        }

        return instance;
    }

    public Connection GetConnection() {
        return this.connection;
    }

}
