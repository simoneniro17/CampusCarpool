package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.PassengerBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.bean.RideRequestsListBean;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.dao.RideRequestDAO;
import com.example.campuscarpool.engineering.factory.RideDAOFactory;
import com.example.campuscarpool.model.Ride;
import com.example.campuscarpool.model.RideRequest;

import java.util.ArrayList;
import java.util.List;

// Controller per la visualizzazione delle richieste di passaggio di un passeggero
public class RideRequestController {

    // Recupero richieste in sospeso
    /*$$$public RideRequestsListBean retrievePassengerPendingRequests(PassengerBean passengerBean) {
        return retrievePassengerRequests(passengerBean, 0);
    }*/

    public List<RideRequestBean> retrievePassengerPendingRequests(PassengerBean passengerBean) {
        return retrievePassengerRequests(passengerBean, 0);
    }

    public List<RideRequestBean> retrievePassengerAcceptedRequests(PassengerBean passengerBean) {
        return retrievePassengerRequests(passengerBean, 1);
    }

    public List<RideRequestBean> retrievePassengerRejectedRequests(PassengerBean passengerBean) {
        return retrievePassengerRequests(passengerBean, 2);
    }

    // Recupero richieste accettate
    /*$$$public RideRequestsListBean retrievePassengerAcceptedRequests(PassengerBean passengerBean) {
        return retrievePassengerRequests(passengerBean, 1);
    }*/

    // Recupero richieste rifiutate
    /*$$$public RideRequestsListBean retrievePassengerRejectedRequests(PassengerBean passengerBean) {
        return retrievePassengerRequests(passengerBean, 2);
    }*/

    // Recupero delle richieste di passaggio di un passeggero in base allo stato
    /*private RideRequestsListBean retrievePassengerRequests(PassengerBean passengerBean, int status) {
        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();

        List<RideRequest> rideRequestList = new ArrayList<>();
        
        if (status == 0) {
            rideRequestList = RideRequestDAO.retrievePendingPassengersRequests(passengerBean.getEmail());
        } else if (status == 1) {
            rideRequestList = RideRequestDAO.retrieveConfirmedPassengersRequests(passengerBean.getEmail());
        } else if (status == 2) {
            rideRequestList = RideRequestDAO.retrieveRejectedPassengersRequests(passengerBean.getEmail());
        }

        setPassengerRideRequestDetails(rideRequestList, rideDAO);

        return new RideRequestsListBean(rideRequestList);
    }*/
    private List<RideRequestBean> retrievePassengerRequests(PassengerBean passengerBean, int status) {
        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();

        List<RideRequestBean> rideRequestBeanList = new ArrayList<>();

        if (status == 0) {
            rideRequestBeanList = RideRequestDAO.retrievePendingPassengersRequests(passengerBean.getEmail());
        } else if (status == 1) {
            rideRequestBeanList = RideRequestDAO.retrieveConfirmedPassengersRequests(passengerBean.getEmail());
        } else if (status == 2) {
            rideRequestBeanList = RideRequestDAO.retrieveRejectedPassengersRequests(passengerBean.getEmail());
        }

        setPassengerRideRequestDetails(rideRequestBeanList, rideDAO);

        return rideRequestBeanList;
    }

    private void setPassengerRideRequestDetails(List<RideRequestBean> rideRequestBeanList, RideDAO rideDAO) {
        for (RideRequestBean rideRequestBean : rideRequestBeanList) {
            Ride ride = rideDAO.findRideById(rideRequestBean.getIdRide());

            rideRequestBean.setDepartureDate(ride.getDepartureDate().toLocalDate());
            rideRequestBean.setDepartureTime(ride.getDepartureTime());
            rideRequestBean.setDepartureLocation(ride.getDepartureLocation());
            rideRequestBean.setDestinationLocation(ride.getDestinationLocation());
            rideRequestBean.setDriverFirstName(ride.getDriverFirstName());
            rideRequestBean.setDriverLastName(ride.getDriverLastName());
            rideRequestBean.setDriverEmail(ride.getDriverEmail());
            rideRequestBean.setDriverPhoneNumber(ride.getDriverPhoneNumber());
        }
    }


    // Inserimento dettagli delle richieste di passaggio con le informazioni della corsa
    /*$$$private void setPassengerRideRequestDetails(List<RideRequest> rideRequestList, RideDAO rideDAO) {
        for (RideRequest rideRequest : rideRequestList) {
            Ride ride = rideDAO.findRideById(rideRequest.getIdRide());

            rideRequest.setDepartureDate(ride.getDepartureDate().toLocalDate());
            rideRequest.setDepartureTime(ride.getDepartureTime());
            rideRequest.setDepartureLocation(ride.getDepartureLocation());
            rideRequest.setDestinationLocation(ride.getDestinationLocation());
            rideRequest.setDriverFirstName(ride.getDriverFirstName());
            rideRequest.setDriverLastName(ride.getDriverLastName());
            rideRequest.setDriverEmail(ride.getDriverEmail());
            rideRequest.setDriverPhoneNumber(ride.getDriverPhoneNumber());
        }
    }

     */
}