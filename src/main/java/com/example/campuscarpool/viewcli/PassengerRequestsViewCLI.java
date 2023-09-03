package com.example.campuscarpool.viewcli;

import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.CommandErrorException;
import com.example.campuscarpool.graphiccontroller.cli.PassengerRequestsCLIController;

import java.util.Scanner;

public class PassengerRequestsViewCLI {
    private final PassengerRequestsCLIController passengerRequestsCLIController;

    public PassengerRequestsViewCLI(PassengerRequestsCLIController passengerRequestsCLIController) {
        this.passengerRequestsCLIController = passengerRequestsCLIController;
    }

    // Visualizzazione menu per controllare le proprie richieste
    public void run() {
        Printer.printMessage("\n-------------------------------------------- MY REQUESTS --------------------------------------------");
        Printer.printMessage("1) Pending Requests \n2) Accepted Requests \n3) Rejected Request \n4) Back");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {
            this.passengerRequestsCLIController.executeCommand(inputLine);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}