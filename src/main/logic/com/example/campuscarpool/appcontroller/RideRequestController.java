package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.PassengerBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.dao.RideRequestDAO;
import com.example.campuscarpool.engineering.factory.RideDAOFactory;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.model.Ride;
import com.example.campuscarpool.model.RideRequest;

import java.util.ArrayList;
import java.util.List;

// Controller per la visualizzazione delle richieste di passaggio di un passeggero
public class RideRequestController {

    // Recupero richieste in sospeso
    public List<RideRequestBean> retrievePassengerPendingRequests(PassengerBean passengerBean) throws NotFoundException {
        return retrievePassengerRequests(passengerBean, 0);
    }

    // Recupero richieste accettate
    public List<RideRequestBean> retrievePassengerAcceptedRequests(PassengerBean passengerBean) throws NotFoundException {
        return retrievePassengerRequests(passengerBean, 1);
    }

    // Recupero richieste rifiutate
    public List<RideRequestBean> retrievePassengerRejectedRequests(PassengerBean passengerBean) throws NotFoundException {
        return retrievePassengerRequests(passengerBean, 2);
    }

    // Recupero delle richieste di passaggio di un passeggero in base allo stato
    private List<RideRequestBean> retrievePassengerRequests(PassengerBean passengerBean, int status) throws NotFoundException {
        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();

        List<RideRequestBean> rideRequestBeanList = new ArrayList<>();
        List<RideRequest> requests = null;

        if (status == 0) {
            requests = RideRequestDAO.retrievePendingPassengersRequests(passengerBean.getEmail());
        } else if (status == 1) {
            requests = RideRequestDAO.retrieveConfirmedPassengersRequests(passengerBean.getEmail());
        } else if (status == 2) {
            requests = RideRequestDAO.retrieveRejectedPassengersRequests(passengerBean.getEmail());
        }

        setPassengerRideRequestDetails(requests, rideRequestBeanList, rideDAO);

        return rideRequestBeanList;
    }

    // Inserimento dettagli delle richieste di passaggio con le informazioni della corsa
    private List<RideRequestBean> setPassengerRideRequestDetails(List<RideRequest> requests, List<RideRequestBean> rideRequestBeanList, RideDAO rideDAO) throws NotFoundException {
        for (RideRequest request : requests) {
            Ride ride = rideDAO.findRideById(request.getIdRide());

            RideRequestBean rideRequestBean = new RideRequestBean(request.getIdRideRequest(), request.getIdRide(), request.getPassengerEmail(), request.getStatus());
            rideRequestBean.setRideInfo(ride.getDepartureDate().toLocalDate(), ride.getDepartureTime(), ride.getDepartureLocation(), ride.getDestinationLocation());
            rideRequestBean.setDriverInfo(ride.getDriverFirstName(), ride.getDriverLastName(), ride.getDriverEmail(), ride.getDriverPhoneNumber());

            rideRequestBeanList.add(rideRequestBean);
        }

        return rideRequestBeanList;
    }
}