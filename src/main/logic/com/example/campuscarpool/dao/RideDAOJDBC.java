package com.example.campuscarpool.dao;

import com.example.campuscarpool.connection.ConnectionDB;
import com.example.campuscarpool.dao.queries.CRUDQueries;
import com.example.campuscarpool.dao.queries.RetrieveQueries;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.model.Ride;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Implementazione del RideDAO usando JDBC per l'accesso ai dati delle corse
public class RideDAOJDBC extends RideDAO {
    // Costanti per specificare i nomi delle colonne
    private static final String ID = "idRide";
    private static final String DATE = "departureDate";
    private static final String TIME = "departureTime";
    private static final String DEPARTURE_LOCATION = "departureLocation";
    private static final String DESTINATION_LOCATION = "destinationLocation";
    private static final String AVAILABLE_SEATS = "availableSeats";
    private static final String DRIVER_FIRST_NAME = "driverFirstName";
    private static final String DRIVER_LAST_NAME = "driverLastName";
    private static final String DRIVER_EMAIL = "driverEmail";
    private static final String DRIVER_PHONE = "driverPhoneNumber";

    // Per recuperare una corsa dal database in base al suo ID
    @Override
    public Ride findRideById(int idRide) throws NotFoundException {
        Connection connection;
        Ride ride = null;

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveRidesById(connection, idRide);

            if (!resultSet.first()) {
                throw new NotFoundException("No ride found with id: " + idRide);
            }

            resultSet.first();
            do {
                ride = setRideData(resultSet);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return ride;
    }

    // Per recuperare una lista di viaggi in base a dei parametri di ricerca
    @Override
    public List<Ride> retrieveRides(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) throws NotFoundException {
        Connection connection;
        Ride ride;
        List<Ride> rideList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveRidesByInfo(connection, departureDate, departureTime, departureLocation, destinationLocation);

            if (!resultSet.first()) {
                throw new NotFoundException("No rides found with this information.");
            }

            resultSet.first();

            do {
                ride = setRideData(resultSet);
                rideList.add(ride);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return rideList;
    }

    // Per estrarre i dati da un ResultSet e restituire un nuovo oggetto Ride
    private Ride setRideData(ResultSet resultSet) throws SQLException {
        int idRide = resultSet.getInt(ID);
        Date departureDate = resultSet.getDate(DATE);
        Time departureTime = resultSet.getTime(TIME);
        String departureLocation = resultSet.getString(DEPARTURE_LOCATION);
        String destinationLocation = resultSet.getString(DESTINATION_LOCATION);
        int availableSeats = resultSet.getInt(AVAILABLE_SEATS);
        String driverFirstName = resultSet.getString(DRIVER_FIRST_NAME);
        String driverLastName = resultSet.getString(DRIVER_LAST_NAME);
        String driverEmail = resultSet.getString(DRIVER_EMAIL);
        String driverPhone = resultSet.getString(DRIVER_PHONE);

        Ride ride = new Ride(idRide, departureDate.toLocalDate(), departureTime.toLocalTime(), departureLocation, destinationLocation, availableSeats);
        ride.setRideDriverInfo(driverFirstName, driverLastName, driverEmail, driverPhone);

        return ride;
    }

    // Per aggiornare il numero di posti disponibili di una corsa
    @Override
    public void updateRideAvailableSeats(int idRide) throws MessageException {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveRidesAvailableSeats(connection, idRide);
            resultSet.first();

            int currentSeats = resultSet.getInt(1);

            resultSet.close();

            // Se ci sono ancora posti disponibili, il valore viene aggiornato
            if (currentSeats > 0) {
                CRUDQueries.updateRideAvailableSeats(connection, idRide);
            } else {
                throw new MessageException("WARNING: you accepted a new request \neven if there are no more\nseats available!");
            }
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }
}