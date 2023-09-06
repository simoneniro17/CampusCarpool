package com.example.campuscarpool.dao.queries;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class RetrieveQueries {

    private static PreparedStatement preparedStatement = null;

    private RetrieveQueries() {
    }

    public static ResultSet checkUser(Connection connection, String username, String password) throws SQLException {
        String sql = "SELECT role FROM user WHERE username = ? AND password = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveDriverByUsername(Connection connection, String username) throws SQLException {
        String sql = "SELECT * FROM driver WHERE email = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, username);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrievePassengerByUsername(Connection connection, String username) throws SQLException {
        String sql = "SELECT * FROM passenger WHERE email = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, username);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveDistinctRide(Connection connection, Date departureDate, Time departureTime, String driverEmail) throws SQLException {
        String sql = "SELECT idRide FROM ride WHERE ((departureDate = ? AND departureTime = ?) AND driverEmail = ?)";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setDate(1, departureDate);
        preparedStatement.setTime(2, departureTime);
        preparedStatement.setString(3, driverEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveRidesAvailableSeats(Connection connection, int idRide) throws SQLException {
        String sql = "SELECT availableSeats FROM ride WHERE idRide = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, idRide);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveRidesById(Connection connection, int idRide) throws SQLException {
        String sql = "SELECT * FROM ride WHERE idRide = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, idRide);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveRidesByInfo(Connection connection, LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) throws SQLException {
        String sql = "SELECT * FROM ride WHERE (departureDate = ? AND departureTime >= ? AND departureLocation = ? AND destinationLocation = ? AND availableSeats > 0)";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setDate(1, Date.valueOf(departureDate));
        preparedStatement.setTime(2, Time.valueOf(departureTime));
        preparedStatement.setString(3, departureLocation);
        preparedStatement.setString(4, destinationLocation);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveIdRide(Connection connection, Date departureDate, Time departureTime, String driverEmail) throws SQLException {
        String sql = "SELECT idRide FROM ride WHERE (departureDate = ? AND departureTime = ? AND driverEmail = ?)";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setDate(1, departureDate);
        preparedStatement.setTime(2, departureTime);
        preparedStatement.setString(3, driverEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveRideRequest(Connection connection) throws SQLException {
        String sql = "SELECT * FROM ride_request WHERE (CURRENT_DATE() <= departureDate AND CURRENT_TIME() <= departureTime)";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveDistinctRequest(Connection connection, int idRide, String passengerEmail) throws SQLException {
        String sql = "SELECT idRideRequest FROM ride_request WHERE (idRide = ? AND passengerEmail = ?)";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, idRide);
        preparedStatement.setString(2, passengerEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveRideRequestsByIdRide(Connection connection, int idRide) throws SQLException {
        String sql = "SELECT idRideRequest, passengerEmail, status FROM ride_request WHERE idRide = ?";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setInt(1, idRide);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrievePendingDriverRequests(Connection connection, String driverEmail) throws SQLException {
        String sql = "SELECT *\n" +
                "FROM ride_request rr\n" +
                "JOIN ride r ON rr.idRide = r.idRide\n" +
                "JOIN driver d ON r.driverEmail = d.email\n" +
                "WHERE (d.email = ? AND rr.status = 0 AND r.departureDate > CURRENT_DATE())" +
                "ORDER BY r.departureDate";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, driverEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveConfirmedDriverRequests(Connection connection, String driverEmail) throws SQLException {
        String sql = "SELECT *\n" +
                "FROM ride_request rr\n" +
                "JOIN ride r ON rr.idRide = r.idRide\n" +
                "JOIN driver d ON r.driverEmail = d.email\n" +
                "WHERE (d.email = ? AND rr.status = 1 AND r.departureDate > CURRENT_DATE())" +
                "ORDER BY r.departureDate";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, driverEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrievePendingPassengerRequests(Connection connection, String passengerEmail) throws SQLException {
        String sql = "SELECT * FROM ride_request WHERE (passengerEmail = ? AND status = 0)";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, passengerEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveConfirmedPassengerRequests(Connection connection, String passengerEmail) throws SQLException {
        String sql = "SELECT * FROM ride_request WHERE (passengerEmail = ? AND status = 1)";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, passengerEmail);

        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveRejectedPassengerRequests(Connection connection, String passengerEmail) throws SQLException {
        String sql = "SELECT * FROM ride_request WHERE (passengerEmail = ? AND status = 2)";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        preparedStatement.setString(1, passengerEmail);

        return preparedStatement.executeQuery();
    }
}
