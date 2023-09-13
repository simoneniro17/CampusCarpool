package com.example.campuscarpool.viewcli;

import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.ScannerSupport;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.CommandErrorException;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.graphiccontroller.cli.DriverRequestsCLIController;

import java.util.Scanner;

public class DriverRequestsViewCLI {

    private final DriverRequestsCLIController driverRequestsCLIController;

    public DriverRequestsViewCLI(DriverRequestsCLIController driverRequestsCLIController) {
        this.driverRequestsCLIController = driverRequestsCLIController;
    }

    // Visualizzazione menu per controllare le richieste ricevute e confermate
    public void run() {
        Printer.printMessage("\n-------------------------------------------- RIDE REQUESTS --------------------------------------------");

        // Opzioni per la visualizzazione delle richieste
        Printer.printMessage("1) Pending requests \n2) Confirmed requests \n3) Back");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {
            this.driverRequestsCLIController.executeCommand(inputLine);
        } catch (CommandErrorException | NotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }

    // Per stampare l'azione richiesta all'utente
    public void printAction() {
        Printer.printMessage("\nInsert the Ride Request ID of the request you want to manage or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int idRideRequest = scanner.nextInt();

        driverRequestsCLIController.executeSelectedId(idRideRequest);
    }

    // Per stampare l'azione richiesta all'utente in caso d'inserimento non valido
    public void rePrintAction() {
        Printer.printMessage("\nThe Ride Request ID you inserted is not in the list. Please, insert a correct value or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int idRideRequest = scanner.nextInt();

        driverRequestsCLIController.executeSelectedId(idRideRequest);
    }

    // Stampa del messaggio per continuare
    public void printContinue() {
        Printer.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }

    // Stampa delle opzioni per gestire la richiesta
    public void printOption() {
        Printer.printMessage("\n1) Accept Request \n2) Reject Request \n3) Back");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        try {
            driverRequestsCLIController.executeOption(choice);
        } catch (CommandErrorException | MessageException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }
}