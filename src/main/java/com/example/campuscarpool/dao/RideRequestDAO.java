package com.example.campuscarpool.dao;

import com.example.campuscarpool.connection.ConnectionDB;
import com.example.campuscarpool.dao.queries.CRUDQueries;
import com.example.campuscarpool.dao.queries.RetrieveQueries;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.exception.DuplicateRequestException;
import com.example.campuscarpool.model.Ride;
import com.example.campuscarpool.model.RideRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Gestisce l'accesso ai dati delle richieste
public class RideRequestDAO {
    // Costanti per i nomi delle colonne
    private static final String ID = "idRideRequest";
    private static final String ID_RIDE = "idRide";
    private static final String PASSENGER_EMAIL = "passengerEmail";
    private static final String STATUS = "status";

    private RideRequestDAO() {

    }

    // Per aggiungere una nuova richiesta
    public static void newRequest(int idRide, String passengerEmail) throws DuplicateRequestException {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveDistinctRequest(connection, idRide, passengerEmail);

            // Il passeggero non ha già inviato una richiesta per la stessa corsa
            if (!resultSet.first()) {
                CRUDQueries.insertRideRequest(connection, idRide, passengerEmail);
            } else {
                throw new DuplicateRequestException();
            }

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    // Per recuperare la lista delle richieste in sospeso di un passeggero
    public static List<RideRequest> retrievePendingPassengersRequests(String passengerEmail) {
        Connection connection;
        List<RideRequest> rideRequestList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrievePendingPassengerRequests(connection, passengerEmail);

            // Ci sono richieste in sospeso
            requestCheck(resultSet, rideRequestList);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestList;
    }

    // Per recuperare la lista delle richieste confermate di un passeggero
    public static List<RideRequest> retrieveConfirmedPassengersRequests(String passengerEmail) {
        Connection connection;
        List<RideRequest> rideRequestList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveConfirmedPassengerRequests(connection, passengerEmail);

            //  Il passeggero ha delle richieste confermata
            requestCheck(resultSet, rideRequestList);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestList;
    }

    // Per recuperare la lista delle richieste rifiutate di un passeggero
    public static List<RideRequest> retrieveRejectedPassengersRequests(String passengerEmail) {
        Connection connection;
        List<RideRequest> rideRequestList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveRejectedPassengerRequests(connection, passengerEmail);

            //  Il passeggero ha delle richieste rifiutate
            requestCheck(resultSet, rideRequestList);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestList;
    }


    // Per recuperare la lista delle richieste di corse future in sospeso a un guidatore
    public static List<RideRequest> retrievePendingDriverRequests(String driverEmail) {
        Connection connection;
        List<RideRequest> rideRequestList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrievePendingDriverRequests(connection, driverEmail);

            //  Il guidatore ha ricevuto richieste
            requestCheck(resultSet, rideRequestList);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestList;
    }


    // Per recuperare la lista delle richieste di corse future confermate da un guidatore
    public static List<RideRequest> retrieveConfirmedDriverRequests(String driverEmail) {
        Connection connection;
        List<RideRequest> rideRequestList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveConfirmedDriverRequests(connection, driverEmail);

            //  Il guidatore ha confermato richieste per corse future
            requestCheck(resultSet, rideRequestList);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestList;
    }

    // Per aggiornare lo stato di una richiesta
    public static void updateStatus(int idRideRequest, int status) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateStatusRideRequest(connection, idRideRequest, status);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    // Estrae i dati dal ResultSet e restituisce un nuovo oggetto RideRequest
    private static RideRequest setRideRequestData(ResultSet resultSet) throws SQLException {
        int idRideRequest = resultSet.getInt(ID);
        int idRide = resultSet.getInt(ID_RIDE);
        String passengerEmail = resultSet.getString(PASSENGER_EMAIL);
        int status = resultSet.getInt(STATUS);

        return new RideRequest(idRideRequest, idRide, passengerEmail, status);
    }

    private static void requestCheck(ResultSet resultSet, List<RideRequest> rideRequestList) throws SQLException {
        RideRequest rideRequest;

        if(resultSet.first()) {
            resultSet.first();
            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestList.add(rideRequest);
            } while (resultSet.next());
        }
        resultSet.close();
    }
}