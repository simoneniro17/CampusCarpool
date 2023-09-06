package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.CompatibleRideBean;
import com.example.campuscarpool.bean.SearchRideBean;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.engineering.factory.RideDAOFactory;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.model.Ride;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SearchRideController {

    public List<CompatibleRideBean> compatibleRides(SearchRideBean searchRideBean) throws MessageException {
        checkSearchedLocations(searchRideBean.getDepartureLocation(), searchRideBean.getDestinationLocation());
        checkSearchedDeparture(searchRideBean.getDepartureDate(), searchRideBean.getDepartureTime());

        List<CompatibleRideBean> compatibleRides = new ArrayList<>();
        CompatibleRideBean compatibleRideBean;

        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();
        List<Ride> rideList = rideDAO.retrieveRides(searchRideBean.getDepartureDate(), searchRideBean.getDepartureTime(),
                searchRideBean.getDepartureLocation(), searchRideBean.getDestinationLocation());

        for (Ride ride : rideList) {
            compatibleRideBean = new CompatibleRideBean(ride.getIdRide(), ride.getDepartureDate().toLocalDate(), ride.getDepartureTime(),
                    ride.getDepartureLocation(), ride.getDestinationLocation(), ride.getAvailableSeats(),
                    ride.getDriverFirstName(), ride.getDriverLastName(), ride.getDriverEmail(), ride.getDriverPhoneNumber());

            compatibleRides.add(compatibleRideBean);
        }

        return compatibleRides;
    }

    private void checkSearchedLocations(String departureLocation, String destinationLocation) throws MessageException {
        if (departureLocation.equalsIgnoreCase(destinationLocation)) {
            throw new MessageException("Departure location cannot be\nequal to destination! Please,\ninsert correct locations.");
        }
    }

    private void checkSearchedDeparture(LocalDate departureDate, LocalTime departureTime) throws MessageException {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        if (departureDate.isBefore(currentDate)) {
            throw new MessageException("You cannot search rides in the past!\nPlease, insert correct date values.");
        }

        if (departureDate.equals(currentDate) && departureTime.isBefore(currentTime)) {
            throw new MessageException("You cannot search rides in the past!\nPlease, insert correct time values.");
        }
    }
}