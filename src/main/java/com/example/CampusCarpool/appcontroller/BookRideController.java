package com.example.CampusCarpool.appcontroller;


import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.dao.RideRequestDAO;
import com.example.CampusCarpool.exception.DuplicateRequestException;
import com.example.CampusCarpool.exception.MessageException;
import com.example.CampusCarpool.exception.NotFoundException;

public class BookRideController {

    public void sendRequest(RideRequestBean rideRequestBean) throws DuplicateRequestException {
        RideRequestDAO.newRequest(rideRequestBean.getIdRide(), rideRequestBean.getPassengerEmail());
    }
}
