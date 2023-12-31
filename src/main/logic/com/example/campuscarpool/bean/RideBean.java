package com.example.campuscarpool.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class RideBean {
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String departureLocation;
    private String destinationLocation;
    private int availableSeats;

    private String driverFirstName;
    private String driverLastName;
    private String driverEmail;
    private String driverPhoneNumber;

    public RideBean(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation, int availableSeats) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.availableSeats = availableSeats;
    }

    public void setRideBeanDriverInfo(String driverFirstName, String driverLastName, String driverEmail, String driverPhoneNumber) {
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.driverEmail = driverEmail;
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public String getDriverEmail() {
        return driverEmail;
    }
}
