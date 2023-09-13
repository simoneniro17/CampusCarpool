package com.example.campuscarpool.model;

public class RideRequest {
    private int idRideRequest;
    private int idRide;
    private String passengerEmail;
    private int status;

    public RideRequest(int idRideRequest, int idRide, String passengerEmail, int status) {
        this.idRideRequest = idRideRequest;
        this.idRide = idRide;
        this.passengerEmail = passengerEmail;
        this.status = status;
    }

    public int getIdRideRequest() {
        return idRideRequest;
    }

    public int getIdRide() {
        return idRide;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public int getStatus() {
        return status;
    }
}