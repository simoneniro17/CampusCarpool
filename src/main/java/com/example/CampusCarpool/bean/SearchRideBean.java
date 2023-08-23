package com.example.CampusCarpool.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class SearchRideBean {
    private LocalDate departureDate;

    private LocalTime departureTime;
    private String departureLocation;
    private String destinationLocation;
    private int availableSeats;

    public SearchRideBean(LocalDate departureDate, LocalTime departureTime, String destinationLocation) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destinationLocation = destinationLocation;
    }

    public SearchRideBean(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
    }

    public SearchRideBean(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation, int availableSeats) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.availableSeats = availableSeats;
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

    public int getAvailableSeats() {
        return availableSeats;
    }
}
