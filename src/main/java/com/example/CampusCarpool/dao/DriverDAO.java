package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.connection.ConnectionDB;
import com.example.CampusCarpool.dao.queries.CRUDQueries;
import com.example.CampusCarpool.dao.queries.RetrieveQueries;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.model.Driver;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DriverDAO {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String GENDER = "gender";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";

    private DriverDAO() {

    }

    public static Driver findDriverByUsername(String username) throws NotFoundException {
        Connection connection;

        Driver driver = null;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveDriverByUsername(connection, username);

            if (!resultSet.first()) {
                throw new NotFoundException("No driver found with username: " + username);
            }

            resultSet.first();
            do {
                driver = setDriverData(resultSet);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return driver;
    }

    public static Driver setDriverData(ResultSet resultSet) throws SQLException {
        String driverFirstName = resultSet.getString(FIRST_NAME);
        String driverLastName = resultSet.getString(LAST_NAME);
        LocalDate driverDateOfBirth = resultSet.getDate(DATE_OF_BIRTH).toLocalDate();
        char driverGender = resultSet.getString(GENDER).charAt(0);
        String driverEmail = resultSet.getString(EMAIL);
        String driverPhoneNumber = resultSet.getString(PHONE_NUMBER);

        Driver driver = new Driver(driverFirstName, driverLastName, driverDateOfBirth, driverGender, driverEmail, driverPhoneNumber);

        return driver;
    }
}
