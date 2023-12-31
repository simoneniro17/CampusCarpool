package com.example.campuscarpool.viewcli;

import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.ScannerSupport;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.DuplicateRideException;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.graphiccontroller.cli.CreateRideCLIController;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateRideFormViewCLI {
    private CreateRideCLIController createRideCLIController;
    private FormViewCLI formViewCLI;

    public CreateRideFormViewCLI(CreateRideCLIController createRideCLIController) {
        this.createRideCLIController = createRideCLIController;
        this.formViewCLI = new FormViewCLI();
    }

    // Visualizzazione form di creazione di una corsa
    public void run() {
        Printer.printMessage("\n-------------------------------------------- CREATE RIDE FORM --------------------------------------------");

        // Ottenimento dettagli corsa
        LocalDate departureDate = formViewCLI.printDepartureDate();
        LocalTime departureTime = formViewCLI.printDepartureTime();
        String departureLocation = formViewCLI.printDepartureLocation();
        String departureDestination = formViewCLI.printDepartureLocation();
        int availableSeats = formViewCLI.printAvailableSeats();

        try {
            // Creazione e aggiunta della corsa
            createRideCLIController.createRide(departureDate, departureTime, departureLocation, departureDestination, availableSeats);
            createRideCLIController.addRide();
            createRideCLIController.displayCreatedRideMessage();
        } catch (DuplicateRideException | MessageException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public static void printContinue() {
        Printer.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }
}