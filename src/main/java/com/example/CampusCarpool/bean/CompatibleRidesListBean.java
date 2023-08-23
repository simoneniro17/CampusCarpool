package com.example.CampusCarpool.bean;

import com.example.CampusCarpool.model.Ride;

import java.util.List;

public class CompatibleRidesListBean {
    private List<Ride> compatibleRidesList;

    public CompatibleRidesListBean(List<Ride> compatibleRidesList) {
        this.compatibleRidesList = compatibleRidesList;
    }

    public List<Ride> getCompatibleRidesList() {
        return compatibleRidesList;
    }

    public void setCompatibleRidesList(List<Ride> compatibleRidesList) {
        this.compatibleRidesList = compatibleRidesList;
    }
}