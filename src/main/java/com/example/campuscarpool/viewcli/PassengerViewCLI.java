package com.example.campuscarpool.viewcli;

import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.CommandErrorException;
import com.example.campuscarpool.exception.NotImplementedException;
import com.example.campuscarpool.graphiccontroller.cli.PassengerCLIController;

import java.util.Scanner;

public class PassengerViewCLI {
    private final PassengerCLIController passengerCLIController;

    public PassengerViewCLI(PassengerCLIController passengerCLIController) {
        this.passengerCLIController = passengerCLIController;
    }

    // Visualizzazione menu Passenger
    public void run() {
        // Opzioni disponibili
        Printer.printMessage("\n-------------------------------------------- PASSENGER HOMEPAGE --------------------------------------------");
        Printer.printMessage("1) Search Ride \n2) My Requests \n3) View Profile \n4) Logout");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {
            this.passengerCLIController.executeCommand(inputLine);
        } catch (CommandErrorException | NotImplementedException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}