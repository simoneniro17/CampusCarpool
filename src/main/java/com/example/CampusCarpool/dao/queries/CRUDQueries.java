package com.example.CampusCarpool.dao.queries;

import com.example.CampusCarpool.model.Ride;
import com.example.CampusCarpool.model.RideRequest;

import java.sql.*;
import java.time.LocalDate;

public class CRUDQueries {

    private static PreparedStatement preparedStatement = null;

    private CRUDQueries() {

    }

    public static int insertUser(Connection connection, String email, String password, String role) throws SQLException {

        String insertStatement = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";

        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, role);

        return preparedStatement.executeUpdate();
    }

    public static int insertDriver(Connection connection, String firstName, String lastName, LocalDate dateOfBirth,
                                  char gender, String email, String phoneNumber) throws SQLException {

        String insertStatement = "INSERT INTO driver (firstName, lastName, dateOfBirth, gender, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, String.valueOf(dateOfBirth));
        preparedStatement.setString(4, String.valueOf(gender));
        preparedStatement.setString(5, email);
        preparedStatement.setString(6, phoneNumber);

        return preparedStatement.executeUpdate();
    }

    public static int insertNewRide(Connection connection, Ride ride) throws SQLException {
        String insertStatement = "INSERT INTO ride (departureDate, departureTime, departureLocation, destinationLocation, availableSeats," +
                "driverFirstName, driverLastName, driverEmail, driverPhoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setDate(1, ride.getDepartureDate());
        preparedStatement.setTime(2, Time.valueOf(ride.getDepartureTime()));
        preparedStatement.setString(3, ride.getDepartureLocation());
        preparedStatement.setString(4, ride.getDestinationLocation());
        preparedStatement.setInt(5, ride.getAvailableSeats());
        preparedStatement.setString(6, ride.getDriverFirstName());
        preparedStatement.setString(7, ride.getDriverLastName());
        preparedStatement.setString(8, ride.getDriverEmail());
        preparedStatement.setString(9, ride.getDriverPhoneNumber());

        return preparedStatement.executeUpdate();
    }

    public static int insertRideRequest(Connection connection, int idRide, String passengerEmail) throws SQLException {
        String insertStatement = "INSERT INTO ride_request (idRide, passengerEmail, status) VALUES (?, ?, 0)";

        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, idRide);
        preparedStatement.setString(2, passengerEmail);

        return preparedStatement.executeUpdate();
    }

    public static int updateStatusRideRequest(Connection connection, int idRideRequest, int status) throws SQLException {
        String updateStatement = "UPDATE ride_request SET status = ? WHERE idRideRequest = ?";

        preparedStatement = connection.prepareStatement(updateStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, status);
        preparedStatement.setInt(2, idRideRequest);

        return preparedStatement.executeUpdate();
    }

    public static int updateRideAvailableSeats(Connection connection, int idRide) throws SQLException {
        String updateStatement = "UPDATE ride SET (availableSeats = availableSeats - 1) WHERE idRide = ?";

        preparedStatement = connection.prepareStatement(updateStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, idRide);

        return preparedStatement.executeUpdate();
    }



    public static int deleteRideRequest(Connection connection, int idRideRequest) throws SQLException {
        String deleteStatement = "DELETE FROM ride_request WHERE idRideRequest = ?";

        preparedStatement = connection.prepareStatement(deleteStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, idRideRequest);

        return preparedStatement.executeUpdate();
    }
}
