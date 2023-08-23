package com.example.CampusCarpool.viewcli;

import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ScannerSupport;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.CommandErrorException;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.graphiccontroller.cli.DriverRequestsCLIController;

import java.time.LocalDate;
import java.util.Scanner;

public class DriverRequestsViewCLI {

    private DriverRequestsCLIController driverRequestsCLIController;

    public DriverRequestsViewCLI(DriverRequestsCLIController driverRequestsCLIController) {
        this.driverRequestsCLIController = driverRequestsCLIController;
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- RIDE REQUESTS --------------------------------------------");

        // Opzioni per la visualizzazione delle richieste
        Printer.printMessage("1) Pending requests \n2) Confirmed requests \n3) Back");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {

            // Esecuzione comando selezionato dall'utente attraverso il controller
            this.driverRequestsCLIController.executeCommand(inputLine);

        } catch (CommandErrorException | NotFoundException e) {

            // Gestione eventuali eccezioni e richiamo alla visualizzazione del menu
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }

    public void printAction() {
        Printer.printMessage("\nInsert the Ride Request ID of the request you want to manage or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int idRideRequest = scanner.nextInt();

        driverRequestsCLIController.executeSelectedId(idRideRequest);
    }

    public void rePrintAction() {
        Printer.printMessage("\nThe Ride Request ID you inserted is not in the list. Please, insert a correct value or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int idRideRequest = scanner.nextInt();

        driverRequestsCLIController.executeSelectedId(idRideRequest);
    }

    public void printContinue() {
        Printer.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }

    public void printOption() {
        Printer.printMessage("\n1) Accept Request \n2) Reject Request \n3) Back");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        try {
            driverRequestsCLIController.executeOption(choice);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }


}
