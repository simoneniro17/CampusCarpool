package com.example.campuscarpool.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompatibleRideBean {
    private int idRide;
    private LocalDate departureDate;

    private LocalTime departureTime;

    private String driverFirstName;

    private String driverLastName;

    private String driverEmail;

    private String driverPhoneNumber;

    public CompatibleRideBean(int idRide, LocalDate departureDate, LocalTime departureTime, String driverFirstName, String driverLastName, String driverEmail, String driverPhoneNumber) {
        this.idRide = idRide;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.driverEmail = driverEmail;
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public int getIdRide() {
        return idRide;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
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
