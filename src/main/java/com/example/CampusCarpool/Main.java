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
        ConnectionDB.getConnection();
        Scanner reader = new Scanner(System.in);
        int choice;
        Printer.printMessage("Which type of view do you want to use?\n\n1) GUI\n2) CLI\n\nInsert the number: ");

        while (true) {
            choice = reader.nextInt();
            if (choice == 1) {
                launch();
                break;
            } else if (choice == 2) {
                LoginCLIController loginCLIController = new LoginCLIController();
                loginCLIController.start();
            } else {
                Printer.printError("Number not valid, please insert 1 or 2");
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setStage(stage);

        stage.setTitle("CampusCarpool");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void stop() throws SQLException {
        ConnectionDB.closeConnection();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}