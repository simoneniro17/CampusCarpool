package com.example.campuscarpool.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class SearchRideBean {
    private LocalDate departureDate;

    private LocalTime departureTime;
    private String departureLocation;
    private String destinationLocation;

    public SearchRideBean(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
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
}
