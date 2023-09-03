package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.RideBean;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.engineering.factory.RideDAOFactory;
import com.example.campuscarpool.exception.DuplicateRideException;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.model.Ride;


import java.time.LocalDate;
import java.time.LocalTime;

public class CreateRideController {

    public void createRide(RideBean rideBean) throws MessageException, DuplicateRideException {
        checkLocations(rideBean.getDepartureLocation(), rideBean.getDestinationLocation());
        checkDeparture(rideBean.getDepartureDate(), rideBean.getDepartureTime());

        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();

        Ride ride = new Ride(rideBean.getDepartureDate(), rideBean.getDepartureTime(), rideBean.getDepartureLocation(),
                rideBean.getDestinationLocation(), rideBean.getAvailableSeats(),
                rideBean.getDriverFirstName(), rideBean.getDriverLastName(), rideBean.getDriverEmail(), rideBean.getDriverPhoneNumber());

        rideDAO.addRide(ride);
    }

    private void checkLocations(String departureLocation, String destinationLocation) throws MessageException {
        if (departureLocation.equalsIgnoreCase(destinationLocation)) {
            throw new MessageException("Departure location cannot be\nequal to destination! Please,\ninsert correct locations.");
        }
    }

    private void checkDeparture(LocalDate departureDate, LocalTime departureTime) throws MessageException {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        if (departureDate.isBefore(currentDate)) {
            throw new MessageException("Your ride cannot be in the past!\nPlease, insert correct date values.");
        }

        if (departureDate.equals(currentDate) && departureTime.isBefore(currentTime)) {
            throw new MessageException("Your ride cannot be in the past!\nPlease, insert correct time values.");
        }
    }
}
