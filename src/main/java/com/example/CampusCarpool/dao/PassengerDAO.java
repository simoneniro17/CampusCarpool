package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.connection.ConnectionDB;
import com.example.CampusCarpool.dao.queries.RetrieveQueries;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.model.Passenger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

// Gestisce l'accesso ai dati dei passeggeri
public class PassengerDAO {
    // Costanti per i nomi delle colonne
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String GENDER = "gender";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";

    private PassengerDAO() {
    }

    // Per cercare un passeggero in base al suo username
    public static Passenger findPassengerByUsername(String username) throws NotFoundException {
        Connection connection;
        Passenger passenger = null;

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrievePassengerByUsername(connection, username);

            if (!resultSet.first()) {
                throw new NotFoundException("No passenger found with username: " + username);
            }

            resultSet.first();
            do {
                passenger = setPassengerData(resultSet);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return passenger;
    }

    // Estrae i dati dal ResultSet e restituisce un nuovo oggetto Passenger
    public static Passenger setPassengerData(ResultSet resultSet) throws SQLException {
        String passengerFirstName = resultSet.getString(FIRST_NAME);
        String passengerLastName = resultSet.getString(LAST_NAME);
        LocalDate passengerDateOfBirth = resultSet.getDate(DATE_OF_BIRTH).toLocalDate();
        char passengerGender = resultSet.getString(GENDER).charAt(0);
        String passengerEmail = resultSet.getString(EMAIL);
        String passengerPhoneNumber = resultSet.getString(PHONE_NUMBER);

        return new Passenger(passengerFirstName, passengerLastName, passengerDateOfBirth, passengerGender, passengerEmail, passengerPhoneNumber);
    }
}