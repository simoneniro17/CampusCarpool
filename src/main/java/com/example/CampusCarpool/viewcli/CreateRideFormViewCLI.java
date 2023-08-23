package com.example.CampusCarpool.viewcli;

import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.DuplicateRideException;
import com.example.CampusCarpool.exception.MessageException;
import com.example.CampusCarpool.graphiccontroller.cli.CreateRideCLIController;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateRideFormViewCLI {

    private CreateRideCLIController createRideCLIController;
    private FormViewCLI formViewCLI;

    public CreateRideFormViewCLI (CreateRideCLIController createRideCLIController) {
        this.createRideCLIController = createRideCLIController;
        this.formViewCLI = new FormViewCLI();
    }

    public void run(){

        Printer.printMessage("\n-------------------------------------------- CREATE RIDE FORM --------------------------------------------");

        LocalDate departureDate = formViewCLI.printDepartureDate();
        LocalTime departureTime = formViewCLI.printDepartureTime();
        String departureLocation = formViewCLI.printDepartureLocation();
        String departureDestination = formViewCLI.printDepartureLocation();
        int availableSeats = formViewCLI.printAvailableSeats();

        try {
            createRideCLIController.createRide(departureDate, departureTime, departureLocation, departureDestination, availableSeats);
            createRideCLIController.addRide();
            createRideCLIController.displayCreatedRideMessage();
        } catch (DuplicateRideException | MessageException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }
}