package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.NotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DriverGUIController {

    //  Per effettuare il logout
    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    //  Per visualizzare i dettagli del profilo
    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    //  Per andare alla schermata di creazione di una corsa
    public void toCreateRide() throws IOException {
        Stage stage = Main.getStage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("createRide.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    //  Per andare alla schermata di gestione delle richieste
    public void toManageRequests() throws IOException {
        Stage stage = Main.getStage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("driverRequests.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        DriverRequestsGUIController driverRequestsGUIController = fxmlLoader.getController();
        try {
            driverRequestsGUIController.displayDriverRequests();
        } catch (NotFoundException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }

        stage.setScene(scene);
        stage.show();
    }
}
