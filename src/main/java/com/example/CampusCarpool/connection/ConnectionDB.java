package com.example.CampusCarpool.connection;

import com.example.CampusCarpool.engineering.Printer;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.*;

import java.util.Properties;

public class ConnectionDB {

    private ConnectionDB() {}

    private static Connection connection;

    public static Connection getConnection() {
        String user;
        String password;
        String url;
        String driverClassName;

        if (connection == null) {
            try{
                Properties db = loadProperties();
                url = db.getProperty("CONNECTION_URL");
                driverClassName = db.getProperty("DRIVER_CLASS_NAME");
                user = db.getProperty("USER");
                password = db.getProperty("PASS");
                Class.forName(driverClassName);

                connection = DriverManager.getConnection(url,user, password);

            } catch (SQLException | IOException | ClassNotFoundException e) {
                Printer.printError(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();

        //closing the resource fileInputStream is handled automatically by the try-with-resources
        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/com/example/CampusCarpool/connection/db.properties")){
            properties.load(fileInputStream);
        }
        return properties;
    }
}
