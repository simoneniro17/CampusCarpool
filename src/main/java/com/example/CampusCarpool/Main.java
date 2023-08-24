package com.example.CampusCarpool;

import com.example.CampusCarpool.connection.ConnectionDB;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.graphiccontroller.cli.LoginCLIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main extends Application {
    private static Stage stage;

    public static void main(String[] args) throws SQLException, IOException {
        // Connessione al database all'avvio
        ConnectionDB.getConnection();

        // Scelta tra GUI E CLI
        Scanner reader = new Scanner(System.in);
        int choice;
        Printer.printMessage("Which type of view do you want to use?\n\n1) GUI\n2) CLI\n\nInsert the number: ");

        while (true) {
            choice = reader.nextInt();
            if (choice == 1) {
                // Avvio GUI
                launch();
                break;
            } else if (choice == 2) {
                // Avvio CLI
                LoginCLIController loginCLIController = new LoginCLIController();
                loginCLIController.start();
                break;
            } else {
                Printer.printError("Number not valid, please insert 1 or 2");
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Caricamento layout GUI
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setStage(stage);

        // Configurazione finestra GUI
        stage.setTitle("CampusCarpool");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void stop() throws SQLException {
        // Chiusura connessione al database alla chiusura dell'applicazione
        ConnectionDB.closeConnection();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}