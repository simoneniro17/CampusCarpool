package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.CompatibleRideBean;
import com.example.campuscarpool.bean.SearchRideBean;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.engineering.factory.RideDAOFactory;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.model.Ride;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//  Controller per la ricerca di corse compatibili
public class SearchRideController {

    //  Per cercare le corse compatibili con i criteri di ricerca
    public List<CompatibleRideBean> compatibleRides(SearchRideBean searchRideBean) throws MessageException, NotFoundException {
        checkSearchedLocations(searchRideBean.getDepartureLocation(), searchRideBean.getDestinationLocation());
        checkSearchedDeparture(searchRideBean.getDepartureDate(), searchRideBean.getDepartureTime());

        //  Lista delle corse compatibili
        List<CompatibleRideBean> compatibleRides = new ArrayList<>();
        CompatibleRideBean compatibleRideBean;

        //  Accesso ai dati delle corse
        RideDAO rideDAO = RideDAOFactory.getInstance().createRideDAO();

        //  Recupero delle corse in base ai criteri di ricerca
        List<Ride> rideList = rideDAO.retrieveRides(searchRideBean.getDepartureDate(), searchRideBean.getDepartureTime(),
                searchRideBean.getDepartureLocation(), searchRideBean.getDestinationLocation());

        //  "Conversione" corse in Bean di corse compatibili
        for (Ride ride : rideList) {
            compatibleRideBean = new CompatibleRideBean(ride.getIdRide(), ride.getDepartureDate().toLocalDate(), ride.getDepartureTime(),
                    ride.getDepartureLocation(), ride.getDestinationLocation(), ride.getAvailableSeats());
            compatibleRideBean.setCompatibleRideBeanDriverInfo(ride.getDriverFirstName(), ride.getDriverLastName(), ride.getDriverEmail(), ride.getDriverPhoneNumber());

            compatibleRides.add(compatibleRideBean);
        }

        return compatibleRides;
    }

    //  Per verificare se le posizioni di partenza e destinazione sono uguali
    private void checkSearchedLocations(String departureLocation, String destinationLocation) throws MessageException {
        if (departureLocation.equalsIgnoreCase(destinationLocation)) {
            throw new MessageException("Departure location cannot be\nequal to destination! Please,\ninsert correct locations.");
        }
    }

    //  Per verificare se la data e l'orario di partenza sono validi
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