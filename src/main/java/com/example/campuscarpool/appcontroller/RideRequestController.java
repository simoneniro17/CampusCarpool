package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.PassengerBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.dao.RideRequestDAO;
import com.example.campuscarpool.engineering.factory.RideDAOFactory;
import com.example.campuscarpool.model.Ride;

import java.util.ArrayList;
import java.util.List;

// Controller per la visualizzazione delle richieste di passaggio di un passeggero
public class RideRequestController {

    // Recupero richieste in sospeso
    public List<RideRequestBean> retrievePassengerPendingRequests(PassengerBean passengerBean) {
        return retrievePassengerRequests(passengerBean, 0);
    }

    // Recupero richieste accettate
    public List<RideRequestBean> retrievePassengerAcceptedRequests(PassengerBean passengerBean) {
        return retrievePassengerRequests(passengerBean, 1);
    }

    // Recupero richieste rifiutate
    public List<RideRequestBean> retrievePassengerRejectedRequests(PassengerBean passengerBean) {
        return retrievePassengerRequests(passengerBean, 2);
    }

    // Recupero delle richieste di passaggio di un passeggero in base allo stato
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

    // Inserimento dettagli delle richieste di passaggio con le informazioni della corsa
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
}