package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.DriverBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.dao.PassengerDAO;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.dao.RideRequestDAO;
import com.example.campuscarpool.engineering.factory.RideDAOFactory;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.model.Passenger;
import com.example.campuscarpool.model.Ride;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ManageRideRequestController {

    public List<RideRequestBean> retrieveDriverPendingRequests(DriverBean driverBean) throws NotFoundException {
        return retrieveDriverRequests(driverBean, 0);
    }

    public List<RideRequestBean> retrieveDriverConfirmedRequests(DriverBean driverBean) throws NotFoundException {
        return retrieveDriverRequests(driverBean, 1);
    }

    public void confirmRideRequest(RideRequestBean rideRequestBean, Pane pane) {
        rideRequestBean.setStatus(1);
        if(pane != null) {
            rideRequestBean.notifyObserversDriver(rideRequestBean, pane);
        }
        updateRideRequestStatus(rideRequestBean, 1);
    }

    public void rejectRideRequest(RideRequestBean rideRequestBean, Pane pane) {
        rideRequestBean.setStatus(2);
        if (pane != null) {
            rideRequestBean.notifyObserversDriver(rideRequestBean, pane);
        }
        updateRideRequestStatus(rideRequestBean, 2);
    }

    private List<RideRequestBean> retrieveDriverRequests(DriverBean driverBean, int status) throws NotFoundException {
        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();

        List<RideRequestBean> rideRequestBeanList = new ArrayList<>();

        if (status == 0) {
            rideRequestBeanList = RideRequestDAO.retrievePendingDriverRequests(driverBean.getEmail());
        } else if (status == 1) {
            rideRequestBeanList = RideRequestDAO.retrieveConfirmedDriverRequests(driverBean.getEmail());
        }

        setDriverRideRequestDetails(rideRequestBeanList, rideDAO);

        return rideRequestBeanList;
    }

    private void setDriverRideRequestDetails(List<RideRequestBean> rideRequestBeanList, RideDAO rideDAO) throws NotFoundException {
        for (RideRequestBean rideRequestBean : rideRequestBeanList) {
            Ride ride = rideDAO.findRideById(rideRequestBean.getIdRide());
            Passenger passenger = PassengerDAO.findPassengerByUsername(rideRequestBean.getPassengerEmail());

            rideRequestBean.setDepartureDate(ride.getDepartureDate().toLocalDate());
            rideRequestBean.setDepartureTime(ride.getDepartureTime());
            rideRequestBean.setDepartureLocation(ride.getDepartureLocation());
            rideRequestBean.setDestinationLocation(ride.getDestinationLocation());
            rideRequestBean.setPassengerFirstName(passenger.getFirstName());
            rideRequestBean.setPassengerLastName(passenger.getLastName());
            rideRequestBean.setPassengerBirth(passenger.getDateOfBirth());
            rideRequestBean.setPassengerEmail(passenger.getEmail());
            rideRequestBean.setPassengerPhoneNumber(passenger.getPhoneNumber());
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