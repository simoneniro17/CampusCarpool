package com.example.CampusCarpool.appcontroller;

import com.example.CampusCarpool.bean.CompatibleRidesListBean;
import com.example.CampusCarpool.bean.SearchRideBean;
import com.example.CampusCarpool.dao.RideDAO;
import com.example.CampusCarpool.engineering.factory.RideDAOFactory;
import com.example.CampusCarpool.exception.MessageException;

import java.time.LocalDate;
import java.time.LocalTime;

public class SearchRideController {

    public CompatibleRidesListBean compatibleRides(SearchRideBean searchRideBean) throws MessageException {
        checkSearchedLocations(searchRideBean.getDepartureLocation(), searchRideBean.getDestinationLocation());
        checkSearchedDeparture(searchRideBean.getDepartureDate(), searchRideBean.getDepartureTime());

        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();

        CompatibleRidesListBean compatibleRidesListBean = new CompatibleRidesListBean(rideDAO.retrieveRides(searchRideBean.getDepartureDate(), searchRideBean.getDepartureTime(), searchRideBean.getDepartureLocation(), searchRideBean.getDestinationLocation()));
        return compatibleRidesListBean;
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
