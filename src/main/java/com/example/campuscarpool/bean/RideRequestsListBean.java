package com.example.campuscarpool.bean;

import com.example.campuscarpool.model.RideRequest;

import java.util.List;

public class RideRequestsListBean {
    private List<RideRequest> rideRequestsList;

    public RideRequestsListBean(List<RideRequest> rideRequestsList) {
        this.rideRequestsList = rideRequestsList;
    }

    public List<RideRequest> getRideRequestsList() {
        return rideRequestsList;
    }

    public void setRideRequestsList(List<RideRequest> rideRequestsList) {
        this.rideRequestsList = rideRequestsList;
    }
}
