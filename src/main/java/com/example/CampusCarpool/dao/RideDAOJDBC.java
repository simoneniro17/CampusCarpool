package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.connection.ConnectionDB;
import com.example.CampusCarpool.dao.queries.CRUDQueries;
import com.example.CampusCarpool.dao.queries.RetrieveQueries;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.exception.MessageException;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.model.Ride;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RideDAOJDBC extends RideDAO {
    private static final String ID = "idRide";
    private static final String DATE = "departureDate";
    private static final String TIME = "departureTime";
    private static final String DEPARTURE_LOCATION = "departureLocation";
    private static final String DESTINATION_LOCATION = "destinationLocation";
    private static final String AVAILABLE_SEATS = "availableSeats";
    private static final String DRIVER_FIRST_NAME = "driverFirstName";
    private static final String DRIVER_LAST_NAME= "driverLastName";
    private static final String DRIVER_EMAIL = "driverEmail";
    private static final String DRIVER_PHONE = "driverPhoneNumber";

    @Override
    public Ride findRideById(int idRide) {
        Connection connection;
        Ride ride = null;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveRidesById(connection, idRide);

            if(!resultSet.first()){
                throw new NotFoundException("No ride found with id: " + idRide);
            }

            resultSet.first();
            do {
                ride = setRideData(resultSet);
            } while(resultSet.next());

            resultSet.close();
        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return ride;
    }

    @Override
    public List<Ride> retrieveRides(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) {
        Connection connection;
        Ride ride;
        List<Ride> rideList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveRidesByInfo(connection, departureDate, departureTime, departureLocation, destinationLocation);

            if(!resultSet.first()) {
                throw new NotFoundException("No rides found with this information.");
            }

            resultSet.first();

            do {
                ride = setRideData(resultSet);
                rideList.add(ride);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideList;
    }

    private Ride setRideData(ResultSet resultSet) throws NotFoundException, SQLException {
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

        Ride ride = new Ride(idRide, departureDate.toLocalDate(), departureTime.toLocalTime(), departureLocation, destinationLocation, availableSeats,
                driverFirstName, driverLastName, driverEmail, driverPhone);

        return ride;
    }

    @Override
    public void updateRideAvailableSeats(int idRide) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveRidesAvailableSeats(connection, idRide);
            resultSet.first();

            int currentSeats = resultSet.getInt(0);

            resultSet.close();

            if(currentSeats > 0) {
                CRUDQueries.updateRideAvailableSeats(connection, idRide);
            } else {
                throw new MessageException("There are no more seats available!");
            }
        } catch (SQLException | MessageException e) {
            Printer.printError(e.getMessage());
        }
    }
}
