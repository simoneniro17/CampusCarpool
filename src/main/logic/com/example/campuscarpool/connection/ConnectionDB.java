package com.example.campuscarpool.connection;

import com.example.campuscarpool.engineering.Printer;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.*;

import java.util.Properties;

// Per gestire la connessione al database
public class ConnectionDB {

    // Costruttore privato per evitare istanze esterne
    private ConnectionDB() {
    }

    private static Connection connection;

    // Per ottenere la connessione al database
    public static Connection getConnection() {
        String user;
        String password;
        String url;
        String driverClassName;

        // Creazione connessione se non esistente
        if (connection == null) {
            try {
                Properties db = loadProperties();
                url = db.getProperty("CONNECTION_URL");
                driverClassName = db.getProperty("DRIVER_CLASS_NAME");
                user = db.getProperty("USER");
                password = db.getProperty("PASS");
                Class.forName(driverClassName);

                connection = DriverManager.getConnection(url, user, password);

            } catch (SQLException | IOException | ClassNotFoundException e) {
                Printer.printError(e.getMessage());
            }
        }
        return connection;
    }

    // Per chiudere la connessione al database
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    // Per caricare le proprietà del database da un file db.properties
    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("src/main/logic/com/example/CampusCarpool/connection/db.properties")) {
            properties.load(fileInputStream);
        }

        return properties;
    }
}