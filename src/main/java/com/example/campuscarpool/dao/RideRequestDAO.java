package com.example.campuscarpool.dao;

import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.connection.ConnectionDB;
import com.example.campuscarpool.dao.queries.CRUDQueries;
import com.example.campuscarpool.dao.queries.RetrieveQueries;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.exception.DuplicateRequestException;
import com.example.campuscarpool.exception.NotFoundException;
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
    /* $$$
    public static List<RideRequest> retrievePendingPassengersRequests(String passengerEmail) {
        Connection connection;
        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrievePendingPassengerRequests(connection, passengerEmail);

            // Non ci sono richieste in sospeso
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

     */

    // Per recuperare la lista delle richieste confermate di un passeggero
    /* $$$ public static List<RideRequest> retrieveConfirmedPassengersRequests(String passengerEmail) {
        Connection connection;
        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveConfirmedPassengerRequests(connection, passengerEmail);

            // Non ci sono richieste confermate
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

     */

    public static List<RideRequestBean> retrievePendingPassengersRequests(String passengerEmail) {
        Connection connection;
        RideRequest rideRequest;
        RideRequestBean rideRequestBean;
        List<RideRequestBean> rideRequestBeanList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrievePendingPassengerRequests(connection, passengerEmail);

            // Non ci sono richieste in sospeso
            if (!resultSet.first()) {
                throw new NotFoundException("You don't have any pending request!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestBean = new RideRequestBean(rideRequest.getIdRideRequest(), rideRequest.getIdRide(),
                        rideRequest.getPassengerEmail(), rideRequest.getStatus());
                rideRequestBeanList.add(rideRequestBean);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestBeanList;
    }

    // Per recuperare la lista delle richieste confermate di un passeggero
    public static List<RideRequestBean> retrieveConfirmedPassengersRequests(String passengerEmail) {
        Connection connection;
        RideRequestBean rideRequestBean;
        RideRequest rideRequest;
        List<RideRequestBean> rideRequestBeanList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveConfirmedPassengerRequests(connection, passengerEmail);

            // Non ci sono richieste confermate
            if (!resultSet.first()) {
                throw new NotFoundException("You don't have any confirmed request!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestBean = new RideRequestBean(rideRequest.getIdRideRequest(), rideRequest.getIdRide(),
                        rideRequest.getPassengerEmail(), rideRequest.getStatus());
                rideRequestBeanList.add(rideRequestBean);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestBeanList;
    }

    public static List<RideRequestBean> retrieveRejectedPassengersRequests(String passengerEmail) {
        Connection connection;
        RideRequest rideRequest;
        RideRequestBean rideRequestBean;
        List<RideRequestBean> rideRequestBeanList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveRejectedPassengerRequests(connection, passengerEmail);

            // Non ci sono richieste rifiutate
            if (!resultSet.first()) {
                throw new NotFoundException("You don't have any rejected request!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestBean = new RideRequestBean(rideRequest.getIdRideRequest(), rideRequest.getIdRide(),
                        rideRequest.getPassengerEmail(), rideRequest.getStatus());
                rideRequestBeanList.add(rideRequestBean);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestBeanList;
    }

    // Per recuperare la lista delle richieste rifiutate di un passeggero
    /* $$$ public static List<RideRequest> retrieveRejectedPassengersRequests(String passengerEmail) {
        Connection connection;
        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveRejectedPassengerRequests(connection, passengerEmail);

            // Non ci sono richieste rifiutate
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

     */
    // Per recuperare la lista delle richieste di corse future in sospeso a un guidatore
    /*$$$public static List<RideRequest> retrievePendingDriverRequests(String driverEmail) {
        Connection connection;
        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrievePendingDriverRequests(connection, driverEmail);

            // Il guidatore non ha ricevuto richieste
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
    }*/

    public static List<RideRequestBean> retrievePendingDriverRequests(String driverEmail) {
        Connection connection;
        RideRequestBean rideRequestBean;
        RideRequest rideRequest;
        List<RideRequestBean> rideRequestBeanList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrievePendingDriverRequests(connection, driverEmail);

            // Il guidatore non ha ricevuto richieste
            if (!resultSet.first()) {
                throw new NotFoundException("No pending request yet!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestBean = new RideRequestBean(rideRequest.getIdRideRequest(), rideRequest.getIdRide(),
                        rideRequest.getPassengerEmail(), rideRequest.getStatus());
                rideRequestBeanList.add(rideRequestBean);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestBeanList;
    }

    public static List<RideRequestBean> retrieveConfirmedDriverRequests(String driverEmail) {
        Connection connection;
        RideRequestBean rideRequestBean;
        RideRequest rideRequest;
        List<RideRequestBean> rideRequestBeanList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveConfirmedDriverRequests(connection, driverEmail);

            // Il guidatore non ha confermato richieste per corse future
            if (!resultSet.first()) {
                throw new NotFoundException("No confirmed request yet!");
            }

            resultSet.first();

            do {
                rideRequest = setRideRequestData(resultSet);
                rideRequestBean = new RideRequestBean(rideRequest.getIdRideRequest(), rideRequest.getIdRide(),
                        rideRequest.getPassengerEmail(), rideRequest.getStatus());
                rideRequestBeanList.add(rideRequestBean);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return rideRequestBeanList;
    }

    // Per recuperare la lista delle richieste di corse future confermate da un guidatore
    /*$$$
    public static List<RideRequest> retrieveConfirmedDriverRequests(String driverEmail) {
        Connection connection;
        RideRequest rideRequest;
        List<RideRequest> rideRequestsList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.retrieveConfirmedDriverRequests(connection, driverEmail);

            // Il guidatore non ha confermato richieste per corse future
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
     */


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
}