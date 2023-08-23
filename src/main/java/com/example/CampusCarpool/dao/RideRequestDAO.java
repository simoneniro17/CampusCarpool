package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.connection.ConnectionDB;
import com.example.CampusCarpool.dao.queries.CRUDQueries;
import com.example.CampusCarpool.dao.queries.RetrieveQueries;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.exception.DuplicateRequestException;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.model.RideRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RideRequestDAO {

    private static final String ID = "idRideRequest";
    private static final String ID_RIDE = "idRide";
    private static final String PASSENGER_EMAIL = "passengerEmail";
    private static final String STATUS = "status";

    private RideRequestDAO() {

    }

    public static void newRequest(int idRide, String passengerEmail) throws DuplicateRequestException {

        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveDistinctRequest(connection, idRide, passengerEmail);

            if (!resultSet.first()) {
                CRUDQueries.insertRideRequest(connection, idRide, passengerEmail);
            } else {
                throw new DuplicateRequestException();
            }

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static List<RideRequest> retrievePendingPassengersRequests(String passengerEmail) {

        Connection connection;

        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();


        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrievePendingPassengerRequests(connection, passengerEmail);

            if (!resultSet.first()) {
                throw new NotFoundException("You don't have any pending request!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestsList.add(rideRequest);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestsList;
    }

    public static List<RideRequest> retrieveConfirmedPassengersRequests(String passengerEmail) {

        Connection connection;

        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();


        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveConfirmedPassengerRequests(connection, passengerEmail);

            if (!resultSet.first()) {
                throw new NotFoundException("You don't have any confirmed request!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestsList.add(rideRequest);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestsList;
    }

    public static List<RideRequest> retrieveRejectedPassengersRequests(String passengerEmail) {

        Connection connection;

        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();


        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveRejectedPassengerRequests(connection, passengerEmail);

            if (!resultSet.first()) {
                throw new NotFoundException("You don't have any rejected request!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestsList.add(rideRequest);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestsList;
    }

    public static List<RideRequest> retrievePendingDriverRequests(String driverEmail) {

        Connection connection;

        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();


        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrievePendingDriverRequests(connection, driverEmail);

            if (!resultSet.first()) {
                throw new NotFoundException("No pending request yet!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestsList.add(rideRequest);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestsList;
    }

    public static List<RideRequest> retrieveConfirmedDriverRequests(String driverEmail) {

        Connection connection;

        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();


        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveConfirmedDriverRequests(connection, driverEmail);

            if (!resultSet.first()) {
                throw new NotFoundException("No confirmed request yet!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestsList.add(rideRequest);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestsList;
    }

    public static void updateStatus(int idRideRequest, int status) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateStatusRideRequest(connection, idRideRequest, status);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    private static RideRequest setRideRequestData(ResultSet resultSet) throws NotFoundException, SQLException {
        int idRideRequest = resultSet.getInt(ID);
        int idRide = resultSet.getInt(ID_RIDE);
        String passengerEmail = resultSet.getString(PASSENGER_EMAIL);
        int status = resultSet.getInt(STATUS);

        return new RideRequest(idRideRequest, idRide, passengerEmail, status);
    }
}
