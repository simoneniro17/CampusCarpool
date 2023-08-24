package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.connection.ConnectionDB;
import com.example.CampusCarpool.dao.queries.RetrieveQueries;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.exception.UserNotFoundException;
import com.example.CampusCarpool.model.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

// Implementazione del LoginDAO usando JDBC per l'accesso ai dati degli utenti
public class LoginDAOJDBC implements LoginDAO{
    @Override
    public UserProfile checkUser(String username, String password) throws UserNotFoundException {
        Connection connection;
        int role;
        UserProfile userProfile = null;

        try {
            // Ottenimento connessione al database
            connection = ConnectionDB.getConnection();

            // Esecuzione query per verificare le credenziali
            ResultSet resultSet = RetrieveQueries.checkUser(connection, username, password);

            // L'utente non è stato trovato
            if (!resultSet.isBeforeFirst()){
                throw new UserNotFoundException();
            }

            // Recupero prima riga del ResultSet
            resultSet.first();

            // Recupero nome del ruolo e assegnazione valore numerico corrispondente al ruolo
            String nameRole = resultSet.getString(1);
            switch (nameRole){
                case "Driver"-> role = 1;
                case "Passenger" -> role = 2;
                default -> throw new NotFoundException("No role found");
            }

            // Chiusura ResultSet
            resultSet.close();

            // Creazione oggetto UserProfile
            userProfile = new UserProfile(role, username);

        } catch (NotFoundException | SQLException e){
            Printer.printError(e.getMessage());
        }

        return userProfile;
    }
}