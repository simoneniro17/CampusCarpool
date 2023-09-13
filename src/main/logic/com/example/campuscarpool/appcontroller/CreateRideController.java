package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.RideBean;
import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.exception.DuplicateRideException;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.model.Ride;

import java.time.LocalDate;
import java.time.LocalTime;

//  Controller per la creazione di una nuova corsa
public class CreateRideController {

    //  Per creare una nuova corsa
    public void createRide(RideBean rideBean) throws MessageException, DuplicateRideException {
        checkLocations(rideBean.getDepartureLocation(), rideBean.getDestinationLocation());
        checkDeparture(rideBean.getDepartureDate(), rideBean.getDepartureTime());

        //  Creazione oggetto Ride con i dati forniti
        Ride ride = new Ride(rideBean.getDepartureDate(), rideBean.getDepartureTime(), rideBean.getDepartureLocation(), rideBean.getDestinationLocation(), rideBean.getAvailableSeats());

        //  Impostazione informazioni sul conducente per la corsa
        ride.setRideDriverInfo(rideBean.getDriverFirstName(), rideBean.getDriverLastName(), rideBean.getDriverEmail(), rideBean.getDriverPhoneNumber());

        //  Aggiunta della corsa al database
        RideDAO.addRide(ride);
    }

    //  Per verificare che le località di partenza e destinazione non siano uguali
    private void checkLocations(String departureLocation, String destinationLocation) throws MessageException {
        if (departureLocation.equalsIgnoreCase(destinationLocation)) {
            throw new MessageException("Departure location cannot be\nequal to destination! Please,\ninsert correct locations.");
        }
    }

    //  Per verificare la data e l'orario di partenza
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