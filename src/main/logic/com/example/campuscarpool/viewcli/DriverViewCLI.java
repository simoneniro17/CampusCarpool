package com.example.campuscarpool.viewcli;

import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.CommandErrorException;
import com.example.campuscarpool.exception.NotImplementedException;
import com.example.campuscarpool.graphiccontroller.cli.DriverCLIController;

import java.util.Scanner;

public class DriverViewCLI {
    private final DriverCLIController driverCLIController;

    public DriverViewCLI(DriverCLIController driverCLIController) {
        this.driverCLIController = driverCLIController;
    }

    // Visualizzazione menu Driver
    public void run() {
        // Opzioni disponibili
        Printer.printMessage("\n-------------------------------------------- DRIVER HOMEPAGE --------------------------------------------");
        Printer.printMessage("1) Create Ride \n2) Manage Requests \n3) View Profile \n4) Logout");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {
            this.driverCLIController.executeCommand(inputLine);
        } catch (CommandErrorException | NotImplementedException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}