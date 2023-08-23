package com.example.CampusCarpool.viewcli;

import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.CommandErrorException;
import com.example.CampusCarpool.exception.NotImplementedException;
import com.example.CampusCarpool.graphiccontroller.cli.DriverCLIController;

import java.util.Scanner;

public class DriverViewCLI {

    private final DriverCLIController driverCLIController;

    public DriverViewCLI(DriverCLIController driverCLIController) {
        this.driverCLIController = driverCLIController;
    }

    // Avvio visualizzazione del menu per il driver
    public void run() {

        Printer.printMessage("\n-------------------------------------------- DRIVER HOMEPAGE --------------------------------------------");
        Printer.printMessage("1) Create Ride \n2) Manage Requests \n3) View Profile \n4) Logout");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {

            // Esecuzione comando selezionato dall'utente attraverso il controller
            this.driverCLIController.executeCommand(inputLine);

        } catch (CommandErrorException | NotImplementedException e) {

            // Gestione eventuali eccezioni e richiamo alla visualizzazione del menu
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}
