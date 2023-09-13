package com.example.campuscarpool.bean;

import com.example.campuscarpool.engineering.observer.Subject;

import java.time.LocalDate;
import java.time.LocalTime;

public class RideRequestBean extends Subject {
    private int idRideRequest;
    private int idRide;
    private String passengerEmail;
    private int status;

    private LocalDate departureDate;
    private LocalTime departureTime;
    private String departureLocation;
    private String destinationLocation;

    private String driverFirstName;
    private String driverLastName;
    private String driverEmail;
    private String driverPhoneNumber;

    private String passengerFirstName;
    private String passengerLastName;
    private LocalDate passengerBirth;
    private String passengerPhoneNumber;

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

    public void setRideInfo(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
    }

    public void setDriverInfo(String driverFirstName, String driverLastName, String driverEmail, String driverPhoneNumber) {
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.driverEmail = driverEmail;
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public void setPassengerInfo(String passengerFirstName, String passengerLastName, LocalDate passengerBirth, String passengerEmail, String passengerPhoneNumber) {
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.passengerBirth = passengerBirth;
        this.passengerEmail = passengerEmail;
        this.passengerPhoneNumber = passengerPhoneNumber;
    }

    public int getIdRideRequest() {
        return idRideRequest;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public LocalDate getPassengerBirth() {
        return passengerBirth;
    }

    public String getPassengerPhoneNumber() {
        return passengerPhoneNumber;
    }
}