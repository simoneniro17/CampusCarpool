package com.example.CampusCarpool.viewcli;

import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.CommandErrorException;
import com.example.CampusCarpool.exception.NotImplementedException;
import com.example.CampusCarpool.graphiccontroller.cli.PassengerRequestsCLIController;

import java.util.Scanner;

public class PassengerRequestsViewCLI {
    private PassengerRequestsCLIController passengerRequestsCLIController;

    public PassengerRequestsViewCLI(PassengerRequestsCLIController passengerRequestsCLIController) {
        this.passengerRequestsCLIController = passengerRequestsCLIController;
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- MY REQUESTS --------------------------------------------");
        Printer.printMessage("1) Pending Requests \n2) Accepted Requests \n3) Rejected Request \n4) Back");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {

            // Esecuzione comando selezionato dall'utente attraverso il controller
            this.passengerRequestsCLIController.executeCommand(inputLine);

        } catch (CommandErrorException e) {

            // Gestione eventuali eccezioni e richiamo alla visualizzazione del menu
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}
