package com.example.CampusCarpool.viewcli;

import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.CommandErrorException;
import com.example.CampusCarpool.exception.NotImplementedException;
import com.example.CampusCarpool.graphiccontroller.cli.PassengerCLIController;

import java.util.Scanner;

public class PassengerViewCLI {

    private final PassengerCLIController passengerCLIController;

    public PassengerViewCLI(PassengerCLIController passengerCLIController) {
        this.passengerCLIController = passengerCLIController;
    }

    // Avvio visualizzazione del menu per il passenger
    public void run() {

        Printer.printMessage("\n-------------------------------------------- PASSENGER HOMEPAGE --------------------------------------------");
        Printer.printMessage("1) Search Ride \n2) My Requests \n3) View Profile \n4) Logout");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {

            // Esecuzione comando selezionato dall'utente attraverso il controller
            this.passengerCLIController.executeCommand(inputLine);

        } catch (CommandErrorException | NotImplementedException e) {

            // Gestione eventuali eccezioni e richiamo alla visualizzazione del menu
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}
