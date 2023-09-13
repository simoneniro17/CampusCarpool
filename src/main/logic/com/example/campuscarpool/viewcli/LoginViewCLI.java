package com.example.campuscarpool.viewcli;

import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.CommandErrorException;
import com.example.campuscarpool.exception.NotImplementedException;
import com.example.campuscarpool.graphiccontroller.cli.LoginCLIController;

import java.util.Scanner;

public class LoginViewCLI {
    private final LoginCLIController loginCLIController;

    public LoginViewCLI(LoginCLIController cliLoginControllerCurrent) {
        this.loginCLIController = cliLoginControllerCurrent;
    }

    // Avvio CLI di login
    public void run() {
        // Opzioni di login
        Printer.printMessage("\n-------------------------------------------- MAIN PAGE --------------------------------------------");
        Printer.printMessage("1) Login \n2) Login with Facebook \n3) Login with Google \n4) Sign up");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        try {
            this.loginCLIController.executeCommand(inputLine);
        } catch (CommandErrorException | NotImplementedException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }

    // Ottenimento credenziali di login dall'utente
    public void getCredentials() {
        Scanner scanner = new Scanner(System.in);

        Printer.printMessage("\nInsert email:");
        String email = scanner.nextLine();

        Printer.printMessage("\nInsert password:");
        String password = scanner.nextLine();

        try {
            // Verifica credenziali
            loginCLIController.checkLogin(email, password);
        } catch (Exception e) {
            Printer.printError(e.getMessage());
        }
    }
}