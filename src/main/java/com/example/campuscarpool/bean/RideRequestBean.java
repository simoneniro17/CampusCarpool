package com.example.campuscarpool.bean;

import com.example.campuscarpool.engineering.observer.Subject;

public class RideRequestBean extends Subject {

    private int idRideRequest;
    private int idRide;
    private String passengerEmail;
    private int status;


    public RideRequestBean(int idRideRequest, int idRide, String passengerEmail, int status) {
        this.idRideRequest = idRideRequest;
        this.idRide = idRide;
        this.passengerEmail = passengerEmail;
        this.status = status;
    }

    public RideRequestBean(int idRide, String passengerEmail, int status) {
        this.idRide = idRide;
        this.passengerEmail = passengerEmail;
        this.status = status;
    }

    public int getIdRideRequest() {
        return idRideRequest;
    }

    public void setIdRideRequest(int idRideRequest) {
        this.idRideRequest = idRideRequest;
    }

    public int getIdRide() {
        return idRide;
    }

    public void setIdRide(int idRide) {
        this.idRide = idRide;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
