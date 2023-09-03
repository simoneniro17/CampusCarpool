package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.DriverBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.bean.RideRequestsListBean;
import com.example.campuscarpool.dao.PassengerDAO;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.dao.RideRequestDAO;
import com.example.campuscarpool.engineering.factory.RideDAOFactory;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.model.Passenger;
import com.example.campuscarpool.model.Ride;
import com.example.campuscarpool.model.RideRequest;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ManageRideRequestController {

    public RideRequestsListBean retrieveDriverPendingRequests(DriverBean driverBean) throws NotFoundException {
        return retrieveDriverRequests(driverBean, 0);
    }

    public RideRequestsListBean retrieveDriverConfirmedRequests(DriverBean driverBean) throws NotFoundException {
        return retrieveDriverRequests(driverBean, 1);
    }

    public void confirmRideRequest(RideRequestBean rideRequestBean, Pane pane) {
        updateRideRequestStatus(rideRequestBean, 1);
        rideRequestBean.setStatus(1);
        if(pane != null) {
            rideRequestBean.notifyObserversPassenger(rideRequestBean, pane);
        }
    }

    public void rejectRideRequest(RideRequestBean rideRequestBean, Pane pane) {
        updateRideRequestStatus(rideRequestBean, 2);
        rideRequestBean.setStatus(2);
        if (pane != null) {
            rideRequestBean.notifyObserversPassenger(rideRequestBean, pane);
        }
    }

    private RideRequestsListBean retrieveDriverRequests(DriverBean driverBean, int status) throws NotFoundException {
        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();

        List<RideRequest> rideRequestList = new ArrayList<>();

        if (status == 0) {
            rideRequestList = RideRequestDAO.retrievePendingDriverRequests(driverBean.getEmail());
        } else if (status == 1) {
            rideRequestList = RideRequestDAO.retrieveConfirmedDriverRequests(driverBean.getEmail());
        }

        setDriverRideRequestDetails(rideRequestList, rideDAO);

        return new RideRequestsListBean(rideRequestList);
    }

    private void setDriverRideRequestDetails(List<RideRequest> rideRequestList, RideDAO rideDAO) throws NotFoundException {
        for (RideRequest rideRequest : rideRequestList) {
            Ride ride = rideDAO.findRideById(rideRequest.getIdRide());
            Passenger passenger = PassengerDAO.findPassengerByUsername(rideRequest.getPassengerEmail());

            rideRequest.setDepartureDate(ride.getDepartureDate().toLocalDate());
            rideRequest.setDepartureTime(ride.getDepartureTime());
            rideRequest.setDepartureLocation(ride.getDepartureLocation());
            rideRequest.setDestinationLocation(ride.getDestinationLocation());
            rideRequest.setPassengerFirstName(passenger.getFirstName());
            rideRequest.setPassengerLastName(passenger.getLastName());
            rideRequest.setPassengerBirth(passenger.getDateOfBirth());
            rideRequest.setPassengerEmail(passenger.getEmail());
            rideRequest.setPassengerPhoneNumber(passenger.getPhoneNumber());
        }
    }

    private void updateRideRequestStatus(RideRequestBean rideRequestBean, int newStatus) {
        RideRequestDAO.updateStatus(rideRequestBean.getIdRideRequest(), newStatus);

        if(newStatus == 1) {
            RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();
            rideDAO.updateRideAvailableSeats(rideRequestBean.getIdRide());
        }
    }
}
