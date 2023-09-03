package com.example.campuscarpool.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Ride {
    private int idRide;
    private Date departureDate;

    private LocalTime departureTime;
    private String departureLocation;
    private String destinationLocation;
    private int availableSeats;

    private String driverFirstName;

    private String driverLastName;

    private String driverEmail;

    private String driverPhoneNumber;

    public Ride(int idRide, LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation,
                int availableSeats, String driverFirstName, String driverLastName, String driverEmail, String driverPhoneNumber) {
        this.idRide = idRide;
        this.departureDate = Date.valueOf(departureDate);
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.availableSeats = availableSeats;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.driverEmail = driverEmail;
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public Ride(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation,
                int availableSeats, String driverFirstName, String driverLastName, String driverEmail, String driverPhoneNumber) {
        this.departureDate = Date.valueOf(departureDate);
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.availableSeats = availableSeats;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.driverEmail = driverEmail;
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public int getIdRide() {
        return idRide;
    }

    public Date getDepartureDate() {
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

    public int getAvailableSeats() {
        return availableSeats;
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
}