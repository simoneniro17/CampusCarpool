package com.example.campuscarpool.model;

import com.example.campuscarpool.engineering.observer.Subject;

import java.time.LocalDate;
import java.time.LocalTime;

public class RideRequest extends Subject {
    private int idRideRequest;
    private int idRide;
    private String passengerEmail;
    private int status;

    /*
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

     */

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

    /*
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public void setDriverPhoneNumber(String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public LocalDate getPassengerBirth() {
        return passengerBirth;
    }

    public void setPassengerBirth(LocalDate passengerBirth) {
        this.passengerBirth = passengerBirth;
    }

    public String getPassengerPhoneNumber() {
        return passengerPhoneNumber;
    }

    public void setPassengerPhoneNumber(String passengerPhoneNumber) {
        this.passengerPhoneNumber = passengerPhoneNumber;
    }

     */
}