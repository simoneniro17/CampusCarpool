package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.DriverBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.dao.PassengerDAO;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.dao.RideRequestDAO;
import com.example.campuscarpool.engineering.factory.RideDAOFactory;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.model.Passenger;
import com.example.campuscarpool.model.Ride;
import com.example.campuscarpool.model.RideRequest;
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

    private List<RideRequestBean> retrieveDriverRequests(DriverBean driverBean, int status) throws NotFoundException {
        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();

        List<RideRequestBean> rideRequestBeanList = new ArrayList<>();
        List<RideRequest> requests = null;

        if (status == 0) {
            requests = RideRequestDAO.retrievePendingDriverRequests(driverBean.getEmail());
        } else if (status == 1) {
            requests = RideRequestDAO.retrieveConfirmedDriverRequests(driverBean.getEmail());
        }

        return setDriverRideRequestDetails(requests, rideRequestBeanList, rideDAO);
    }


    public void confirmRideRequest(RideRequestBean rideRequestBean, Pane pane) throws MessageException {
        rideRequestBean.setStatus(1);
        if(pane != null) {
            rideRequestBean.notifyObserversDriver(rideRequestBean, pane);
        }
        updateRideRequestStatus(rideRequestBean, 1);
    }

    public void rejectRideRequest(RideRequestBean rideRequestBean, Pane pane) throws MessageException {
        rideRequestBean.setStatus(2);
        if (pane != null) {
            rideRequestBean.notifyObserversDriver(rideRequestBean, pane);
        }
        updateRideRequestStatus(rideRequestBean, 2);
    }

    private List<RideRequestBean> setDriverRideRequestDetails(List<RideRequest> requests, List<RideRequestBean> rideRequestBeanList, RideDAO rideDAO) throws NotFoundException {
        for (RideRequest request : requests) {
            Ride ride = rideDAO.findRideById(request.getIdRide());
            Passenger passenger = PassengerDAO.findPassengerByUsername(request.getPassengerEmail());

            RideRequestBean rideRequestBean = new RideRequestBean(request.getIdRideRequest(), request.getIdRide(), request.getPassengerEmail(), request.getStatus());
            rideRequestBean.setRideInfo(ride.getDepartureDate().toLocalDate(), ride.getDepartureTime(), ride.getDepartureLocation(), ride.getDestinationLocation());
            rideRequestBean.setPassengerInfo(passenger.getFirstName(), passenger.getLastName(), passenger.getDateOfBirth(), passenger.getEmail(), passenger.getPhoneNumber());

            rideRequestBeanList.add(rideRequestBean);
        }

        return rideRequestBeanList;
    }

    private void updateRideRequestStatus(RideRequestBean rideRequestBean, int newStatus) throws MessageException {
        if (newStatus == 1) {
            RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();
            rideDAO.updateRideAvailableSeats(rideRequestBean.getIdRide());
        }
        RideRequestDAO.updateStatus(rideRequestBean.getIdRideRequest(), newStatus);
    }
}