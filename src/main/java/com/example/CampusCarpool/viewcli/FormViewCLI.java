package com.example.CampusCarpool.viewcli;

import com.example.CampusCarpool.appcontroller.SearchRideController;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.MessageException;
import com.example.CampusCarpool.graphiccontroller.cli.FormCLIController;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class FormViewCLI {

    // CLIController per la gestione del modulo di compilazione
    FormCLIController formCLIController;
    SearchRideController searchRideController;

    protected FormViewCLI() {
        this.formCLIController = new FormCLIController();
        this.searchRideController = new SearchRideController();
    }

    // Per ottenere la data di partenza
    public LocalDate printDepartureDate() {
        LocalDate departureDate = null;

        Printer.printMessage("\nInsert departure date with format yyyy-MM-dd:");
        Scanner scanner = new Scanner(System.in);

        try {
            departureDate = formCLIController.validateDepartureDate(scanner.nextLine());
        } catch (MessageException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printDepartureDate();
        }
        return departureDate;
    }

    // Per ottenere l'orario di partenza
    public LocalTime printDepartureTime() {
        LocalTime departureTime = null;

        Printer.printMessage("\nInsert departure time with format HH:mm:ss:");
        Scanner scanner = new Scanner(System.in);

        try {
            departureTime = formCLIController.validateDepartureTime(scanner.nextLine());
        } catch (MessageException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printDepartureTime();
        }
        return departureTime;
    }

    // Per ottenere la località di partenza
    public String printDepartureLocation() {
        String departureLocation = null;

        Printer.printMessage("\nInsert departure location:");
        Scanner scanner = new Scanner(System.in);

        try {
            departureLocation = formCLIController.executeLocation(scanner.nextLine());
        } catch (MessageException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printDepartureLocation();
        }

        return departureLocation;
    }

    // Per ottenere la località di destinazione
    public String printDestinationLocation() {
        String destinationLocation = null;

        Printer.printMessage("\nInsert destination location:");
        Scanner scanner = new Scanner(System.in);

        try {
            destinationLocation = formCLIController.executeLocation(scanner.nextLine());
        } catch (MessageException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printDestinationLocation();
        }

        return destinationLocation;
    }

    // Per ottenere il numero di posti disponibili
    public int printAvailableSeats() {
        int availableSeats = 0;

        Printer.printMessage("\nInsert the number of available seats:");
        Scanner scanner = new Scanner(System.in);

        try {
            availableSeats = formCLIController.executeAvailableSeats(scanner.nextLine());
        } catch (MessageException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printAvailableSeats();
        }

        return availableSeats;
    }
}