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
                int availableSeats)  {
        this.idRide = idRide;
        this.departureDate = Date.valueOf(departureDate);
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.availableSeats = availableSeats;
    }

    public Ride(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation, int availableSeats) {
        this.departureDate = Date.valueOf(departureDate);
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.availableSeats = availableSeats;
    }

    public void setRideDriverInfo(String driverFirstName, String driverLastName, String driverEmail, String driverPhoneNumber) {
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.driverEmail = driverEmail;
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
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

    public int getIdRide() {
        return idRide;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getDriverEmail() {
        return driverEmail;
    }
}