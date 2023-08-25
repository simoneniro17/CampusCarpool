package com.example.CampusCarpool.graphiccontroller.cli;

import com.example.CampusCarpool.appcontroller.SearchRideController;
import com.example.CampusCarpool.bean.CompatibleRidesListBean;
import com.example.CampusCarpool.bean.SearchRideBean;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.MessageException;
import com.example.CampusCarpool.model.Ride;
import com.example.CampusCarpool.viewcli.SearchRideFormViewCLI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SearchRideCLIController implements GraphicCLIController {
    private SearchRideFormViewCLI searchRideFormViewCLI;
    private CompatibleRidesListBean compatibleRidesListBean;
    private SearchRideBean searchRideBean;

    @Override
    public void start() {
        this.searchRideFormViewCLI = new SearchRideFormViewCLI(this);
        this.searchRideFormViewCLI.run();
    }

    // Per creare il SearchRideBean con i dettagli di ricerca inseriti
    public void compatibleRides(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) {
        this.searchRideBean = new SearchRideBean(departureDate, departureTime, departureLocation, destinationLocation);
    }

    // Per trovare le corse compatibili
    public void displayCompatibleRides() throws MessageException {
        SearchRideController searchRideController = new SearchRideController();
        this.compatibleRidesListBean = searchRideController.compatibleRides(searchRideBean);

        List<Ride> compatibleRides = compatibleRidesListBean.getCompatibleRidesList();

        // Non ci sono corse compatibili
        if (compatibleRides.isEmpty()) {
            throw new MessageException("No compatible rides found.");
        }

        // Stampa delle corse compatibili
        printCompatibleRides(compatibleRides);

        try {
            searchRideFormViewCLI.printAction();
        } catch (NumberFormatException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    // Per stampare la lista delle corse compatibili
    private static void printCompatibleRides(List<Ride> compatibleRidesList) {
        if (!compatibleRidesList.isEmpty()) {
            Printer.printMessage("\n------------------------------------------------------------------------------------------------ COMPATIBLE RIDES ----------------------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-20s | %-15s | %-20s | %-20s | %-15s | %-20s | %-20s | %-20s | %-20s%n",
                    "RideID", "Departure Date", "Departure Time", "Departure Location", "Destination Location",
                    "Available Seats", "Driver's First Name", "Driver's Last Name", "Driver's Email", "Driver's Phone Number");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (Ride ride : compatibleRidesList) {
                System.out.printf("%-10s | %-20s | %-15s | %-20s | %-20s | %-15s | %-20s | %-20s | %-20s | %-20s%n",
                        ride.getIdRide(), ride.getDepartureDate(), ride.getDepartureTime(), ride.getDepartureLocation(),
                        ride.getDestinationLocation(), ride.getAvailableSeats(), ride.getDriverFirstName(),
                        ride.getDriverLastName(), ride.getDriverEmail(), ride.getDriverPhoneNumber());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    // Per elaborare l'ID della corsa selezionata dal Passenger
    public void executeSelectedId(int idRide) {
        if(idRide == 0) {
            this.start();
        } else {
            manageRide(idRide);
        }
    }

    // Per gestire la corsa selezionata
    public void manageRide(int idRide) {
        for(Ride compatibleRide : (compatibleRidesListBean.getCompatibleRidesList())) {
            if(compatibleRide.getIdRide() == idRide) {
                BookRideCLIController bookRideCLIController = new BookRideCLIController();
                bookRideCLIController.sendRideRequest(compatibleRide.getIdRide());
                return;
            }
        }

        searchRideFormViewCLI.rePrintAction();
    }
}