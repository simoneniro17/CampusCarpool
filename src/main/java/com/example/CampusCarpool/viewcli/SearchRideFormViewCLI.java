package com.example.CampusCarpool.viewcli;

import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.MessageException;
import com.example.CampusCarpool.graphiccontroller.cli.BookRideCLIController;
import com.example.CampusCarpool.graphiccontroller.cli.SearchRideCLIController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class SearchRideFormViewCLI {

    private SearchRideCLIController searchRideCLIController;
    private FormViewCLI formViewCLI;
    private BookRideCLIController bookRideCLIController;

    public SearchRideFormViewCLI (SearchRideCLIController searchRideCLIController) {
        this.searchRideCLIController = searchRideCLIController;
        this.formViewCLI = new FormViewCLI();
        this.bookRideCLIController = new BookRideCLIController();
    }

    // Visualizzazione form di ricerca di una corsa
    public void run() {
        Printer.printMessage("\n-------------------------------------------- SEARCH RIDE FORM --------------------------------------------");

        // Ottenimento informazioni sulla corsa
        LocalDate departureDate = formViewCLI.printDepartureDate();
        LocalTime departureTime = formViewCLI.printDepartureTime();
        String departureLocation = formViewCLI.printDepartureLocation();
        String destinationLocation = formViewCLI.printDestinationLocation();

        try {
            // Ricerca e stampa delle corse compatibili
            searchRideCLIController.compatibleRides(departureDate, departureTime, departureLocation, destinationLocation);
            searchRideCLIController.displayCompatibleRides();
        } catch (MessageException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    // Per stampare l'azione richiesta all'utente
    public void printAction() {
        Printer.printMessage("\nInsert the Ride ID of the ride you want to send a request or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int idRide = scanner.nextInt();

        searchRideCLIController.executeSelectedId(idRide);
    }

    // Per stampare l'azione richiesta all'utente in caso d'inserimento non valido
    public void rePrintAction() {
        Printer.printMessage("\nThe Ride ID you inserted is not in the list. Please, insert a correct value or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int idRide = scanner.nextInt();

        searchRideCLIController.executeSelectedId(idRide);
    }
}