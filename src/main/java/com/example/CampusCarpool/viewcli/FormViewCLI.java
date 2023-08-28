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
        LocalDate departureDate;
        Scanner scanner = new Scanner(System.in);

        do {
            Printer.printMessage("\nInsert departure date with format yyyy-MM-dd:");

            try {
                departureDate = formCLIController.validateDepartureDate(scanner.nextLine());
                break;
            } catch (MessageException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        } while (true);

        return departureDate;
    }

    // Per ottenere l'orario di partenza
    public LocalTime printDepartureTime() {
        LocalTime departureTime;
        Scanner scanner = new Scanner(System.in);

        do {
            Printer.printMessage("\nInsert departure time with format HH:mm:ss:");

            try {
                departureTime = formCLIController.validateDepartureTime(scanner.nextLine());
                break;
            } catch (MessageException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        } while (true);

        return departureTime;
    }

    // Per ottenere la località di partenza
    public String printDepartureLocation() {
        String departureLocation;
        Scanner scanner = new Scanner(System.in);

        do {
            Printer.printMessage("\nInsert departure location:");

            try {
                departureLocation = formCLIController.executeLocation(scanner.nextLine());
                break;
            } catch (MessageException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        } while (true);

        return departureLocation;
    }

    // Per ottenere la località di destinazione
    public String printDestinationLocation() {
        String destinationLocation;
        Scanner scanner = new Scanner(System.in);

        do {
            Printer.printMessage("\nInsert destination location:");

            try {
                destinationLocation = formCLIController.executeLocation(scanner.nextLine());
                break;
            } catch (MessageException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        } while (true);

        return destinationLocation;
    }

    // Per ottenere il numero di posti disponibili
    public int printAvailableSeats() {
        int availableSeats;
        Scanner scanner = new Scanner(System.in);

        do {
            Printer.printMessage("\nInsert the number of available seats:");

            try {
                availableSeats = formCLIController.executeAvailableSeats(scanner.nextLine());
                break;
            } catch (MessageException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        } while (true);

        return availableSeats;
    }
}