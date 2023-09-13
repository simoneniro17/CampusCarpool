package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.dao.RideRequestDAO;
import com.example.campuscarpool.exception.DuplicateRequestException;

//  Controller per l'invio di una richiesta di passaggio da parte di un passeggero
public class BookRideController {
    public void sendRequest(RideRequestBean rideRequestBean) throws DuplicateRequestException {
        RideRequestDAO.newRequest(rideRequestBean.getIdRide(), rideRequestBean.getPassengerEmail());
    }
}