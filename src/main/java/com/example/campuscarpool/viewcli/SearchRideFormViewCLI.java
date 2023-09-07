package com.example.campuscarpool.viewcli;

import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.ScannerSupport;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.graphiccontroller.cli.BookRideCLIController;
import com.example.campuscarpool.graphiccontroller.cli.SearchRideCLIController;

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
        } catch (MessageException | NotFoundException e) {
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

    public void printContinue() {
        Printer.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }
}